package com.nju.terminalchat.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.*;

/**
 * @description
 * @date:2022/12/6 21:01
 * @author: qyl
 */
public class TerminalClientInHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        out.println (LocalDateTime.now ( ).format (DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm:ss")));
        String resp = msg.toString (StandardCharsets.UTF_8);
        out.println (resp);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        out.println ("connect to server successfully : " + ctx.channel ( ).remoteAddress ( ));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        out.println ("已经关闭连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace ( );
    }
}
