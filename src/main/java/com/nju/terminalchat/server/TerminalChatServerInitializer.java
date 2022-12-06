package com.nju.terminalchat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;

/**
 * @description
 * @date:2022/12/6 21:21
 * @author: qyl
 */
public class TerminalChatServerInitializer extends ChannelInitializer<Channel> {
    private ChannelGroup group;

    public TerminalChatServerInitializer(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void initChannel(Channel ch){
        ChannelPipeline pipeline = ch.pipeline ( );
        pipeline.addLast (new TerminalChatServerHandler(group));
    }
}
