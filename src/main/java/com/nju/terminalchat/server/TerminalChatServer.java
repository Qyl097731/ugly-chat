package com.nju.terminalchat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

/**
 * @description
 * @date:2022/12/6 20:48
 * @author: qyl
 */
public class TerminalChatServer {
    private final EventLoopGroup mainGroup = new NioEventLoopGroup ( );
    private final EventLoopGroup subGroup = new NioEventLoopGroup ( );

    private final ChannelGroup channelGroup = new DefaultChannelGroup (ImmediateEventExecutor.INSTANCE);

    private static Channel channel;

    public void start() {
        ServerBootstrap server = new ServerBootstrap ( );
        server.group (mainGroup)
                .channel (NioServerSocketChannel.class)
                .childHandler (createInitializer (channelGroup));
        ChannelFuture future = server.bind (8080);
        future.syncUninterruptibly ( );
        channel = future.channel ( );
    }

    private ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
        return new TerminalChatServerInitializer (channelGroup);
    }

    public void destroy() {
        if (channel != null) {
            channel.close ( );
        }
        mainGroup.shutdownGracefully ( );
        subGroup.shutdownGracefully ( );
    }

    public static void main(String[] args) throws InterruptedException {
        TerminalChatServer server = new TerminalChatServer ( );
        server.start ( );
        Runtime.getRuntime ( ).addShutdownHook (new Thread (server::destroy));
        channel.closeFuture ( ).sync ( );
    }
}
