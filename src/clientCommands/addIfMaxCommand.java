package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда add_if_max
Затем создается команда add с элементами add_if_max
 */
public class addIfMaxCommand{
    public addIfMaxCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String command = "add_if_max";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        mess.clear();
        for( int i = 0 ; i <8 ; i++){
            command = in.next();
            message = command.getBytes();
            mess = ByteBuffer.wrap(message);
            s.send(mess,a);
        }
        exceptionCatcher E = new exceptionCatcher();
        if(E. checker(s)){
            E.message();
        }
    }
}
//add_if_max Math 5.0 5 5 5 VERY_EASY MATH 8