package com.kggs.wg5000sdk.service;

import com.kggs.wg5000sdk.req.Instructions;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * Netty 太重了，感觉改成Socket直连要轻便一些
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/21 9:16
 */
public class WG5000RemoteService {

    private static Logger log = LoggerFactory.getLogger(WG5000RemoteService.class);
    private Client client;

    /**
     * 远程开门
     *
     * @param instructions
     */
    public void Open(Instructions instructions) {
        log.info(instructions.toString());
        this.client = new Client();
        this.client.Start(instructions);
        log.info("远程开门执行完毕");
    }

    @Slf4j
    public static class Client {

        public void Start(Instructions instructions) {
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                            ch.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            try {
                ChannelFuture channelFuture = bootstrap.connect(instructions.getAddress().getIp(), instructions.getAddress().getPort()).sync();
                log.info("客户端连接服务器成功....");
                Thread.sleep(100);
                String command = this.CmdBuilder(instructions.getUsername(), instructions.getPassword(), instructions.getStatus().name(), instructions.getDoorNumber());
                channelFuture.channel().writeAndFlush(command);
                channelFuture.channel().closeFuture().await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }


        /**
         * 构建远程开门指令
         * <p>
         * N3000 -USER "abc" -PASSWORD "123" -Open "m001-1号"
         * </p>
         *
         * @param username   用户名
         * @param password   密码
         * @param cmd        制定动作
         * @param doorNumber 门号
         * @return 指令字符串
         */
        private String CmdBuilder(String username, String password, String cmd, String doorNumber) {
            StringBuilder builder = new StringBuilder();
            builder.append("N3000 ");
            builder.append("-USER \"" + username + "\" ");
            builder.append("-PASSWORD \"" + password + "\" ");
            builder.append("-" + cmd + " ");
            builder.append("\"" + doorNumber + "\" ");
            return builder.toString();
        }
    }


    /**
     * 基于Netty实现的客户端处理器
     **/
    @Slf4j
    public static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("客户端Active .....");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
            log.info("thread.name={}", Thread.currentThread().getName());
            System.out.println("server channelRead......");
            System.out.println(ctx.channel().remoteAddress() + "----->Server :" + msg.toString());
            ctx.writeAndFlush("server say :" + msg);
            ctx.disconnect();
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
