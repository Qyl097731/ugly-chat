package com.nju.quickstart.server;

import io.netty.channel.*;

/**
 * @description
 * @date:2022/12/6 18:03
 * @author: qyl
 */
public class SimpleChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline ( );
        pipeline.addLast (new ChatServerHandler ());
    }
}
