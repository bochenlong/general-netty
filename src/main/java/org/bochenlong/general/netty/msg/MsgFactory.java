package org.bochenlong.general.netty.msg;


import org.bochenlong.general.netty.BizMsgType;
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
    
    public static NettyMsg newMsg(BizMsgType type, Object o) {
        Header header = new Header();
        header.setType(type.getType());
        return newMsg(header, o);
    }
    
    public static NettyMsg newMsg(BizMsgType type, Map<String, Object> customized, Object o) {
        NettyMsg msg = newMsg(type, o);
        msg.getHeader().getCustomized().putAll(customized);
        return msg;
    }
    
    public static NettyMsg newMsg(BizMsgType type, byte priority, Map<String, Object> customized, Object o) {
        NettyMsg msg = newMsg(type, customized, o);
        msg.getHeader().setPriority(priority);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(BizMsgType type) {
        Header header = new Header();
        header.setType(type.getType());
        NettyMsg msg = new NettyMsg(header);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(BizMsgType type, Map<String, Object> customized) {
        NettyMsg msg = newMsgHeader(type);
        msg.getHeader().getCustomized().putAll(customized);
        return msg;
    }
    
    public static NettyMsg newMsgHeader(BizMsgType type, byte priority, Map<String, Object> customized) {
        NettyMsg msg = newMsgHeader(type, customized);
        msg.getHeader().setPriority(priority);
        return msg;
    }
}
