package com.javen.socket.nioserver;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NIOSocketServerSelector {
    public static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(8082));
        //设置serversocketchannel为非阻塞
        serverSocket.configureBlocking(false);
        //打开selector处理channel，即创建epoll
        Selector selector = Selector.open();
        //把serversocketchannel注册到selector上，并且selector对客户端accept连接操作感兴趣
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("system start success");

        while (true) {
            //阻塞等待需要处理的事件发生
            selector.select(1000);

            //获取selector中注册的全部事件的selectorkey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //遍历selectorkeys进行处理

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel socket = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = socket.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                } else if (key.isReadable()) {
                 SocketChannel socketChannel = (SocketChannel) key.channel();
                 ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                 int len = socketChannel.read(byteBuffer);

                 if (len > 0) {
                     System.out.println("收到消息：" + new String(byteBuffer.array()));
                 } else if (len == -1) {
                     System.out.println("关闭连接");
                     socketChannel.close();
                 }

                }
                //从事件集合里删除本次处理的key，防止下次select重复处理
                iterator.remove();
            }
        }
    }
}
