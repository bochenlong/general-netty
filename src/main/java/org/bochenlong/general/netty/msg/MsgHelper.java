package org.bochenlong.general.netty.msg;

import org.bochenlong.general.netty.msg.queue.IMsgQueue;
import org.bochenlong.general.netty.util.SpiUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgHelper {
    private static Set<Short> set = new HashSet<>();
    
    public static boolean addMsgType(short type) {
        assert type >= 100;
        getMsgQueue().create(type);
        return set.add(type);
    }
    
    public static boolean addMsgTypes(Set<Short> types) {
        types.stream().forEach(MsgHelper::addMsgType);
        return true;
    }
    
    public static IMsgQueue getMsgQueue() {
        return SpiUtil.getServiceImpl(IMsgQueue.class);
    }
}
