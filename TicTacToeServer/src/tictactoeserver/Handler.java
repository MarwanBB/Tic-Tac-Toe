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
                            //System.out.println(arrString);
                                if (DatabaseAccessLayer.signIn(arrString[1], arrString[2])) {
                                                   signInResponse("userFoundAfterSignInRequest");
                                               }
                                               else{
                                                   signInResponse("userNotFoundAfterSignInRequest");
                                               }
                            break;
                            case "refreshOnlineOnSignIn":
                            //arrString[] = "refreshOnlineOnSignIn" ,  username ,  password
                            this.user.setUsername(arrString[1]);
                            this.user.setPassword(arrString[2]);
                            this.isAvailable = 1;
                            this.isPlaying = 0;
                            handlerRefreshAvailablePlayers();
                            break;
                        case "refreshOnlinePlayersWhenRefreshButtonIsClicked":
                            //arrString[] = refreshOnlinePlayersWhenRefreshButtonIsClicked
                            handlerRefreshAvailablePlayers();
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

    void handlerSignUpResponse(Boolean bol){
        try {
            printStream = new PrintStream(socket.getOutputStream());
            if(bol == true){
                printStream.println("signUpSucceded");
            } else{
                printStream.println("signUpFailed");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signInResponse(String response) throws IOException {
        //response is either "userFoundAfterSignInRequest" or "userNotFoundAfterSignInRequest"
        printStream = new PrintStream(socket.getOutputStream());
        printStream.println(response);

    }    


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    void handlerRefreshAvailablePlayers() {
        try {
            printStream = new PrintStream(socket.getOutputStream());
            //used to empty the available players list before filling it again with the current available players.
            printStream.println("emptyAvailablePlayersList");

            for (Handler handler : handlerList) {
                if (handler.isAvailable == 1) {
                    printStream = new PrintStream(socket.getOutputStream());
                    // used to fill the available players list after emptying it by the emptyAvailablePlayersList function.
                    printStream.println("refreshAvailablePlayersList" + "/" + handler.user.getUsername());

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
