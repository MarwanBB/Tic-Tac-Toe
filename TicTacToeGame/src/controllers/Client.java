package controllers;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;

public class Client {

    Socket mySocket;
    PrintStream printstream;
    private static Client instance;
    //private DataInputStream dataInputStream;;

    public static Client getInstance() {
        if (instance == null) {
            return new Client();
        } else {
            return instance;
        }
    }

    private Client() {
        try {
            mySocket = new Socket("127.0.0.1", 5005);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //createThread();
    }

//    public void createThread() {
//
//        new Thread() {
//            @Override
//            public void run() {
//
//                while (true) {
//                    DataInputStream dataInputStream;
//                    try {
//                        System.out.println("in try of client");
//                        dataInputStream = new DataInputStream(mySocket.getInputStream());
//                        String str = dataInputStream.readLine();
//                        
//                        System.out.println(dataInputStream.readLine());
//                        
//                        if (str != null) {
//                            switch(str){
//                                case "userFound":
//                                    System.out.println("i found the user!");
//                                    
//
//                                    
//                            }
//                        }
//                    } catch (IOException e) {
//                        System.out.println("in catch of client");
//                        System.out.println(e.toString());
//                    }
//                }
//            }
//        }.start();
//    }
    public void signUpRequest(User user) {
        try {

            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signUp + "/" + user.getUsername() + "/" + user.getPassword());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void signInRequest(User user) {
        System.out.println("atop of sign in request in client");
        try {

            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signIn + "/" + user.getUsername() + "/" + user.getPassword());

            //printStream.println();
        } catch (IOException ex) {
            System.out.println("catch of sign in request");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String readResponse() throws IOException {
        DataInputStream dataInputStream;
        dataInputStream = new DataInputStream(mySocket.getInputStream());
        String str = dataInputStream.readLine();
        return str;
    }

}
