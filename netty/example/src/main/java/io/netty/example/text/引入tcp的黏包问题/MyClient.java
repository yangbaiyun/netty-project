package io.netty.example.text.引入tcp的黏包问题;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: Changwu
 * @Date: 2019/12/9 13:15
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture localhost = bootstrap.group(worker)
                    .handler(new MyClientInitializer())
                    .channel(NioSocketChannel.class)
                    .connect("localhost", 9999)
                    .sync();
            localhost.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
