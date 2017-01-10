package org.bochenlong.general.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.bochenlong.general.netty.NettyChannel;
import org.bochenlong.general.netty.client.handler.ClientInHandler;
import org.bochenlong.general.netty.codec.MsgDecoder;
import org.bochenlong.general.netty.NettyManager;
import org.bochenlong.general.netty.codec.MsgEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bochenlong on 16-11-3.
 */
public class NettyClient {
    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);
    
    private volatile Channel channel;
    
    public NettyClient(String host) {
        connect(host, NettyManager.DEFAULT_PORT);
    }
    
    public NettyClient(String host, int port) {
        connect(host, port);
    }
    
    private EventLoopGroup workGroup;
    
    private void connect(String host, int port) {
        try {
            workGroup = new NioEventLoopGroup();
            
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)// 保活
                    .option(ChannelOption.TCP_NODELAY, false)// 有数据就发
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, NettyManager.CONNECT_TIME_OUT) // 连接超时时间
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MsgDecoder(NettyManager.MSG_MAX_LEN
                                    , NettyManager.MSG_LEN_OFFSET, NettyManager.MSG_LEN_FIELD
                                    , NettyManager.MSG_LEN_ADJUSTMENT));
                            ch.pipeline().addLast(new MsgEncoder());
                            ch.pipeline().addLast(new ClientInHandler());
                        }
                    });
            
            ChannelFuture future = bootstrap.connect(host, port).sync();
            this.channel = future.channel();
            
            logger.info("NettyClient connect ok {} - {}", host, port);
            this.channel.closeFuture().addListener(a -> {
                logger.info("NettyClient connect close {} - {}", host, port);
                // 关闭资源
                this.close();
            });
        } catch (Exception e) {
            logger.error("NettyClient connect exception : {} - {} / {}", host, port, e.getMessage());
            // 关闭资源
            this.close();
            e.printStackTrace();
        }
    }
    
    public void disConnect() {
        this.channel.close();
    }
    
    public Channel channel() {
        return this.channel;
    }
    
    private void close() {
        workGroup.shutdownGracefully();
        logger.info("NettyClient close over");
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.channel.id().hashCode();
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NettyClient)) {
            return false;
        }
        NettyClient p = (NettyClient) obj;
        return p.channel.equals(this.channel);
    }
    
}
