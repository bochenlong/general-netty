package org.bochenlong.general.netty.msg.queue;

import org.bochenlong.general.netty.msg.bean.NettyMsg;

/**
 * Created by bochenlong on 16-12-28.
 */
public interface IMsgQueue {
    void create(short t);
    
    boolean add(short t, NettyMsg msg);
    
    NettyMsg take(short t);
}
