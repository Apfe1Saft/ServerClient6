package serverCommands;

import collection.Collection;
import data.Coordinates;
import data.Difficulty;
import data.Discipline;
import data.LabWork;
import java.io.IOException;
import functions.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class updateIdCommand implements byteToString {
    public updateIdCommand(DatagramChannel s, SocketAddress a, Collection collection, byte[] buffer) throws IOException {
        ByteBuffer mess = ByteBuffer.wrap(buffer);
        s.receive(mess);
        exceptionSender A = new exceptionSender();
        try {
            A.setMessage("ВВеденное значение id не является long");
            String kString = working(mess.array());
            Long k = Long.parseLong(kString);
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
            String D5 = null;
            //Discipline
            String D6 = null;
            long D7 = 0L;
            //LabWork(D0,coordinates,D3,D4,difficulty,discipline)

            for (int i = 0; i < 8; i++) {
                mess = ByteBuffer.wrap(buffer);
                s.receive(mess);
                buffer = new byte[1000];
                switch (i) {
//Long id, String name, Coordinates coordinates, LocalDate creationDate, Long minimalPoint,
// Integer maximumPoint, Difficulty difficulty, Discipline discipline
                    case 0: {
                        D0 = working(mess.array());
                    }
                    break;
                    case 1: {
                        buffer = new byte[1000];
                        A.setMessage("Ошибка при вводе значения -  не является double: " + new String(mess.array()));
                        D1 = Double.parseDouble(working(mess.array()));
                    }
                    break;
                    case 2: {
                        buffer = new byte[1000];
                        A.setMessage("Ошибка при вводе значения -  не является int: " + new String(mess.array()));
                        D2 = Integer.parseInt(working(mess.array()));
                    }
                    break;
                    case 3: {
                        buffer = new byte[1000];
                        A.setMessage("Ошибка при вводе значения -  не является long: " + new String(mess.array()));
                        D3 = Long.parseLong(working(mess.array()));
                    }
                    break;
                    case 4: {
                        buffer = new byte[1000];
                        A.setMessage("Ошибка при вводе значения -  не является int: " + new String(mess.array()));
                        D4 = Integer.parseInt(working(mess.array()));
                    }
                    break;
                    case 5: {
                        D5 = working(mess.array());
                    }
                    break;
                    case 6: {
                        D6 = working(mess.array());
                    }
                    break;
                    case 7: {
                        buffer = new byte[1000];
                        A.setMessage("Ошибка при вводе значения -  не является long: " + new String(mess.array()));
                        D7 = Long.parseLong(working(mess.array()));
                    }
                    break;
                    default:
                        throw new IllegalStateException("Невозможно: " + i + 1);
                }
            }
            A.setMessage("ВВеденное значение difficulty не является Enum: " + D5);
            Discipline discipline = new Discipline(D6, D7);
            Difficulty difficulty = new addCommand().StringtoEnum(D5);
            Coordinates coordinates = new Coordinates(D1, D2);
            for (LabWork i : collection.getVector()) {
                if (k.equals(i.getId())) {
                    LabWork labWork = new LabWork(D0, collection.getMaxId(), coordinates, D3, D4, difficulty, discipline);
                    i.setCoordinates(labWork.getCoordinates());
                    i.setMaximumPoint(labWork.getMaximumPoint());
                    i.setCreationDate(labWork.getCreationDate());
                    i.setDifficulty(labWork.getDifficulty());
                    i.setDiscipline(labWork.getDiscipline());
                    i.setMinimalPoint(labWork.getMinimalPoint());
                    i.setName(labWork.getName());
                }
            }
            A.sender(0, s, a);
        } catch (Exception E) {
            A.sender(1, s, a);
        }
    }
}
