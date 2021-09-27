package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_by_id
2. Элемент String id
 */
public class removeByIdCommand {
    public removeByIdCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String command = "remove_by_id";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        String id ;
        id = in.next();
        message = id.getBytes();
        mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        exceptionCatcher E = new exceptionCatcher();
        if(E.checker(s)){
            E.message();
        }
    }
}
//remove_by_id