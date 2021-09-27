package server;

import collection.Collection;
import collection.loader.DifficultyExeption;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server{
    public static void main(String[] args)  {
        try{

            SocketAddress a =
                    new InetSocketAddress(InetAddress.getByName("localhost"), 2100);
            DatagramChannel s =
                    DatagramChannel.open();
            s.bind(a);
            while(true) {
                CommandWorker comwor = new CommandWorker();
                Collection collection = new Collection("data.txt");
                collection.fileSaving();
                boolean flag = true;
                System.out.println("Связь с клиентом устанавливается...");
                while (flag) {

                    if (comwor.connectionChecker(s, a, new byte[100])) {
                        String command;
                        do {
                            ByteBuffer mess = ByteBuffer.wrap(new byte[1000]);
                            a = s.receive(mess);
                            command = new String(mess.array());
                            comwor.comWork(command, s, a, collection);
                        } while (!command.startsWith("exit"));
                        flag = false;
                        System.out.println("Отключение от клиента завершено\n");
                    }
                }
            }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException | DifficultyExeption e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}