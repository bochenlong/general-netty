package org.bochenlong.general.netty.server.auth;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by bochenlong on 17-1-9.
 */
public interface IAuth {
    boolean auth(ChannelHandlerContext ctx);
    
    boolean remove(ChannelHandlerContext ctx);
}
