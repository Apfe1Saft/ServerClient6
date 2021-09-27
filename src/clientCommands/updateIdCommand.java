package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда update_id
2.- 8. Элементы команды update_id
 */
public class updateIdCommand {
    public updateIdCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String command = "update";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        command = in.next();
        message = command.getBytes();
        mess.clear();
        mess = ByteBuffer.wrap(message);
        s.send(mess,a);
            for (int i = 0; i < 8; i++) {
                command = in.next();
                message = command.getBytes();
                mess.clear();
                mess = ByteBuffer.wrap(message);
                s.send(mess,a);
        }
        exceptionCatcher E = new exceptionCatcher();
        if(E.checker(s)){
            System.out.println("ERROR");
            E.message();
        }
    }
}
//update 1 Math 123.4 5 6 7 VERY_EASY MATH 8