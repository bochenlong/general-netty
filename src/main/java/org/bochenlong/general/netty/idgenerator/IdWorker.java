package org.bochenlong.general.netty.idgenerator;

import org.bochenlong.general.netty.common.Constant;

import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;

/**
 * Created by bochenlong on 17-1-3.
 */
public class IdWorker {
    private static class Holder {
        private static IdWorker idWorker = new IdWorker(Constant.dataCenterId, Constant.workerId);
    }
    
    public static IdWorker instance() {
        return Holder.idWorker;
    }
    
    public IdWorker(long dataCenterId, long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", workerId));
        }
        
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", dataCenterId));
        }
        
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }
    
    private long dataCenterId;//
    private long workerId;//
    
    private final long twEpoch = 1288834974657L;
    private final long workerIdBits = 5L;
    private final long dataCenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private final long sequenceBits = 12L;
    
    private final long workerIdShift = sequenceBits;
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;
    
    private LongSupplier timeGen = () -> System.currentTimeMillis();
    private LongUnaryOperator tilNextMillis = lastTimestamp -> {
        long timestamp = timeGen.getAsLong();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen.getAsLong();
        }
        return timestamp;
    };
    
    public synchronized long nextId() {
        long timestamp = timeGen.getAsLong();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", (lastTimestamp - timestamp)));
        }
        
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis.applyAsLong(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twEpoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }
    
}
