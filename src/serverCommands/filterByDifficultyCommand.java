package serverCommands;

import collection.Collection;
import collection.loader.DifficultyExeption;
import data.LabWork;
import functions.*;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

import data.*;

public class filterByDifficultyCommand implements byteToString {
    public filterByDifficultyCommand(){}
    public filterByDifficultyCommand(DatagramChannel s, SocketAddress a, Collection collection, byte[] buffer) throws IOException {
        exceptionSender A = new exceptionSender();
        try {
            ByteBuffer mess = ByteBuffer.wrap(buffer);
            s.receive(mess);
            A.setMessage("ВВеденное значение difficulty не является Enum:" + new String(mess.array()));
            Difficulty difficulty = StringtoEnum(working(mess.array()));
            int k = 0;
            if (difficulty != null) {
                for (LabWork i : collection.getVector()) {
                    if (difficulty.equals(i.getDifficulty())) {
                        k++;
                    }
                }
            }
            A.sender(0,s,a);
            byte[] request1 = String.valueOf(k).getBytes(StandardCharsets.UTF_8);
            mess = ByteBuffer.wrap(request1);
            if (difficulty != null) {
                s.send(mess,a);
                for (LabWork i : collection.getVector()) {
                    if (difficulty.equals(i.getDifficulty())) {
                        byte[] message = i.toString().getBytes();
                        mess = ByteBuffer.wrap(message);
                        s.send(mess,a);
                    }
                }
            }
        }catch (Exception E) {
            A.sender(1,s,a);
        }
    }
    public Difficulty StringtoEnum(String line) throws DifficultyExeption {
        Difficulty difficulty ;
        if (line.startsWith("VERY_EASY") & line.length()=="VERY_EASY".length()) difficulty = Difficulty.VERY_EASY;
        else
        if (line.startsWith("NORMAL")& line.length()=="NORMAL".length()){ difficulty = Difficulty.NORMAL;}
        else
        if (line.startsWith("IMPOSSIBLE")& line.length()=="IMPOSSIBLE".length()) difficulty = Difficulty.IMPOSSIBLE;
        else
        if (line.startsWith("INSANE")& line.length()=="INSANE".length()) difficulty = Difficulty.INSANE;
        else
        if (line.startsWith("TERRIBLE")& line.length()=="TERRIBLE".length()) difficulty = Difficulty.TERRIBLE;
        else throw new DifficultyExeption();

        return difficulty;

    }
}