package org.bochenlong.general.netty.msg;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.DefaultQueueOption;
import org.bochenlong.general.netty.msg.queue.UserDefineOption;
import org.bochenlong.general.netty.msg.queue.IMsgQueue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgHelper {
    private static Set<Short> set = new HashSet<>();
    
    public static boolean addMsgType(short type) {
        assert type >= 100;
        getMsgQueue().createQueue(type);
        return set.add(type);
    }
    
    public static boolean addMsgTypes(Set<Short> types) {
        types.stream().forEach(MsgHelper::addMsgType);
        return true;
    }
    
    public static boolean add(short type, NettyMsg message) {
        return getMsgQueue().add(type, message);
    }
    
    
    public static void take(short type, NettyMsg message) {
        getMsgQueue().take(type);
    }
    
    
    private static IMsgQueue<Short, NettyMsg> getMsgQueue() {
        if (MsgManager.getMsgQueueType() == MsgManager.MsgQueueType.DEFAULT) {
            return DefaultQueueOption.me().getIMsgQueue();
        } else if (MsgManager.getMsgQueueType() == MsgManager.MsgQueueType.USER_DEFINE) {
            return UserDefineOption.me().getIMsgQueue();
        }
        return null;
    }
}
