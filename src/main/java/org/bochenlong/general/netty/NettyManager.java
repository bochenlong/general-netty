package org.bochenlong.general.netty;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 16-10-24.
 */
public class NettyManager {
    private static class Holder {
        private static NettyManager manager = init();
        
        private static NettyManager init() {
            InputStream in = NettyManager.class.getClassLoader().getResourceAsStream("netty.yaml");
            return new Yaml().loadAs(in, NettyManager.class);
        }
    }
    
    public static NettyManager me() {
        return Holder.manager;
    }
    
    
    private static String DEFAULT_HOST = NettyHelper.getLocalIP();
    private static int DEFAULT_PORT = 8989;
    
    private static int MSG_MAX_LEN = 1024 * 1024;
    private static int MSG_LEN_OFFSET = 0;
    private static int MSG_LEN_FIELD = 4;
    private static int MSG_LEN_ADJUSTMENT = -5;
    
    private static int BACKLOG_SIZE = 1204;
    
    private static int CONNECT_TIME_OUT = 1000 * 30;
    private static int SEND_TIME_OUT = 1000 * 15;
    private static int RETRY_TIME = 1000 * 60;
    
    private static long DATA_CENTER_ID = 1L;
    private static long WORK_ID = 1L;
    
    private static List<Map<String, Short>> BIZ_MSG_TYPE = new ArrayList<>();
    
    
    public String getDEFAULT_HOST() {
        return DEFAULT_HOST;
    }
    
    public void setDEFAULT_HOST(String DEFAULT_HOST) {
        this.DEFAULT_HOST = DEFAULT_HOST;
    }
    
    public int getDEFAULT_PORT() {
        return DEFAULT_PORT;
    }
    
    public void setDEFAULT_PORT(int DEFAULT_PORT) {
        this.DEFAULT_PORT = DEFAULT_PORT;
    }
    
    public int getMSG_MAX_LEN() {
        return MSG_MAX_LEN;
    }
    
    public void setMSG_MAX_LEN(int MSG_MAX_LEN) {
        this.MSG_MAX_LEN = MSG_MAX_LEN;
    }
    
    public int getMSG_LEN_OFFSET() {
        return MSG_LEN_OFFSET;
    }
    
    public void setMSG_LEN_OFFSET(int MSG_LEN_OFFSET) {
        this.MSG_LEN_OFFSET = MSG_LEN_OFFSET;
    }
    
    public int getMSG_LEN_FIELD() {
        return MSG_LEN_FIELD;
    }
    
    public void setMSG_LEN_FIELD(int MSG_LEN_FIELD) {
        this.MSG_LEN_FIELD = MSG_LEN_FIELD;
    }
    
    public int getMSG_LEN_ADJUSTMENT() {
        return MSG_LEN_ADJUSTMENT;
    }
    
    public void setMSG_LEN_ADJUSTMENT(int MSG_LEN_ADJUSTMENT) {
        this.MSG_LEN_ADJUSTMENT = MSG_LEN_ADJUSTMENT;
    }
    
    public int getBACKLOG_SIZE() {
        return BACKLOG_SIZE;
    }
    
    public void setBACKLOG_SIZE(int BACKLOG_SIZE) {
        this.BACKLOG_SIZE = BACKLOG_SIZE;
    }
    
    public int getCONNECT_TIME_OUT() {
        return CONNECT_TIME_OUT;
    }
    
    public void setCONNECT_TIME_OUT(int CONNECT_TIME_OUT) {
        this.CONNECT_TIME_OUT = CONNECT_TIME_OUT;
    }
    
    public int getSEND_TIME_OUT() {
        return SEND_TIME_OUT;
    }
    
    public void setSEND_TIME_OUT(int SEND_TIME_OUT) {
        this.SEND_TIME_OUT = SEND_TIME_OUT;
    }
    
    public int getRETRY_TIME() {
        return RETRY_TIME;
    }
    
    public void setRETRY_TIME(int RETRY_TIME) {
        this.RETRY_TIME = RETRY_TIME;
    }
    
    public long getDATA_CENTER_ID() {
        return DATA_CENTER_ID;
    }
    
    public void setDATA_CENTER_ID(long DATA_CENTER_ID) {
        this.DATA_CENTER_ID = DATA_CENTER_ID;
    }
    
    public long getWORK_ID() {
        return WORK_ID;
    }
    
    public void setWORK_ID(long WORK_ID) {
        this.WORK_ID = WORK_ID;
    }
    
    public List<Map<String, Short>> getBIZ_MSG_TYPE() {
        return BIZ_MSG_TYPE;
    }
    
    public void setBIZ_MSG_TYPE(List<Map<String, Short>> BIZ_MSG_TYPE) {
        this.BIZ_MSG_TYPE = BIZ_MSG_TYPE;
    }
    
    public static void main(String[] args) {
        System.out.println(NettyManager.WORK_ID);
    }
}
