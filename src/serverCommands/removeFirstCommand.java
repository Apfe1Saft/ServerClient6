package serverCommands;

import collection.Collection;


public class removeFirstCommand {
    public removeFirstCommand( Collection collection)  {
        collection.getVector().removeElementAt(0);
    }
}
