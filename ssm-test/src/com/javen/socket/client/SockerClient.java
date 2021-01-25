package com.javen.socket.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SockerClient {
    public static void main(String[] args) throws Exception{
        Socket s = new Socket("127.0.0.1",8082);//1,建立socket服务.指定要连接主机和端口.
        OutputStream out = s.getOutputStream();//2,获取socket流中的输出流.将数据写到该流中.通过网络发送给 服务端.
        out.write("hello , i am  client".getBytes());//getBytes（）是将一个字符串转化为一个字节数组 //write(byte[] b) //将 b.length 个字节从指定的 byte 数组写入此输出流。
        InputStream in = s.getInputStream();//3,获取socket流中的输入流,将服务端 反馈的数据 获取到,并打印.
        byte[] buf = new byte[1024];
        int len = in.read(buf);//int read(byte[] b) // 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。 /*参数：b - 存储读入数据的缓冲区。 返回：读入缓冲区的总字节数；如果因为已经到达流末尾而不再有数据可用，则返回 -1。是阻塞式方法*/
        System.out.println(new String(buf,0,len)); //String(byte[] bytes, int offset, int length) //通过使用平台的默认字符集解码指定的 byte 子数组，构造一个新的 String。 s.close();

    }

    private static void recive(Socket socket) throws Exception{
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        // 发送数据
        printWriter.println("hello , i am  client");
        // 刷新一下，使得服务器立马可以收到请求信息
        printWriter.flush();
        printWriter.close();
        socket.close();
    }
}
