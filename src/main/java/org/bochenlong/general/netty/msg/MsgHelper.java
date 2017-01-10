package org.bochenlong.general.netty.msg;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.IMsgQueue;
import org.bochenlong.general.netty.util.SpiUtil;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgHelper {
    private static IMsgQueue msgQueue = SpiUtil.getServiceImpl(IMsgQueue.class);
    
    public static void addMsgType(short type) {
        if (isBizType(type))
            create(type);
        throw new RuntimeException("biz_msg_type should be in [1,32767]");
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
    
    public static boolean isBizType(short type) {
        return type > 0 && type <= Short.MAX_VALUE;
    }
}
