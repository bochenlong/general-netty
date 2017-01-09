package org.bochenlong.general.netty;

/**
 * Created by bochenlong on 16-12-29.
 */
public enum BizMsgType {
    AMOUNT((short) 100),
    TASK((short) 200);
    
    private short type;
    BizMsgType(Short type) {
        this.type = type;
    }
    
    public short getType() {
        return type;
    }
}
