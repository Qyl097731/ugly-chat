package com.nju.quickstart.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description
 * @date:2022/12/6 17:31
 * @author: qyl
 */
public class ChatClient {
    private static final EventLoopGroup GROUP = new NioEventLoopGroup ( );

    /**
     * 启动服务返回future
     */
    public static void start() {
        Bootstrap bootstrap = new Bootstrap ();
        bootstrap.group ( GROUP  )
                .channel (NioSocketChannel.class)
                .handler (new ChatClientHandler());

        try {
            ChannelFuture future = bootstrap.connect (new InetSocketAddress (8080)).sync ();
            future.channel ().closeFuture ().sync ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }

    public static void main(String[] args) {
        start ();
    }
}
