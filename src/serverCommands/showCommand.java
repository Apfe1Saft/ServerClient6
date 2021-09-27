package serverCommands;

import collection.Collection;
import data.LabWork;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class showCommand {
    public showCommand(Collection collection, DatagramChannel s, SocketAddress a) throws IOException {
        String line = "VectorDate:" + collection.getVectorDate()+"\nCollection{";

        //1
        byte[] request = line.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(request);
        s.send(mess, a);

        //2
        line = "vector{\n";
        request = line.getBytes();
        mess = ByteBuffer.wrap(request);
        s.send(mess, a);

        int k =0;
        for(LabWork ignored : collection.getVector())k++;
        line = String.valueOf(k);
        request = line.getBytes();
        mess = ByteBuffer.wrap(request);
        s.send(mess, a);

        for(LabWork i :collection.getVector()) {
            System.out.println(i.toString());
            line = i.toString().replace("[", "{").replace("]", "}") + "\n";
            request = line.getBytes();
            mess = ByteBuffer.wrap(request);
            s.send(mess, a);
        }

        //3
        line = "}\nfileName = "+collection.getFileName()+", VectorDate = " + collection.getVectorDate().toString() + ", MaxId = "+ 0+"}";
        request = line.getBytes();
        mess = ByteBuffer.wrap(request);
        s.send(mess, a);
    }
}