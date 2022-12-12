package tictactoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket socket;

    public Server() {
        System.out.println("Server turned on");
        try {
            
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
            ex.printStackTrace();
        }   
        
    }
    
    public static void main(String[] args) {
        //new Server();
    }
    
}
