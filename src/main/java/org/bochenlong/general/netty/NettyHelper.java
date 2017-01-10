package org.bochenlong.general.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.client.NettyClient;
import org.bochenlong.general.netty.common.exception.RemoteException;
import org.bochenlong.general.netty.func.CallBack;
import org.bochenlong.general.netty.msg.MsgHelper;
import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.resp.NettyFuture;
import org.bochenlong.general.netty.resp.RequestProp;
import org.bochenlong.general.netty.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by bochenlong on 16-11-4.
 */
public class NettyHelper {
    
    private static Logger logger = LoggerFactory.getLogger(NettyHelper.class);
    
    public static String getIp(SocketAddress socketAddress) {
        String address = socketAddress.toString();
        address = address.substring(address.indexOf("/") + 1, address.indexOf(":"));
        return address;
    }
    
    public static String getLocalIP() {
        String localIP = null;// 如果没有找到外网IP，则返回本机IP
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {
                        if (!ip.isSiteLocalAddress()) {
                            // 如果找到外网IP，第一时间返回
                            return ip.getHostAddress();
                        } else {
                            // 否则保留找到的本机IP
                            localIP = ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return localIP;
    }
    
    public static void sendBack(String host, NettyMsg msg) {
        msg.getHeader().setRequestProp(RequestProp.RESPONSE.getProp());
        connect(host).writeAndFlush(msg);
    }
    
    public static void sendAsync(String host, NettyMsg msg) {
        msg.getHeader().setRequestProp(RequestProp.ASYNC_REQUEST.getProp());
        connect(host).writeAndFlush(msg);
    }
    
    public static Future<NettyMsg> sendSync(String host, NettyMsg msg) throws RemoteException {
        msg.getHeader().setRequestProp(RequestProp.SYNC_REQUEST.getProp());
        Channel channel = connect(host);
        try {
            ChannelFuture future = channel.writeAndFlush(msg);
            future.await(NettyManager.me().getSEND_TIME_OUT());
            Throwable cause = future.cause();
            if (cause != null) {
                throw cause;
            }
        } catch (Throwable throwable) {
            throw new RemoteException(String.format("fail sendSync message to %s, cause %s", getIp(channel.remoteAddress()), throwable.getMessage()));
        }
        // 封装等待Future
        return NettyChannel.futures.computeIfAbsent(msg.getHeader().getRequestId(), a -> new NettyFuture<>());
    }
    
    
    private static void writeResp(NettyMsg msg) {
        NettyFuture<NettyMsg> f = NettyChannel.futures.get(msg.getHeader().getRequestId());
        if (f == null) return;
        f.set(msg);
        NettyChannel.futures.remove(msg.getHeader().getRequestId());
    }
    
    
    public static Channel connect(String host) {
        Channel channel = new NettyClient(host).channel();
        return NettyChannel.channels.computeIfAbsent(host, a -> channel);
    }
    
    public static void deliveryMsg(ChannelHandlerContext ctx, NettyMsg msg) {
        logger.info("receive msg {}", msg);
        // 如果resp，则writeResp
        if (msg.getHeader().getRequestProp() == RequestProp.RESPONSE.getProp()) {
            writeResp(msg);
            return;
        }
        // 否则传递到队列消费
        msg.getHeader().setRemote(getIp(ctx.channel().remoteAddress()));
        MsgHelper.add(msg.getHeader().getType(), msg);
        
    }
    
    public static void startServer() {
        NettyManager.me().getBIZ_MSG_TYPE().stream()
                .map(a -> a.values()).flatMap(Collection::stream)
                .map(Integer::shortValue).forEach(MsgHelper::addMsgType);
        new NettyServer().start();
    }
    
    public static void close(String host) {
        Channel channel = NettyChannel.channels.remove(host);
        if (channel != null) channel.close();
    }
    
    public static void close(String host, CallBack callBack) {
        close(host);
        callBack.call();
    }
    
    public static boolean removeChannel(ChannelHandlerContext ctx) {
        return NettyChannel.channels.remove(getIp(ctx.channel().remoteAddress()), ctx.channel());
    }
    
    public static void closeAndRecon(String host) {
        logger.info("closeAndRecon {}", host);
        closeAndRecon(host, Long.MAX_VALUE, NettyManager.me().getRETRY_TIME(), TimeUnit.MILLISECONDS);
    }
    
    public static void closeAndRecon(String host, long retry, long interval, TimeUnit unit) {
        long t = System.currentTimeMillis();
        long finalRetry = unit.toMillis(retry);
        close(host, () -> {
            int i = 0;
            while (System.currentTimeMillis() - t <= finalRetry) {
                logger.info("reconnect at {}", i++);
                Channel channel = null;
                
                try {
                    channel = connect(host);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                
                if (channel != null) {
                    NettyChannel.channels.put(host, channel);
                    logger.info("client reconnect ok");
                    break;
                }
                
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
            logger.info("client reconnect fail");
        });
    }
    
    
}
