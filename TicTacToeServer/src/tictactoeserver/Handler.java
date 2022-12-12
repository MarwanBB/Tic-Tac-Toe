package tictactoeserver;

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
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());

            Handler.chatList.add(this); // every time add chat in handler
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                String str = dataInputStream.readLine();
                if (str != null) {
                    if (!str.isEmpty()) {
                        //sendMessageToAll(str);
                    }
                }

            } catch (IOException ex) {
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
