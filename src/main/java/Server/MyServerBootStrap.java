package Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class MyServerBootStrap {

    public static void main(String[] args) throws InterruptedException {
        new MyServerBootStrap().start(8888);
    }

     NioEventLoopGroup worker;
     NioEventLoopGroup boss;
    public void start(int port) throws InterruptedException {
        try {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        worker = new NioEventLoopGroup();
        boss = new NioEventLoopGroup();
        serverBootstrap.group(worker,boss);
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                pipeline.addLast(new ServerProxyHandler());
            }
        });
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }

}
