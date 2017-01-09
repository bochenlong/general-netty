package org.bochenlong.general.netty.msg.queue.impl;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.IMsgQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bochenlong on 16-12-27.
 */
public class DefaultMsgQueue implements IMsgQueue<Short, NettyMsg> {
    private ConcurrentHashMap<Short, LinkedBlockingQueue<NettyMsg>> queueMap =
            new ConcurrentHashMap<>();
    
    @Override
    public void createQueue(Short type) {
        queueMap.computeIfAbsent(type, a -> new LinkedBlockingQueue<>());
    }
    
    @Override
    public boolean add(Short type, NettyMsg nettyMsg) {
        return queueMap.get(type).add(nettyMsg);
    }
    
    @Override
    public NettyMsg take(Short type) {
        try {
            return queueMap.get(type).take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
