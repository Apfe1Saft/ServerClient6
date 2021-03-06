package collection.writer;

import data.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Vector;

/**
 * {@param class which write vector to File by PrintWriter
 */
public class Writer implements Writing{

    @Override
    public void writing(String file, Vector<LabWork> vector) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("<?xml version=\"1.0\"?>");

            writer.println("\t<VectorDate>" + LocalDate.now() + "</VectorDate>");
            writer.println("\t<LabWork>");
            for (Object o : vector) {
                LabWork a = (LabWork) o;
                writer.println("\t\t<ElementNumber" + a.getId() + ">");
                writer.println("\t\t\t<id>" + a.getId() + "</id>");
                writer.println("\t\t\t<name>" + a.getName() + "</name>");
                writer.println("\t\t\t<coordinates>\n\t\t\t\t<x>" + a.getCoordinates().getX() + "</x>\n\t\t\t\t<y>" +
                        a.getCoordinates().getY() + "</y>\n" + "\t\t\t</coordinates>");
                writer.println("\t\t\t<creationDate>" + a.getCreationDate() + "</creationDate>");
                writer.println("\t\t\t<minimalPoint>" + a.getMinimalPoint() + "</minimalPoint>");
                writer.println("\t\t\t<maximumPoint>" + a.getMaximumPoint() + "</maximumPoint>");
                writer.println("\t\t\t<difficulty>" + a.getDifficulty() + "</difficulty>");
                writer.println("\t\t\t<discipline>\n" + "\t\t\t\t<practiceHours>" + a.getDiscipline().getPracticeHours() + "</practiceHours>\n"
                        + "\t\t\t\t<name>" + a.getDiscipline().getName() + "</name>\n" + "\t\t\t</discipline>");
                writer.println("\t\t</ElementNumber" + a.getId() + ">");


            }
            writer.println("\t</LabWork>");
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Файл для записи data.xml не обнаружен и был создан");
        }
    }


}
