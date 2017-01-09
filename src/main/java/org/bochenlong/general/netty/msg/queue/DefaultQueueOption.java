package org.bochenlong.general.netty.msg.queue;

import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.msg.queue.impl.DefaultMsgQueue;

/**
 * Created by bochenlong on 16-12-22.
 */
public class DefaultQueueOption {
    private static class Holder {
        private static DefaultQueueOption queueOption = new DefaultQueueOption();
    }
    
    public static DefaultQueueOption me() {
        return Holder.queueOption;
    }
    
    private IMsgQueue<Short, NettyMsg> IMsgQueue =
            new DefaultMsgQueue();
    
    
    public IMsgQueue<Short, NettyMsg> getIMsgQueue() {
        return me().IMsgQueue;
    }
    
    public void setIMsgQueue(IMsgQueue<Short, NettyMsg> IMsgQueue) {
        me().IMsgQueue = IMsgQueue;
    }
}
