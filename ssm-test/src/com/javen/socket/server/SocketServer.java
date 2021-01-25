package com.javen.socket.server;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8082);
        while (true) {
            System.out.println("等待连接");
            //阻塞方法
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了");
            handler(socket);

        }
    }

    private static void handler(Socket socket) throws Exception{
        byte[] bytes = new byte[1024];
        System.out.println("准备接受数据");
        //接受客户端的数据，阻塞方法，没有数据可以读的时候就阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("接受完数据");
        if(read != -1) {
            System.out.println("接受到的数据\n" + new String(bytes, 0, read));
        }
        socket.getOutputStream().write("\nhello i am server".getBytes());
        socket.getOutputStream().flush();
    }
}
