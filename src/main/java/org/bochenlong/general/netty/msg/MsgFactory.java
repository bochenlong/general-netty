package org.bochenlong.general.netty.msg;


import org.bochenlong.general.netty.msg.bean.Header;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

import java.util.Map;

/**
 * Created by bochenlong on 17-1-3.
 */
public class MsgFactory {
    public static NettyMsg newMsg(Header header, Object o) {
        return new NettyMsg(header, o);
    }
    
    public static NettyMsg newMsg(byte bizMsgType, Object o) {
        Header header = new Header();
        header.setType(bizMsgType);
        return newMsg(header, o);
    }
    
    public static NettyMsg newMsg(byte bizMsgType, Map<String, Object> customized, Object o) {
        NettyMsg msg = newMsg(bizMsgType, o);
        msg.getHeader().getCustomized().putAll(customized);
        return msg;
    }
    
    public static NettyMsg newMsg(byte bizMsgType, byte priority, Map<String, Object> customized, Object o) {
        NettyMsg msg = newMsg(bizMsgType, customized, o);
        msg.getHeader().setPriority(priority);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(byte bizMsgType) {
        Header header = new Header();
        header.setType(bizMsgType);
        NettyMsg msg = new NettyMsg(header);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(byte bizMsgType, Map<String, Object> customized) {
        NettyMsg msg = newMsgHeader(bizMsgType);
        msg.getHeader().getCustomized().putAll(customized);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(byte bizMsgType, byte priority, Map<String, Object> customized) {
        NettyMsg msg = newMsgHeader(bizMsgType, customized);
        msg.getHeader().setPriority(priority);
        return msg;
    }
}
