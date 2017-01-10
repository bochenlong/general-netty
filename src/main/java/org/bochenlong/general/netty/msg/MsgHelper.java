package org.bochenlong.general.netty.msg;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.IMsgQueue;
import org.bochenlong.general.netty.util.SpiUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgHelper {
    private static IMsgQueue msgQueue = SpiUtil.getServiceImpl(IMsgQueue.class);
    
    public static void addMsgType(short type) {
        assert type >= 100;
        create(type);
    }
    
    public static void create(short t) {
        msgQueue.create(t);
    }
    
    public static boolean add(short t, NettyMsg msg) {
        return msgQueue.add(t, msg);
    }
    
    public static NettyMsg take(short t) {
        return msgQueue.take(t);
    }
}
