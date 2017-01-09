package org.bochenlong.general.netty.server.authpolicy;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.server.authpolicy.impl.UserDefine;
import org.bochenlong.general.netty.server.authpolicy.impl.SingleConnect;
import org.bochenlong.general.netty.server.authpolicy.impl.WhiteList;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class AuthHelper {
    public static boolean auth(ChannelHandlerContext ctx) {
        if (AuthManager.getAuthType() == AuthManager.AuthType.WHITE_LIST) {
            return auth(ctx, WhiteList.me().getPredicate());
        } else if (AuthManager.getAuthType() == AuthManager.AuthType.SINGLE_CONNECT) {
            return auth(ctx, SingleConnect.me().getPredicate());
        } else if (AuthManager.getAuthType() == AuthManager.AuthType.USER_DEFINE) {
            return auth(ctx, UserDefine.me().getPredicate());
        }
        return false;
    }
    
    public static void removeAuth(ChannelHandlerContext ctx) {
        if (AuthManager.getAuthType() == AuthManager.AuthType.WHITE_LIST) {
            removeAuth(ctx, WhiteList.me().getConsumer());
        } else if (AuthManager.getAuthType() == AuthManager.AuthType.SINGLE_CONNECT) {
            removeAuth(ctx, SingleConnect.me().getConsumer());
        } else if (AuthManager.getAuthType() == AuthManager.AuthType.USER_DEFINE) {
            removeAuth(ctx, UserDefine.me().getConsumer());
        }
    }
    
    private static boolean auth(ChannelHandlerContext ctx, Predicate predicate) {
        if (predicate.test(ctx)) return true;
        return false;
    }
    
    private static void removeAuth(ChannelHandlerContext ctx, Consumer consumer) {
        consumer.accept(ctx);
    }
}
