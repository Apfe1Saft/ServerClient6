package clientCommands;

import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда help
 */
public class helpCommand implements byteToString{
    public helpCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "help";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        // создаём дейтаграмму, длина которой определяется длиной сообщения
        // передаём серверу
        s.send(mess,a);
        byte[] buffer = new byte[1000*1000];
        mess = ByteBuffer.wrap(buffer);System.out.println(new String(mess.array()));
        s.receive(mess);
        message = mess.array();
        System.out.println(working(message));

    }
}