package Buffer;

import java.nio.CharBuffer;

/**
 * Created by Len on 2019-04-04
 */
public class BufferFillDrain {

    private static int index = 0;
    private static String [] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly",
    };

    public static void main(String[] args){
        CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)){
            buffer.flip(  );
            drainBuffer (buffer);
            buffer.clear(  );
        }
    }

    private static void drainBuffer (CharBuffer buffer)  {
        while (buffer.hasRemaining(  )) {
            System.out.print (buffer.get(  ));
        }
        System.out.println ("");
    }

    private static boolean fillBuffer (CharBuffer buffer) {
        if (index >= strings.length) {
            return (false);
        }
        String string = strings [index++];
        for (int i = 0; i < string.length(  ); i++) {
            buffer.put (string.charAt (i));
        }
        return (true);
    }

}
