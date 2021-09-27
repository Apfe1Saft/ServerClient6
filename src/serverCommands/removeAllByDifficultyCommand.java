package serverCommands;

import collection.Collection;
import data.Difficulty;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import functions.*;

public class removeAllByDifficultyCommand implements byteToString{
    public removeAllByDifficultyCommand(DatagramChannel s, SocketAddress a,  Collection collection, byte[] buffer) throws IOException {
        ByteBuffer mess = ByteBuffer.wrap(buffer);
        s.receive(mess);
        System.out.println(working(mess.array()));
        String line = working(mess.array());
        exceptionSender A = new exceptionSender();
        try {
            A.setMessage("ВВеденное значение difficulty не является Enum:"+line);
            Difficulty difficulty = new filterByDifficultyCommand().StringtoEnum(line);
            collection.getVector().removeIf(i -> difficulty.equals(i.getDifficulty()));
            A.sender(0,s,a);
        }catch (Exception E) {
            A.sender(1,s,a);
        }
    }
}
