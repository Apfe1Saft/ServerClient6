package clientCommands;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
/*
Краткая информация:
На сервер передается несколько пакетов:
1. Команда remove_all_by_difficulty
2. Элемент Difficulty difficulty
 */
public class removeAllByDifficultyCommand {
    public removeAllByDifficultyCommand(DatagramChannel s, SocketAddress a, Scanner in) throws IOException {
        String difficulty = "remove_all_by_difficulty";
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

    }
}
//remove_all_by_difficulty VERY_EASY