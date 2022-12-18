package tictactoeserver;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class Handler extends Thread {

    DataInputStream dataInputStream;
    PrintStream printStream;
    public Socket socket;
    private User user;
    private int isAvailable;
    private int isPlaying;

    public static Vector<Handler> handlerList = new Vector<Handler>();
//    public static String playerOne;
//    public static String playerTwo;
    public static Vector<String> playerOne = new Vector<String>();
    public static Vector<String> playerTwo = new Vector<String>();

    private int indexHandler;

    public Handler(Socket socket, int countHandlersFromServer) {

        user = new User();

        indexHandler = countHandlersFromServer;

        System.out.println("handler constructor outside try");
        this.socket = socket;
        try {
            System.out.println("handler constructor inside try");
            dataInputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());

            Handler.handlerList.add(this); // every time add chat in handler

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
                System.out.println("str recieved by handler: " + str);

                if (str != null) {
                    String[] arrString = str.split("/");

                    switch (arrString[0]) {
                        case Constants.signUp:
                            System.out.println("check true for sign up in handler");
                            DatabaseAccessLayer.signUp(arrString[1], arrString[2]);
                            System.out.println(arrString[1]);
                            System.out.println(arrString[2]);
                            break;

                        case Constants.signIn:
                            System.out.println("check true for sign in in handler");
                            System.out.println(arrString);
                            if (DatabaseAccessLayer.signIn(arrString[1], arrString[2])) {
                                sendMessage("userFound");

                                System.out.println(arrString[1]);
                                System.out.println(arrString[2]);
                            }
                            break;

                        case "message":
                            sendMessageToAll(str);
                            break;

                        case "refreshOnlineOnSignIn":
                            this.user.setUsername(arrString[1]);
                            this.user.setPassword(arrString[2]);
                            this.isAvailable = 1;
                            this.isPlaying = 0;
                            refreshAvailablePlayers();
                            break;

                        case "refreshOnlineAnyTime":
                            refreshAvailablePlayers();
                            break;

                        case "invitePlayer":
                            invitePlayerHandler(str);
                            break;
                        case "playGame2Players":
                            playGame(str);
                            break;
                    }

                }

            } catch (IOException ex) {
                System.out.println("in catch of handler of the while loop in run");
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws IOException {
//        for (Handler handler : handlerList) {
//            handler.printStream.println(message);
//        }
        System.out.println("In handler sending back message::: " + message);
        printStream = new PrintStream(socket.getOutputStream());
        printStream.println(message);

    }

    public void sendMessageToAll(String str) throws IOException {

        String[] arrString = str.split("/");

//            int index1 = playerOne.indexOf(arrString[1]);
//            int index2 = playerTwo.indexOf(arrString[2]);
        int index1 = handlerList.indexOf(playerOne.indexOf(arrString[1]));
        int index2 = handlerList.indexOf(playerTwo.indexOf(arrString[2]));

        System.out.println(index1);
        System.out.println(index2);

        handlerList.get(index1).printStream.println("message" + "/" + arrString[3]);
        handlerList.get(index2).printStream.println("message" + "/" + arrString[3]);

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    void refreshAvailablePlayers() {
        try {
            printStream = new PrintStream(socket.getOutputStream());
            printStream.println("emptyAvailablePlayersList");

            for (Handler handler : handlerList) {
                if (handler.isAvailable == 1) {
                    printStream = new PrintStream(socket.getOutputStream());
                    printStream.println("refreshAvailable" + "/" + handler.user.getUsername());

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void invitePlayerHandler(String str) {
        String[] arrString = str.split("/");
        for (Handler handler : handlerList) {
            if (handler.user.getUsername().equals(arrString[2])) {
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("createRequest" + "/" + arrString[1] + "/" + arrString[2]);
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    void playGame(String str) {
        String[] arrString = str.split("/");
        for (Handler handler : handlerList) {
            if (handler.user.getUsername().equals(arrString[1]) || handler.user.getUsername().equals(arrString[2])) {
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("goToGameView" + "/" + arrString[1] + "/" + arrString[2]);
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

}
