package clientCommands;

import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
На сервер передается несколКраткая информация:
ько пакетов:
1. Команда show
 */
public class showCommand implements byteToString {
    public showCommand(DatagramChannel s, SocketAddress a) throws IOException {
        String command = "show";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);

        //1
        do {
        mess = ByteBuffer.wrap(new byte[100]);
        s.receive(mess);
        message = mess.array();
        }while(!new String(message).startsWith("Vector"));
        System.out.println(new String(message));

        //2
        do {
            mess = ByteBuffer.wrap(new byte[10]);
            s.receive(mess);
            message = mess.array();
        }while(!working(message).startsWith("vector"));
        System.out.println(new String(message).replace("\n",""));

        do {
            mess = ByteBuffer.wrap(new byte[100]);
            s.receive(mess);
            message = mess.array();
        }while((working(message).length()<1));
        int k = Integer.parseInt(working(message));
        for(;k>0;--k){
            do {
                mess = ByteBuffer.wrap(new byte[300]);
                s.receive(mess);
                message = mess.array();

            }while(!working(message).startsWith("LabWork"));
            System.out.println(new String(message).replace("\n",""));
        }

        //3
        do {
            mess = ByteBuffer.wrap(new byte[100]);
            s.receive(mess);
            message = mess.array();
        }while(!working(message).startsWith("}\nfileName"));
        System.out.println(new String(message));
    }
}