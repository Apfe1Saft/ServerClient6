package serverCommands;

import collection.*;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class clearCommand {
    public clearCommand ( Collection collection, DatagramChannel s, SocketAddress a) throws IOException {
        exceptionSender A = new exceptionSender();
        try {
            A.setMessage("Не удалось отчистить файл, проверьте, доступен/существует ли он");
            collection.getVector().clear();
            collection.fileSaving();
            A.sender(0,s,a);
        }catch (Exception e){
            A.sender(1,s,a);
        }
    }
}
