package Channel.ServerSocketChannel;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Len on 2019-03-30
 * ava NIO中的 ServerSocketChannel
 * 是一个可以监听新进来的TCP连接的通道
 * 就像标准IO中的ServerSocket一样
 */
public class ServerSocketChannelTest1 {
    public static void main(String args[]) throws IOException {

        //打开 ServerSocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();
        //监听新进来的连接 通常不会仅仅只监听一个连接
        //在while循环中调用 accept()方法
        while(true){
            SocketChannel socketChannel = channel.accept();
            //do something with socketChannel...
            if(false){//test
                break;
            }
        }

        //非阻塞模式
        //ServerSocketChannel可以设置成非阻塞模式
        // 在非阻塞模式下，accept() 方法会立刻返回
        // 如果还没有新进来的连接,返回的将是null
        channel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = channel.accept();
            if(socketChannel != null){
                //do something with socketChannel...
            }
            if(false){//test
                break;
            }
        }

        //关闭 ServerSocketChannel
        channel.close();
    }
}
