package clientCommands;

import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда filter_by_difficulty
2. Элемент String name
 */
public class filterStartsWithNameCommand implements byteToString {
    public filterStartsWithNameCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String command = "filter_starts_with_name";
        byte[] message = command.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(message);
        // создаём дейтаграмму, длина которой определяется длиной сообщения
        // передаём серверу
        s.send(mess,a);
        command = in.next();
        mess = ByteBuffer.wrap(command.getBytes(StandardCharsets.UTF_8));
        s.send(mess,a);
        do {
            mess = ByteBuffer.wrap(new byte[100]);
            s.receive(mess);
        }while(!working(mess.array()).startsWith("k:"));

        int k = Integer.parseInt(working(mess.array()).replace("k:",""));
        for (;k>0;k--){
            do {
                mess = ByteBuffer.wrap(new byte[250]);
                s.receive(mess);
                //System.out.println(working(mess.array()));
                System.out.println(working(mess.array()));
            }while(!working(mess.array()).startsWith("LabWork"));
        }

    }
}
//filter_starts_with_name Math