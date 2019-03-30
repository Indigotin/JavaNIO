package Channel.FileChannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Len on 2019-03-30
 */
public class FileChannelTest1 {

    public static void main(String args[]) throws IOException {

        RandomAccessFile file  = new RandomAccessFile("D:/test.txt","rw");
        //创建通道
        FileChannel fileChannel = file.getChannel();
        //创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        //读取数据
        int readBuff = fileChannel.read(byteBuffer);
        while(readBuff != -1){
            System.out.println("Read : "+readBuff);
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get());
            }
            System.out.println();
            byteBuffer.clear();
            readBuff = fileChannel.read(byteBuffer);
        }
        file.close();
    }
}
