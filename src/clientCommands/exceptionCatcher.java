package clientCommands;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import functions.*;

public class exceptionCatcher implements byteToString {
    private String message = "";//Сообщение об ошибке
    public exceptionCatcher(){}
    public boolean checker(DatagramChannel s)  throws IOException  {
        String line ;
        ByteBuffer mess ;
        do {
            mess = ByteBuffer.wrap(new byte[1000*100]);
            s.receive(mess);
            line = working(mess.array());

        }while(line.length()!=1);
        //System.out.println("-> "+line);
        if(line.startsWith("1")){
            do {
                mess = ByteBuffer.wrap(new byte[100]);
                s.receive(mess);
                message = new String(mess.array());
            }while(!message.startsWith("!"));
            message = message.replace("!","");
            return true;
        }
        return false;
    }

    public void message() {
        System.out.println(message);
    }
}
//+