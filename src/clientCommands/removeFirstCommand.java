package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_first
 */
public class removeFirstCommand {
    public removeFirstCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "remove_first";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);

    }
}
// remove_first
