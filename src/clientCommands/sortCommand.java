package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда sort
 */
public class sortCommand {
    public sortCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "sort";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);
    }
}
