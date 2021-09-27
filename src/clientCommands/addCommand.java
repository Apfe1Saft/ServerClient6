package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда add
2. - 8. Элементы команды add
 */
public class addCommand {
    public addCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String command = "add";
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
//add Math 0.0 0 0 0 VERY_EASY MATH 8
//+