package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(String.valueOf(App.class));

    private static final int PORT_NUMBER = 4432;
    public static void main(String[] args) throws IOException {
        logger.info(":::            :::");
        logger.info(":::    Socket Application Process Start        :::");
        logger.info(":::            :::");
        System.out.println("Socket Application Process Start");

        try(ServerSocket server = new ServerSocket(PORT_NUMBER)){
            while(true){
                Socket connection = server.accept();
                server.setReuseAddress(true);
                Thread task = new SocketThreadServer(connection);
                task.start();
            }
        }catch (IOException e) {

        }
    }
}
