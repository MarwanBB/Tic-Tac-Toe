package tictactoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket socket;

    public Server() {
        System.out.println("Server inside constructor outside try");
        try {
            System.out.println("Server inside constructor inside try");
            serverSocket = new ServerSocket(5005);
                new Thread(new Runnable(){
                
                @Override
                public void run() {
                    while(true){
                        try {
                            socket = serverSocket.accept();
                            new Handler(socket);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
            }).start();
        } catch (IOException ex) {
            System.out.println("server at catch");
            ex.printStackTrace();
        }   
        
    }
    
    public static void main(String[] args) {
        //new Server();
    }
    
}
