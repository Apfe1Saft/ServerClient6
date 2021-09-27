package serverCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class exceptionSender {
    String message  = "";
    public void sender(int flag, DatagramChannel s, SocketAddress a) throws IOException {
        byte[] request;
        request = String.valueOf(flag).getBytes();
        ByteBuffer mess = ByteBuffer.wrap(request);
        s.send(mess,a);
        if(flag == 1){
            request = message.getBytes();
            mess = ByteBuffer.wrap(request);
            s.send(mess,a);

        }
    }

    public void setMessage(String message) {
        this.message ="!" + message;
    }
}
