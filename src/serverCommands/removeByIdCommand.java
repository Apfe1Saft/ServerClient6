package serverCommands;

import collection.Collection;
import java.io.IOException;

import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class removeByIdCommand implements byteToString{
    public removeByIdCommand(DatagramChannel s, SocketAddress a, byte[] buffer, Collection collection) throws IOException {
        ByteBuffer mess = ByteBuffer.wrap(buffer);
        s.receive(mess);
        String line = working(mess.array());
        exceptionSender A = new exceptionSender();
        try {
            A.setMessage("Неправильное введенное значение id (long):"+line);
            Long k = Long.parseLong(line);
            collection.getVector().removeIf(i -> k.equals(i.getId()));
            A.sender(0,s,a);
        }catch (Exception E) {
            A.sender(1,s,a);
        }
    }
}
