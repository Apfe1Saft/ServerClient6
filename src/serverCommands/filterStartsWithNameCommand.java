

package serverCommands;

import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import collection.Collection;
import data.*;
public class filterStartsWithNameCommand implements byteToString {
    public filterStartsWithNameCommand(DatagramChannel s, SocketAddress a, Collection collection) throws IOException {
        ByteBuffer mess = ByteBuffer.wrap(new byte[100]);
        s.receive(mess);
        String name = working(mess.array());
        int k = 0;
        for (LabWork i : collection.getVector()) {
            if (i.getName().equals(name)) k++;
        }
        byte[] request1 = ("k:" + k).getBytes();
        mess = ByteBuffer.wrap(request1);
        s.send(mess,a);
        for (LabWork i : collection.getVector()) {
            if (i.getName().equals(name)) {
                byte[] message = i.toString().getBytes();
                mess = ByteBuffer.wrap(message);
                s.send(mess,a);
            }
        }
    }
}


