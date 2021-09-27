package server;

import collection.Collection;
import collection.loader.DifficultyExeption;
import serverCommands.*;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

import functions.*;
public class
CommandWorker implements byteToString{
    public void comWork(String command, DatagramChannel s, SocketAddress a, Collection collection) throws IOException, DifficultyExeption {
        // резервируем буфер под клиентское сообщение
        byte [] buffer = new byte[1000];
        String line = working(command.getBytes(StandardCharsets.UTF_8));
        //System.out.println(line+":");
        if (command.startsWith("help")) {new helpCommand(s,a);}
        if (command.startsWith("show")) new showCommand(collection,s,a);
        if (command.startsWith("sort")) new sortCommand(collection);
        if (command.startsWith("info")) new infoCommand(collection,s,a);
        if (command.startsWith("add") & line.length()<="add".length()) new addCommand(collection,s,a);
        if (command.startsWith("filter_by_difficulty")) new filterByDifficultyCommand( s,  a,  collection, buffer);
        if (command.startsWith("clear")) new clearCommand(  collection,  s, a);
        if (command.startsWith("filter_starts_with_name"))new filterStartsWithNameCommand(s,a,collection); //new filterStartsWithNameCommand( s,  a,buffer , collection );// new filterStartsWithNameCommand( s,  a,  buffer, collection);
        if (command.startsWith("remove_all_by_difficulty")) new removeAllByDifficultyCommand( s,  a,  collection, buffer);
        if (command.startsWith("remove_by_id")) new removeByIdCommand( s,  a, buffer,  collection);
        if (command.startsWith("remove_first")) new removeFirstCommand(  collection);
        if (command.startsWith("update")) new updateIdCommand( s,  a,  collection, buffer);
        if (command.startsWith("add_if_max") ) new addIfMaxCommand(collection,s,a);
        collection.fileSaving();
    }

    public boolean connectionChecker(DatagramChannel s, SocketAddress a,byte[] buffer) {
        try {
            // резервируем дейтаграмму под пакет клиента
            ByteBuffer mess = ByteBuffer.wrap(buffer);
            // принимаем пакет клиента
            s.receive(mess);
            //System.out.println(new String(mess.array()));
            String line = working(mess.array());
            if (!line.equals("1"))return false;
            byte[] message = line.getBytes();
            mess.clear();
            mess = ByteBuffer.wrap(message);
            s.send(mess,a);
            System.out.println("Связь с клиентом установлена");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

