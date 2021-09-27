package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import functions.*;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда filter_by_difficulty
2. Элемент Difficulty difficulty
 */
public class filterByDifficultyCommand implements byteToString{
    public filterByDifficultyCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String difficulty = "filter_by_difficulty";
        byte[] message = difficulty.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        difficulty = in.next();
        message = difficulty.getBytes();
        mess = ByteBuffer.wrap(message);
        s.send(mess,a);
        exceptionCatcher E = new exceptionCatcher();
        if(E.checker(s)){
            E.message();
        }
        else {
            do {
                mess = ByteBuffer.wrap(new byte[250]);
                s.receive(mess);
            }while(working(mess.array()).length()!=1);
           // System.out.println(byteToString(mess.array()));
            int k = Integer.parseInt(E.working(mess.array()));
            for (int i = 0; i < k; ++i) {
                do {
                    mess = ByteBuffer.wrap(new byte[250]);
                    s.receive(mess);

                }while(!working(mess.array()).startsWith("LabWork"));
                System.out.println(working(mess.array()));
            }
        }
    }
}
//filter_by_difficulty NORMAL