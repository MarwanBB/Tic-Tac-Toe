package tictactoeserver;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class Handler extends Thread {

    DataInputStream dataInputStream;
    PrintStream printStream;
    public Socket socket;
    public static Vector<Handler> chatList = new Vector<Handler>();

    public Handler(Socket socket) {
        System.out.println("handler constructor outside try");
        this.socket = socket;
        try {
            System.out.println("handler constructor inside try");
            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());

            Handler.chatList.add(this); // every time add chat in handler
            
            
            start();
        } catch (IOException ex) {
            System.out.println("inside catch");
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        System.out.println("Handler inside run outside while loop");
        while (true) {
            try {
                String str = dataInputStream.readLine();
                System.out.println("Inside Handler, in while loop in try dataInputStream.readLine() in run");
                System.out.println(str);
                
                if (str != null) {
                    String[] arrString = str.split("/");
                    if (arrString[0].equals(Constants.signUp)){
                        DatabaseAccessLayer.SignUp(arrString[1], arrString[2]);
                        System.out.println(arrString[1]);
                        System.out.println(arrString[2]);
                    }
                }

            } catch (IOException ex) {
                System.out.println("in catch of handler of the while loop in run");
                ex.printStackTrace();
            }
        }
    }

//    public void sendMessageToAll(String message) {
//        for (Handler chat : chatList) {
//            chat.printStream.println(message);
//        }
//    }
}
