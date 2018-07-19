package Client;
import bean.Body;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

public class ClientProxyHandler extends SimpleChannelInboundHandler<Object> implements Callable {

    private Body body;
    private Object result;
    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
         channel = ctx.channel();
    }

    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
            this.result=obj;
            notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public synchronized Object call() throws Exception {
        channel.writeAndFlush(body);
        wait();
        return result;
    }
}
