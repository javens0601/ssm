package com.javen.socket.nioserver;

import jdk.management.resource.internal.inst.ServerSocketChannelImplRMHooks;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NIOSocketServer {
    public static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(8082));
        //设置serversocketchannel为非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("system start success");

        while (true) {
            //非阻塞模式accept方法不会阻塞, NIO的非阻塞是由操作系统内部实现的，底层调用了操作系统的accept函数
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {//有客户端连接
                System.out.println("连接成功");
                //设置socketChannel为非阻塞
                socketChannel.configureBlocking(false);
                //保存客户端在list中
                channelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterable = channelList.iterator();
            while (iterable.hasNext()) {
                SocketChannel socketChannel1 = iterable.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                //非阻塞模式read方法不会阻塞
                int len = socketChannel1.read(byteBuffer);
                if (len > 0) {
                    System.out.println("接受到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    iterable.remove();
                    System.out.println("删除连接");
                }
            }
        }
    }
}
