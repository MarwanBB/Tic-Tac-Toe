package controllers;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    Socket mySocket;
    PrintStream printstream;

    public Client() {
        try {
            mySocket = new Socket("127.0.0.1", 5005);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        createThread();
    }

    public void createThread() {

        new Thread() {
            @Override
            public void run() {

                while (true) {
                    DataInputStream dataInputStream;
                    try {
                        dataInputStream = new DataInputStream(mySocket.getInputStream());
                        String str = dataInputStream.readLine();

                        if (str != null) {
                            if (!str.isEmpty()) {
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }.start();
    }

    public void sendMessage() {

    }

}
