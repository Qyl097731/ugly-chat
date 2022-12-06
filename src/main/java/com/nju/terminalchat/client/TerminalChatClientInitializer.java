package com.nju.terminalchat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @description
 * @date:2022/12/6 20:55
 * @author: qyl
 */
public class TerminalChatClientInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline ( );
        pipeline.addLast (new TerminalClientInHandler());
    }
}
