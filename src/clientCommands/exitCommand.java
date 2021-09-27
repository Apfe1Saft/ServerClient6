package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class exitCommand {
    public exitCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "exit";
        byte[] message = command.getBytes();
        // создаём дейтаграмму, длина которой определяется длиной сообщения
        ByteBuffer mess = ByteBuffer.wrap(message);
        // передаём серверу
        s.send(mess,a);
    }
}
