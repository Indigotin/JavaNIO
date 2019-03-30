package Channel.FileChannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Len on 2019-03-30
 * 通道间数据传输
 */
public class FileChannelTest2 {

    public static void main(String args[]) throws IOException {

        RandomAccessFile file1 = new RandomAccessFile("D:/test.txt","rw");
        RandomAccessFile file2  = new RandomAccessFile("D:/OutTest.txt","rw");
        //创建通道
        FileChannel InChannel = file1.getChannel();
        FileChannel OutChannel = file2.getChannel();
        //通道间通信
        //OutChannel.transferFrom(InChannel, 0, InChannel.size());
        InChannel.transferTo(0,InChannel.size(),OutChannel);
        file1.close();
        file2.close();
    }
}
