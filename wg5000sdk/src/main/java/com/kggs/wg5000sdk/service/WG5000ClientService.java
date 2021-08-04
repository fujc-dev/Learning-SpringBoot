package com.kggs.wg5000sdk.service;

import com.kggs.wg5000sdk.enums.Status;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Netty 太重了，感觉改成Socket直连要轻便一些，架不住方便
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/21 9:16
 */
public class WG5000ClientService {

    /**
     *
     */
    private final String SERVER_ERROR_MSG = "iRet=0, Failed";
    private final String SERVER_SUCCESS_MSG = "iRet=1, OK";
    private String ip;
    private int port;
    private String username;
    private String password;
    private String doorNumber;
    private Status status;

    /**
     * 远程开门执行结果维护
     */
    private Boolean _execute_status = false;

    public WG5000ClientService(String ip,
                               int port,
                               String username,
                               String password,
                               Status status,
                               String doorNumber) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.status = status;
        this.doorNumber = doorNumber;
    }


    public boolean Open() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder(Charset.forName("gb2312")));
                        ch.pipeline().addLast(new StringDecoder(Charset.forName("gb2312")));
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            System.out.println("----->客户端连接服务器成功....");
            channelFuture.channel().closeFuture().await(5000);
            System.out.println("----->客户端与服务器连接已关闭..");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        System.out.println("----->远程开门执行完毕");
        return _execute_status;
    }


    /**
     * 根据门名称（单个记录的原始数据）构建远程开门指令
     * <p>
     * N3000 -USER "abc" -PASSWORD "123" -Open  19a798b100001656E1D05E0000000000511BCD9800930000 <br/>
     * N3000 -USER "abc" -PASSWORD "123" -Open "m001-1号"
     * </p>
     *
     * @param username   用户名
     * @param password   密码
     * @param cmd        执行动作
     * @param doorNumber 门号
     * @return 指令字符串
     */
    private static String CmdBuilderByDoorName(String username, String password, String cmd, String doorNumber) {
        StringBuilder builder = new StringBuilder();
        builder.append("N3000 ");
        builder.append("-USER \"" + username + "\" ");
        builder.append("-PASSWORD \"" + password + "\" ");
        builder.append("-" + cmd + " ");
        builder.append("\"" + doorNumber + "\" ");
        return builder.toString();
    }

    /**
     * 根据产品序列号与控制器编号构建远程开门指令
     * <p>
     * N3000 -USER "abc" -PASSWORD "123" -Open  410123456 1
     * </p>
     *
     * @param username 用户名
     * @param password 密码
     * @param cmd      执行动作
     * @param sn       产品序列号
     * @param code     控制器编号
     * @return
     */
    private static String CmdBuilderBySNAndCtrlCode(String username, String password, String cmd, String sn, String code) {
        StringBuilder builder = new StringBuilder();
        builder.append("N3000 ");
        builder.append("-USER \"" + username + "\" ");
        builder.append("-PASSWORD \"" + password + "\" ");
        builder.append("-" + cmd + " ");
        builder.append(sn + " ");
        builder.append(code + " ");
        return builder.toString();
    }


    /**
     * 基于Netty实现的客户端处理器
     **/

    public class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            String command = CmdBuilderByDoorName(username, password, status.name(), doorNumber);
            System.out.println("----->客户端与服务端通道-开启：" + ctx.channel().localAddress() + "Channel Active");
            System.out.println("----->客户端准备发送的数据包：" + command);
            ctx.writeAndFlush(Unpooled.copiedBuffer(command, Charset.forName("gb2312")));
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
            //iRet=0, Failed
            System.out.println("----->thread.name=" + Thread.currentThread().getName());
            System.out.println("----->Server ChannelRead......");
            String _callback = msg.toString();
            System.out.println("----->" + ctx.channel().remoteAddress() + "Server Say  :" + _callback);
            if (_callback.endsWith(SERVER_SUCCESS_MSG)) {
                _execute_status = true;
            }
            ctx.disconnect();
            ctx.close();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("----->Throwable Begin");
            cause.printStackTrace();
            System.out.println("----->Throwable End");
            ctx.close();

        }
    }
}
