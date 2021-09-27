package client;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import clientCommands.*;

public class CommandReader {
    public CommandReader(){}
    public void work(String command, DatagramChannel s, SocketAddress a, Scanner in, int way ) throws IOException, InterruptedException {
        switch (command){
            case"exit":{new exitCommand(s,a);}break;
            case"help":{new helpCommand( s,a);}break;
            case"show":{new showCommand( s,a);}break;
            case"info":{new infoCommand( s,a);}break;
            case"clear":{new clearCommand( s,a);}break;
            case"sort":{new sortCommand(s,a);}break;
            case"add":{new addCommand(s,a,in);}break;
            case"remove_by_id":{new removeByIdCommand( s,a, in);}break;
            case"execute_script":{new executeScriptCommand( s,a, in,way);}break;
            case"remove_first":{new removeFirstCommand( s,a);}break;
            case"update":{new updateIdCommand( s,a, in);}break;
            case"add_if_max":{new addIfMaxCommand( s,a, in);}break;
            case"remove_all_by_difficulty":{new removeAllByDifficultyCommand( s,a,in);}break;
            case"filter_by_difficulty":{new filterByDifficultyCommand( s,a, in);}break;
            case"filter_starts_with_name":{new filterStartsWithNameCommand( s,a,in);}break;
            default:{ System.out.println("Такой команды не существует, обратитесь к команде help для вывода справки по командам");break; }
        }
    }
    public boolean connectionChecker(DatagramChannel s, SocketAddress a){
        try {
            byte[] message = "1".getBytes();
            ByteBuffer mess = ByteBuffer.wrap(message);
            s.send(mess,a);
            mess.clear();
            s.receive(mess);
            String kString = new String(mess.array());
            return kString.startsWith("1");
        }catch (Exception e){
            return false;
        }
    }

}