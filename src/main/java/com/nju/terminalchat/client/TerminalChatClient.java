package com.nju.terminalchat.client;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.nju.utils.ReaderUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

import static com.nju.utils.ReaderUtils.*;

/**
 * @description
 * @date:2022/12/6 20:34
 * @author: qyl
 */
public class TerminalChatClient {
    private final EventLoopGroup group = new NioEventLoopGroup ( );


    private static Channel channel;

    private final int port;

    public TerminalChatClient(int port) {
        this.port = port;
    }

    public void start() {
        Bootstrap bootstrap = new Bootstrap ( );
        bootstrap.group (group)
                .channel (NioSocketChannel.class)
                .handler (createInitializer ( ));

        ChannelFuture future = bootstrap.connect (new InetSocketAddress (8080));
        future.syncUninterruptibly ( );
        channel = future.channel ( );
    }

    private ChannelInitializer<Channel> createInitializer() {
        return new TerminalChatClientInitializer ( );
    }

    public void destroy() {
        group.shutdownGracefully ( );
    }

    static void chat() throws IOException {
        while (true) {
            String input = getInputMsg ( );
            if (!input.equals (OVER)) {
                channel.writeAndFlush (Unpooled.copiedBuffer (input, StandardCharsets.UTF_8));
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        TerminalChatClient client = new TerminalChatClient (8080);
        client.start ( );
        Runtime.getRuntime ( ).addShutdownHook (new Thread (client::destroy));
        chat ( );
        channel.closeFuture ( ).sync ( );
    }

}
