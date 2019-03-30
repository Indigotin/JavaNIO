package Channel.FileChannel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Len on 2019-03-30
 * FileChannel是一个连接到文件的通道。可以通过文件通道读写文件
 * FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下
 */
public class FileChannelTest3 {
    public static void main(String args[]) throws IOException {

        RandomAccessFile file = new RandomAccessFile("D:/OutTest.txt","rw");
        //创建通道
        FileChannel fileChannel = file.getChannel();
        //从FileChannel读取数据
        ByteBuffer buf = ByteBuffer.allocate(48);
        //int bytesRead = fileChannel.read(buf);
        //.....
        //从FileChannel写数据
        String data = "FileChannelTest  "+System.currentTimeMillis();
        buf.put(data.getBytes());
        buf.flip();
        //FileChannel.write()是在while循环中调用的
        // 因为无法保证write()方法一次能向FileChannel写入多少字节
        while(buf.hasRemaining()) {
            fileChannel.write(buf);
        }
        fileChannel.close();
        //补充
        //FileChannel的position
        long pos = fileChannel.position();
        fileChannel.position(pos+12);
        //FileChannel的size方法 将返回该实例所关联文件的大小
        long fileSize = fileChannel.size();
        //FileChannel的truncate方法
        //截取一个文件 截取文件时 文件将中指定长度后面的部分将被删除
        fileChannel.truncate(36);
        //FileChannel的force方法 将通道里尚未写入磁盘的数据强制写到磁盘上
        //操作系统会将数据缓存在内存中 所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上
        //要保证这一点 需要调用force()方法
        //force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上
        fileChannel.force(true);

        file.close();
    }
}
