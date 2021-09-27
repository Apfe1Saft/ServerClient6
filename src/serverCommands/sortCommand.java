package serverCommands;

import collection.Collection;
import java.util.Comparator;
import data.*;

public class sortCommand {
    public sortCommand ( Collection collection)  {
        collection.getVector().sort(new MaximumPointComparator());
    }
}
class MaximumPointComparator  implements Comparator<LabWork> {
    public int compare(LabWork labWork1, LabWork labWork2){
        if(labWork1.getMaximumPoint().equals(labWork2.getMaximumPoint())) return 0;
        if(labWork1.getMaximumPoint() > labWork2.getMaximumPoint()) return 1;
        else  return -1;
    }
}