package org.bochenlong.general.netty.msg.queue;

/**
 * Created by bochenlong on 16-12-28.
 */
public interface IMsgQueue<Short, NettyMessage> {
    void createQueue(Short t);
    
    boolean add(Short t, NettyMessage message);
    
    NettyMessage take(Short t);
}
