package com.nju.quickstart.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description
 * @date:2022/12/6 17:31
 * @author: qyl
 */
public class ChatServer {
    private static final EventLoopGroup GROUP = new NioEventLoopGroup ( );
    /**
     * 启动服务返回future
     */
    public static void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap ( );
            bootstrap.group (GROUP)
                    .channel (NioServerSocketChannel.class)
                    .childHandler (createInitializer ( ));
            ChannelFuture future = bootstrap.bind (8080).syncUninterruptibly ( );
            future.channel ().closeFuture ().sync ();
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        } finally {
            GROUP.shutdownGracefully ( );
        }
    }

    private static ChannelInitializer<Channel> createInitializer() {
        return new SimpleChannelInitializer ( );
    }

    public static void main(String[] args) {
        start ( );
    }
}
