package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class SocketThreadServer extends Thread {

    private static final Logger logger = Logger.getLogger(String.valueOf(SocketThreadServer.class));

    private Socket socket;

    public SocketThreadServer(Socket socket){
        this.socket = socket;
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        try{
            String connIP = socket.getInetAddress().getHostAddress();
   //         logger.info(connIP+"에서 연결 시도합니다.");

            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 기존 로그 제거
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            // End

            Handler handler = new ConsoleHandler();
            CustomLogFormatter formatter = new CustomLogFormatter();
            handler.setFormatter(formatter);

            // 포맷팅 새로 한 로그로 찍음...
            rootLogger.addHandler(handler);
            rootLogger.info(br.readLine());

            pw.println("수신완료");
            pw.flush();

        }catch (IOException e) {
            logger.info((Supplier<String>) e);
        }finally {
            try{
                if (pw != null) {
                    pw.close();
                }
                if (br != null) {
                    br.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch(IOException e){
               logger.info(String.valueOf(e));
            }
        }
    }

}
