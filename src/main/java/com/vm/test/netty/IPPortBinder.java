package com.vm.test.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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
							ch.pipeline().addLast("Http-decoder", new HttpRequestDecoder()); // 请求消息解码器
							ch.pipeline().addLast("Http-aggregator", new HttpObjectAggregator(65535));// 目的是将多个消息转换为单一的request或者response对象
							ch.pipeline().addLast("Http-encoder", new HttpResponseEncoder());//响应解码器
							ch.pipeline().addLast("Http-chunked", new ChunkedWriteHandler());//目的是支持异步大文件传输（）
                            ch.pipeline().addLast("Http-server-handler", new SimpleChannelInboundHandler<FullHttpRequest>() {

								@Override
								protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {


								    System.out.println(fullHttpRequest.headers().get("userId"));
								    System.out.println(fullHttpRequest.content().toString(StandardCharsets.UTF_8));

									FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.ACCEPTED,
											Unpooled.copiedBuffer("request success:" + HttpResponseStatus.ACCEPTED.toString() + "\r\n", CharsetUtil.UTF_8));
									response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html;charset=UTF-8");
									channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
								}
							});
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
