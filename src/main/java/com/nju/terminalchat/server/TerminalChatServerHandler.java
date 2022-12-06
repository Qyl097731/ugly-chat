package com.nju.terminalchat.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @description
 * @date:2022/12/6 21:22
 * @author: qyl
 */
public class TerminalChatServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private ChannelGroup group;

    private static final Map<Integer, String> resMap = new HashMap<Integer, String> ( ) {{
        put (0, "Gitee 是开源中国社区2013年推出的基于 Git 的代码托管服务，目前已经成为国内知名的代码托管平台，致力于为国内开发者提供优质稳定的托管服务。");

        put (1, "Gitee 除了提供最基础的 Git 代码托管之外，还提供代码在线查看、历史版本查看、Fork、Pull Request、打包下载任意版本、Issue、Wiki 、保护分支、代码质量检测、PaaS项目演示等方便管理、开发、协作、共享的功能。");

        put (2, "目前 Gitee 提供了面向个人开发者的「社区版」和面向企业研发团队的「企业版」服务，支持 Git/SVN 管理，提供代码审核、Bug 跟踪以及 Webhook 钩子回调等功能，开发者可以在 Gitee" +
                " 自行创建仓库。");
    }};

    public TerminalChatServerHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        String input = msg.toString (StandardCharsets.UTF_8);
        out.println (ctx.channel ().remoteAddress () + "\n" + input);
        try {
            int id = Integer.parseInt (input);
            String res = resMap.getOrDefault (id, "请致电人工：10086");
            ctx.writeAndFlush (Unpooled.copiedBuffer (res, StandardCharsets.UTF_8));
        } catch (Exception e) {
            group.writeAndFlush (Unpooled.copiedBuffer ("不明原因，服务器暂时关闭",StandardCharsets.UTF_8));
            group.close ();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        out.println ("client connected successfully : " + ctx.channel ( ).remoteAddress ( ));
        group.add (ctx.channel ( ));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        out.println ("客户端: " + ctx.channel ( ).remoteAddress ( ).toString ( ) + "  结束");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace ();
    }
}
