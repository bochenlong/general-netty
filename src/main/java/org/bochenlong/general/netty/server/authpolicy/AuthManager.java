package org.bochenlong.general.netty.server.authpolicy;

import org.bochenlong.general.netty.server.authpolicy.impl.UserDefine;
import org.bochenlong.general.netty.server.authpolicy.impl.WhiteList;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class AuthManager {
    private static AuthType authType;
    
    public static AuthType getAuthType() {
        if (authType == null) setSINGLE_CON();
        return authType;
    }
    
    public static void setSINGLE_CON() {
        authType = AuthType.SINGLE_CONNECT;
    }
    
    public static void setWHITE_LIST(Set<String> whiteList) {
        assert whiteList != null;
        authType = AuthType.WHITE_LIST;
        WhiteList.me().setWhiteList(whiteList);
    }
    
    public static void setUSER_DEFINE(Predicate predicate, Consumer consumer) {
        assert predicate != null && consumer != null;
        authType = AuthType.USER_DEFINE;
        UserDefine.me().setPredicate(predicate);
        UserDefine.me().setConsumer(consumer);
    }
    
    public enum AuthType {
        WHITE_LIST,
        SINGLE_CONNECT,
        USER_DEFINE
    }
}
