package serverCommands;

import collection.Collection;
import  functions.*;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import data.*;
import collection.loader.*;

public class addCommand implements byteToString {
    public addCommand (  Collection collection, DatagramChannel s, SocketAddress a ) throws IOException {
        String D0 = "";
        double D1 = 0.0;
        int D2 = 0;
        long D3 = 0;
        int D4 = 0;
        String D5 = "";
        String D6 = null;
        long D7 = 0L;
        exceptionSender A = new exceptionSender();
        try {

            for (int i = 0; i < 8; i++) {
                ByteBuffer mess = ByteBuffer.wrap(new byte[100]);
                s.receive(mess);

                switch (i) {
                    case 0: {
                        A.setMessage("Что это такое : " + new String(mess.array()));
                        D0 = working(mess.array());
                    }
                    break;
                    case 1: {
                        A.setMessage("Ошибка при вводе значения - не является double: " + new String(mess.array()));
                        D1 = Double.parseDouble(working(mess.array()));

                    }
                    break;
                    case 2: {
                        A.setMessage("Ошибка при вводе значения  - не является int: " + new String(mess.array()) );
                        D2 = Integer.parseInt(working(mess.array()));
                    }
                    break;
                    case 3: {
                        A.setMessage("Ошибка при вводе значения  - не является long: " + new String(mess.array()));
                        D3 = Long.parseLong(working(mess.array()));
                    }
                    break;
                    case 4: {
                        A.setMessage("Ошибка при вводе значения - не является int: " + new String(mess.array()) + " ");
                        D4 = Integer.parseInt(working(mess.array()));
                    }
                    break;
                    case 5: {
                        D5 = working(mess.array());
                    }
                    break;
                    case 6: {
                        A.setMessage("Что это такое : " + new String(mess.array()));
                        D6 = working(mess.array());
                    }
                    break;
                    case 7: {
                        A.setMessage("Ошибка при вводе значения -  не является long: " + new String(mess.array()) );
                        D7 = Long.parseLong(working(mess.array()));
                    }
                    break;
                    default:
                        //До этого не дойдет
                }
                mess.clear();
            }
            //D0 = "Math";
            A.setMessage("ВВеденное значение difficulty не является Difficulty: "+ D5);
            Discipline discipline = new Discipline(D6, D7);
            Difficulty difficulty = StringtoEnum(D5);
            Coordinates coordinates = new Coordinates(D1, D2);
            collection.getVector().add(new LabWork(D0, collection.getMaxId(), coordinates, D3, D4, difficulty, discipline));
            collection.fileSaving();
            A.sender(0,s,a);
        }catch (Exception E) {
            System.out.println(A.message);
            A.sender(1,s,a);
        }
    }
    //Max Id double coordinates Long Integer difficulty discipline


    public addCommand() {
        //NOTHING
    }


    public Difficulty StringtoEnum(String line) throws DifficultyExeption {
        Difficulty difficulty ;
        if(line.startsWith("VERY_EASY") && line.length()<="VERY_EASY".length())difficulty = Difficulty.VERY_EASY;
        else
        if(line.startsWith("NORMAL"))difficulty = Difficulty.NORMAL;
        else
        if(line.startsWith("TERRIBLE"))difficulty = Difficulty.TERRIBLE;
        else
        if(line.startsWith("IMPOSSIBLE"))difficulty = Difficulty.IMPOSSIBLE;
        else
        if(line.startsWith("INSANE"))difficulty = Difficulty.INSANE;
        else throw new DifficultyExeption();
        return difficulty;
    }
}
// Комментарии

