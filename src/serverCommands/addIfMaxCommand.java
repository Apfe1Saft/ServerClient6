package serverCommands;

import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import collection.*;
import data.Coordinates;
import data.Difficulty;
import data.Discipline;
import data.LabWork;
public class addIfMaxCommand implements byteToString {
    public addIfMaxCommand(  Collection collection, DatagramChannel s, SocketAddress a) throws IOException {
        //Name
        String D0 = "";
        //coordinates
        double D1 = 0.0;
        int D2 = 0;
        //minimalPoint
        long D3 = 0;
        //maximumPoint
        int D4 = 0;
        //Difficulty
        String D5 = "";
        //Discipline
        String D6 = null;
        long D7 = 0L;
        //LabWork(D0,coordinates,D3,D4,difficulty,discipline)
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
                A.setMessage("ВВеденное значение difficulty не является Difficulty: "+ D5);
                Discipline discipline = new Discipline(D6, D7);
                Difficulty difficulty = new addCommand().StringtoEnum(D5);
                Coordinates coordinates = new Coordinates(D1, D2);
                int Max = 0;
                for (LabWork i : collection.getVector()) {
                    if (Max < i.getMaximumPoint()) {
                        Max = i.getMaximumPoint();
                    }
                }
                if (D4 > Max) {
                    collection.getVector().add(new LabWork(D0, collection.getMaxId(), coordinates, D3, D4, difficulty, discipline));
                    collection.fileSaving();
                }

            A.sender(0,s,a);
        }catch(Exception e){
            A.sender(1,s,a);
        }
    }
}
