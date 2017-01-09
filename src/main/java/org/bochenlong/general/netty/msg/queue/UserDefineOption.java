package org.bochenlong.general.netty.msg.queue;

import org.bochenlong.general.netty.msg.bean.NettyMsg;

/**
 * Created by bochenlong on 16-12-29.
 */
public class UserDefineOption {
    private static class Holder {
        private static UserDefineOption queueOption = new UserDefineOption();
    }
    
    public static UserDefineOption me() {
        return UserDefineOption.Holder.queueOption;
    }
    
    private IMsgQueue<Short, NettyMsg> IMsgQueue = null;
    
    
    public IMsgQueue<Short, NettyMsg> getIMsgQueue() {
        return me().IMsgQueue;
    }
    
    public void setIMsgQueue(IMsgQueue<Short, NettyMsg> IMsgQueue) {
        me().IMsgQueue = IMsgQueue;
    }
}
