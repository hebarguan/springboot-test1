package com.vm.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @author huaihai.guan
 * @since 2021/8/5 10:41
 */
public class IPPortBinder {

    public static void main(String[] args) {

        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boosGroup, workGroup).channel(NioServerSocketChannel.class)
			.childOption(ChannelOption.TCP_NODELAY, true)
			.childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue").handler(new ServerHandler())
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
						}
					});
			ChannelFuture future = bootstrap.bind(8888).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
    }
}
