package org.bochenlong.general.netty;

/**
 * Created by bochenlong on 16-12-29.
 */
public enum BizMsgType {
    AMOUNT((short) 100);
    private short type;
    
    BizMsgType(short type) {
        this.type = type;
    }
    
    public short getType() {
        return type;
    }
    
    public void setType(short type) {
        this.type = type;
    }
}
