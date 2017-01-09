package org.bochenlong.general.netty.msg.bean;

/**
 * Created by bochenlong on 16-11-4.
 */
public class NettyMsg {
    private Header header;
    private Object body;
    
    public NettyMsg() {
    }
    
    public NettyMsg(Header header) {
        this.header = header;
    }
    
    public NettyMsg(Header header, Object body) {
        this.header = header;
        this.body = body;
    }
    
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
    
    @Override
    public String toString() {
        return "NettyMsg{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
