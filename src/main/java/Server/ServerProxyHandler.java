package Server;

import bean.Body;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

public class ServerProxyHandler extends SimpleChannelInboundHandler<Object> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        Body body=(Body)obj;
        String className = body.getClassName()+"Impl";
        String methodName = body.getMethodName();
        Class<?>[] paramerTypes = body.getParamerTypes();
        Object[] paramValues = body.getParamValues();
        Class<?> clazz = Class.forName(className);
        Object bean = clazz.newInstance();
        Method method = clazz.getDeclaredMethod(methodName, paramerTypes);
        channelHandlerContext.writeAndFlush(method.invoke(bean, paramValues));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
