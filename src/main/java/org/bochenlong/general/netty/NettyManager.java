package org.bochenlong.general.netty;

/**
 * Created by bochenlong on 16-10-24.
 */
public class  NettyManager {
    public static String DEFAULT_HOST = NettyHelper.getLocalIP();
    public static int DEFAULT_PORT = 8989;
    
    public static int MSG_MAX_LEN = 1024 * 1024;
    public static final int MSG_LEN_OFFSET = 0;
    public static final int MSG_LEN_FIELD = 4;
    public static final int MSG_LEN_ADJUSTMENT = -5;
    
    public static int BACKLOG_SIZE = 1204;
    
    public static int CONNECT_TIME_OUT = 1000 * 30;
    
    public static int SEND_TIME_OUT = 1000 * 15;
    
    public static int RETRY_TIME = '\uea60';
    
    public static void setDefaultHost(String defaultHost) {
        DEFAULT_HOST = defaultHost;
    }
    
    public static void setDefaultPort(int defaultPort) {
        DEFAULT_PORT = defaultPort;
    }
    
    public static void setMsgMaxLen(int msgMaxLen) {
        MSG_MAX_LEN = msgMaxLen;
    }
    
    public static void setBacklogSize(int backlogSize) {
        BACKLOG_SIZE = backlogSize;
    }
    
    public static void setConnectTimeOut(int connectTimeOut) {
        CONNECT_TIME_OUT = connectTimeOut;
    }
    
    public static void setSendTimeOut(int sendTimeOut) {
        SEND_TIME_OUT = sendTimeOut;
    }
}
