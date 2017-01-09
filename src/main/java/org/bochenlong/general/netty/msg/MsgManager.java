package org.bochenlong.general.netty.msg;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.UserDefineOption;
import org.bochenlong.general.netty.msg.queue.IMsgQueue;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgManager {
    
    private static MsgQueueType msgQueueType;
    
    public static MsgQueueType getMsgQueueType() {
        if (msgQueueType == null) setDefault();
        return msgQueueType;
    }
    
    public static void setDefault() {
        msgQueueType = MsgQueueType.DEFAULT;
    }
    
    public static void setUserDefine(IMsgQueue<Short, NettyMsg> IMsgQueue) {
        UserDefineOption.me().setIMsgQueue(IMsgQueue);
        msgQueueType = MsgQueueType.USER_DEFINE;
    }
    
    public enum MsgQueueType {
        DEFAULT,
        USER_DEFINE
    }
}
