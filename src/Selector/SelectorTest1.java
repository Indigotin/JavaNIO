package Selector;


import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Len on 2019-03-30
 * 与Selector一起使用时，Channel必须处于非阻塞模式下
 * 这意味着不能将FileChannel与Selector一起使用
 * 因为FileChannel不能切换到非阻塞模式
 * 而套接字通道都可以
 */
public class SelectorTest1 {

    public static void main(String args[]) throws IOException {

        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        //非阻塞模式
        socketChannel.configureBlocking(false);
        //将通道注册到Selector
        //Selector监听Channel时对什么事件感兴趣(Connect,Accept,Read,Write)
        SelectionKey key = socketChannel.register(selector,SelectionKey.OP_READ);
        //SelectionKey读写interest集合
        int interestSet = key.interestOps();
        //SelectionKey对象的属性(interest集合 ready集合 Channel Selector)
        /*boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        int isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
        int isInterestedInRead    = interestSet & SelectionKey.OP_READ;
        int isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE;*/
        //访问ready集合
        //int readySet = key.readyOps();
        //检测channel中什么事件或操作已经就绪
        /*boolean isAcceptable = key.isAcceptable();
        key.isConnectable();
        key.isReadable();
        key.isWritable();*/
        //Channel + Selector
        /*Channel Channel  = key.channel();
        Selector Selector = key.selector();*/
        //可以将一个对象或者更多信息附着到SelectionKey上
        //1
        /*Object theObject = new String();
        key.attach(theObject);
        Object attachedObj = key.attachment();*/
        //2
        //SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ, theObject);

        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;
            Set selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> keys = selectKeys.iterator();
            while(keys.hasNext()){
                SelectionKey k = keys.next();
                if(k.isAcceptable()){
                    //
                }else if(k.isConnectable()){
                    //
                }else if(k.isReadable()){
                    //
                }else if(k.isWritable()){
                    //
                }
                //处理完通道时自己移除 下次该通道变成就绪时 Selector会再次将其放入已选择键集中
                keys.remove();
            }
        }

    }
}
