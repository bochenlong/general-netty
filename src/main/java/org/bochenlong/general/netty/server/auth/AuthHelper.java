package org.bochenlong.general.netty.server.auth;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.util.SpiUtil;

/**
 * Created by bochenlong on 17-1-9.
 */
public class AuthHelper {
    private static IAuth auth = SpiUtil.getServiceImpl(IAuth.class);
    
    public static boolean auth(ChannelHandlerContext ctx) {
        return auth.auth(ctx);
    }
    
    public static boolean remove(ChannelHandlerContext ctx) {
        return auth.remove(ctx);
    }
}
