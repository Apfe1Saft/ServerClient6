package serverCommands;

import collection.Collection;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class infoCommand {
    public infoCommand ( Collection collection, DatagramChannel s, SocketAddress a) throws IOException {
        String line = "Type : Vector; \n"+"    Initialization date : "+collection.getVectorDate()+"\n"+
                "Elements :"+ collection.getVector().size()+"\n"+
                "Type: LabWork;\n"+
                "LabWork's data:" +
                "    private Long id; \n" +
                "    private String name; \n" +
                "    private Coordinates coordinates; \n" +
                "    private java.time.LocalDate creationDate; \n" +
                "    private Long minimalPoint; \n" +
                "    private Integer maximumPoint; \n" +
                "    private Difficulty difficulty; \n" +
                "    private Discipline discipline; \n" +
                "LabWork's Methods :"+
                "     toString();\n" +
                "     LabWork();\n" +
                "     getId();\n" +
                "     getName();\n" +
                "     getCoordinates();\n" +
                "     getMinimalPoint(); \n" +
                "     getMaximumPoint(); \n" +
                "     getCreationDate();\n" +
                "     getDifficulty(); \n" +
                "     getDiscipline(); \n"+
                "Type: Difficulty;\n"+
                "     VERY_EASY;\n"+
                "     NORMAL;\n"+
                "     IMPOSSIBLE;\n"+
                "     INSANE;\n"+
                "     TERRIBLE;";
        byte[] request = line.getBytes();
        ByteBuffer mess = ByteBuffer.wrap(request);
        s.send(mess,a);
    }
}
