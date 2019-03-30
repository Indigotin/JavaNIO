package Channel.SocketChannel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Len on 2019-03-30
 * Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道
 */
public class SocketChannelTest1 {
    public static void main(String args[]) throws IOException {

        //创建SocketChannel
        //1、打开一个SocketChannel并连接到互联网上的某台服务器。
        //2、一个新连接到达ServerSocketChannel时 会创建一个SocketChannel

        //打开 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        //socketChannel.connect(new InetSocketAddress("http://baidu.com", 80));

        //从 SocketChannel 读取数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);

        //写入 SocketChannel
        String newData = "New String to write..." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        //Write()方法无法保证能写多少字节到SocketChannel
        //我们重复调用write()直到Buffer没有要写的字节为止
        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }

        //非阻塞模式
        //设置 SocketChannel 为非阻塞模式（non-blocking mode）
        //设置之后 就可以在异步模式下调用connect()  read() 和write()
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://baidu.com", 80));
        //connect()
        while(! socketChannel.finishConnect() ){
            //wait, or do something else...
        }
        //write()
        //非阻塞模式下 可能尚未写出任何内容时可能就返回了 所以需要在循环中调用write()
        while(buf.hasRemaining()) {
            socketChannel.write(buf);
        }
        //read()
        //非阻塞模式下 read()方法在尚未读取到任何数据时可能就返回了
        //所以需要关注它的int返回值 它会告诉你读取了多少字节
        while(socketChannel.read(buf)!=-1){
            //do something ...
        }
        //非阻塞模式与选择器搭配会工作的更好
        //通过将一或多个SocketChannel注册到Selector 可以询问选择器哪个通道已经准备好了读取 写入等

        //关闭 SocketChannel
        socketChannel.close();
    }
}
