package controllers;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import models.AlertBoxOneButton;
import models.Room;
import models.SceneNavigator;
import models.User;

public class Client implements Runnable {

    Socket mySocket;
    PrintStream printstream;
    private static Client instance;
    private DataInputStream dataInputStream;
    private String str = "";
    private Thread threadClient;
    private User user;

    public static Client getInstance() {
        String s = "";

        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThreadClient() {
        return threadClient;
    }

    public void setThreadClient(Thread threadClient) {
        this.threadClient = threadClient;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    private Client() {
        try {

            mySocket = new Socket("127.0.0.1", 5005);
            user = new User();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        threadClient = new Thread(this);
        threadClient.start();
    }

    public void run() {

        while (true) {
            try {

                dataInputStream = new DataInputStream(mySocket.getInputStream());
                str = dataInputStream.readLine();

                

                if (str != null) {
                    String[] arrString = str.split("/");

                    //checking first element of the String array (the key)
                    switch (arrString[0]) {
                        case "userFound":
                            System.out.println("i found the user");
                            
                            break;
                        case "refreshAvailablePlayersList":
                            // used to fill the available players list after emptying it by the emptyAvailablePlayersList function.
                            // arrString[] = "refreshAvailable" + "/" + handler.user.getUsername()
                            clientRefreshAvailableClients(arrString[1]);
                            break;
                        case "emptyAvailablePlayersList":
                            //used to empty the available players list before filling it again with the current available players.
                            //arrString[] = emptyAvailablePlayersList
                            clientEmptyAvailablePlayersList();
                            break;
                    }
                }

            } catch (IOException e) {
                str = "";
                System.out.println("in catch of client");
            }
        }

    }

    public void clientSignUpRequest(User user) {
        try {

            System.out.println(user.getUsername());
            System.out.println(user.getPassword());

            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signUp + "/" + user.getUsername() + "/" + user.getPassword());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clientSignInRequest(User user) {
        System.out.println("sign in request in client");
        try {

            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signIn + "/" + user.getUsername() + "/" + user.getPassword());

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
    public void clientRefreshOnlineOnSignIn(User user) {
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println("refreshOnlineOnSignIn" + "/" + user.getUsername() + "/" + user.getPassword());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void clientRefreshOnlineAnyTimeClient() {
        // invoked when refresh button is clicked in online players view.
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println("refreshOnlinePlayersWhenRefreshButtonIsClicked");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void clientRefreshAvailableClients(String userNameAvaiable) {
        OnlinePlayersController.addToAvailablePlayersList(userNameAvaiable);
        System.out.println("Username available in client refreshAvailableClients::: " + userNameAvaiable);
    }
    void clientEmptyAvailablePlayersList() {
        OnlinePlayersController.emptyAvailablePlayersListInView();
    }

}
