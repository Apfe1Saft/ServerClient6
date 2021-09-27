package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда clear
 */
public class clearCommand {
    public  clearCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "clear";
        byte[] message = command.getBytes();
        // создаём дейтаграмму, длина которой определяется длиной сообщения
        ByteBuffer mess = ByteBuffer.wrap(message);
        // передаём серверу
        s.send(mess,a);
        exceptionCatcher E = new exceptionCatcher();
        if(E.checker(s)){
            E.message();
        }
    }

}
//clear
//+