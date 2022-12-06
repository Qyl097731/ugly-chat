package com.nju.quickstart.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @description
 * @date:2022/12/6 19:46
 * @author: qyl
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        String s = msg.toString (StandardCharsets.UTF_8);
        System.out.println (s);
        ctx.writeAndFlush (Unpooled.copiedBuffer ("I have received the msg : " + s, StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught (ctx, cause);
    }
}
