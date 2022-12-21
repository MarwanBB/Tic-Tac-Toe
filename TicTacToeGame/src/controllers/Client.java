package controllers;

import Utility.Constants;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
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

    public static Client getInstance(String ip, int port) {
        String s = "";

        if (instance == null) {
            instance = new Client(ip, port);
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

    public synchronized String getStr() {
        return str;
    }

    public synchronized void setStr(String str) {
        this.str = str;
    }

    private Client(String ip, int port) {
        try {

//            
//            System.out.println("input text is" + input.getText());
            mySocket = new Socket(ip, port);
            //mySocket = new Socket(input.getText(), 5005);
            //mySocket = new Socket("127.0.0.1", 5005);
            user = new User();
            threadClient = new Thread(this);
            threadClient.start();

        } catch (IOException ex) {

            if (ex instanceof ConnectException) {
                // SignInController.getThreadSignIn().stop();
                AlertBoxOneButton.createAlert("connection Exeption", "server is Off", "ok");
                this.closeClient();

            }

        }

    }

    public void run() {

        while (true) {
            try {

                dataInputStream = new DataInputStream(mySocket.getInputStream());
                str = dataInputStream.readLine();

                /*if (SignInController.getSignInRequestRunningFlag() == 1) { //if a sign in request is running
                    SignInController.getThreadSignIn().resume();
                }*/
                if (str != null) {
                    String[] arrString = str.split("/");

                    //checking first element of the String array (the key)
                    switch (arrString[0]) {
                        case Constants.signUpSucceded:
                            //arrString[] = signUpSucceded
                            AlertBoxOneButton.createAlert("Sign Up", "Sign Up was successful.", "Ok");
                            break;
                        case Constants.signUpFailed:
                            //arrString[] = signUpFailed
                            AlertBoxOneButton.createAlert("Sign Up", "Sign Up failed, username already exists.", "Ok");
                            break;

                        case Constants.userNotFoundAfterSignInRequest:
                            AlertBoxOneButton.createAlert("Sign In", "Sign In failed, Wrong username or password or user already logged in.", "Ok");
                            break;

                        case Constants.emptyAvailablePlayersList:
                            //used to empty the available players list before filling it again with the current available players.
                            //arrString[] = emptyAvailablePlayersList
                            clientEmptyAvailablePlayersList();
                            break;

                        case Constants.refreshAvailablePlayersList:
                            // used to fill the available players list after emptying it by the emptyAvailablePlayersList function.
                            // arrString[] = "refreshAvailable" + "/" + handler.user.getUsername()
                            clientRefreshAvailableClients(arrString[1]);
                            break;

                        case Constants.showAlertForInvitedPlayer:
                            //arrString[] = showAlertForInvitedPlayer , username p1 , username p2
                            clientCreateRequest(str);
                            break;

                        case Constants.takeTheTwoPlayersToTheGameViewSincePlayerTwoAcceptedInvitation:
                            //arrString[] = takeTheTwoPlayersToTheGameView , username p1 , username p2 , (1 or 0)
                            // "1" for p1 and "0" for p2
                            clientGoToGameView(str);
                            break;

                        case Constants.showDeclineAlertForPlayerOne:
                            //arrString[] = player2DeclinedGameInvitation , username p1 , username p2
                            clientShowDeclineAlertForPlayerOne(str);
                            break;

                        case Constants.refreshGameBoardResponse:
                            //arrString[] = refreshGameBoardResponse , bString 
                            // where bString is the string that loads the game board
                            refreshGameBoardResponse(str);
                            break;

                        case "userFoundAfterSignInRequest":
                            OnlinePlayersController.playerUsername = user.getUsername();
                            setUser(user);
                            clientRefreshOnlineOnSignIn(user);
                            SceneNavigator.navigate("/views/OnlinePlayers.fxml");

                            break;

                        case "showWinningVideo":
                            showWinningVideo();
                            break;

                        case "showLosingVideo":
                            showLosingVideo();
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
            printstream.println(Constants.signUpRequest + "/" + user.getUsername() + "/" + user.getPassword());

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clientSignInRequest(User user) {
        System.out.println("sign in request in client");
        try {

            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.signInRequest + "/" + user.getUsername() + "/" + user.getPassword());

        } catch (IOException ex) {
            System.out.println("catch of sign in request");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void clientSendMessage(String st) {
//        try {
//            printstream = new PrintStream(mySocket.getOutputStream());
//            printstream.println(st);
//        } catch (IOException ex) {
//            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void clientRefreshOnlineOnSignIn(User user) {
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.refreshOnlineOnSignIn + "/" + user.getUsername() + "/" + user.getPassword());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clientRefreshOnlineAnyTimeClient() {
        // invoked when refresh button is clicked in online players view.
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.refreshOnlinePlayersWhenRefreshButtonIsClicked);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void clientEmptyAvailablePlayersList() {
        OnlinePlayersController.emptyAvailablePlayersListInView();
    }

    void clientRefreshAvailableClients(String userNameAvaiable) {
        OnlinePlayersController.addToAvailablePlayersList(userNameAvaiable);
        System.out.println("Username available in client refreshAvailableClients::: " + userNameAvaiable);
    }

    public void clientInvitePlayer(String userNameOfPlayerInvited) {
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(Constants.invitePlayer + "/" + this.user.getUsername() + "/" + userNameOfPlayerInvited);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void clientCreateRequest(String str) {
        //arrString[] = showAlertForInvitedPlayer , username p1 , username p2
        String[] arrString = str.split("/");

        ButtonType Accept = new ButtonType("Accept");
        ButtonType Decline = new ButtonType("Decline");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.NONE, "", Accept, Decline);
                alert.setTitle("Game Invitation");
                alert.setContentText(arrString[1] + " would like to play with you.");
                Window window = alert.getDialogPane().getScene().getWindow();
                window.setOnCloseRequest(e -> alert.hide());
                Optional<ButtonType> result = alert.showAndWait();
                result.ifPresent(res -> {
                    if (res.equals(Accept)) {
                        clientResponseOfInvitation(Constants.invitedPlayerAcceptedGameInvitation + "/" + arrString[1] + "/" + arrString[2]);
                    } else if (res.equals(Decline)) {
                        clientResponseOfInvitation(Constants.invitedPlayerDeclinedGameInvitation + "/" + arrString[1] + "/" + arrString[2]);
                    }
                });
            }
        });

    }

    void clientResponseOfInvitation(String str) {
        //arrString[] = invitedPlayerAcceptedGameInvitation , username p1 , username p2
        //or arrString[] = invitedPlayerDeclinedGameInvitation , username p1 , username p2
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(str);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void clientGoToGameView(String str) {
        //arrString[] = takeTheTwoPlayersToTheGameView , username p1 , username p2 , (1 or 0)
        // "1" for p1 and "0" for p2

        String[] arrString = str.split("/");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                try {

                Room.setPlayerOneUserName(arrString[1]);
                Room.setPlayerTwoUserName(arrString[2]);
                Room.setTurn(arrString[3]);
                //Room.setPlayerStartTurn(arrString[3]);

                System.out.println("before trying to go to game");

                SceneNavigator.navigate("/views/GameOnline.fxml");

            }
        });

    }

    public void clientRefreshGameBoardRequest(String str) {
        //arrString[]= "refreshGameBoardAfterEveryTurn" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName() + "/" + bString
        //so arrString[] = "refreshGameBoardAfterEveryTurn" + username1 + username2 + String that loads the game board after every game button clicked.
        String[] arrString = str.split("/");
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println(str);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void refreshGameBoardResponse(String str) {
        //arrString[] = refreshGameBoardResponse , bString 
        // where bString is the string that loads the game board

        String[] arrString = str.split("/");

        Room.setBoardDataAsString(arrString[1]);

        SceneNavigator.navigate("/views/GameOnline.fxml");

    }

    void clientShowDeclineAlertForPlayerOne(String str) {
        //arrString[] = player2DeclinedGameInvitation , username p1 , username p2
        String[] arrString = str.split("/");
        AlertBoxOneButton.createAlert("Game Invitation", arrString[2] + " declined your invitation", "Ok");
    }

    public void closeClient() {
        try {
            this.dataInputStream.close();
            this.printstream.close();
            this.mySocket.close();
            this.threadClient.stop();
            instance = null;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CloseRequest() {

        // printstream = new PrintStream(mySocket.getOutputStream());
        printstream.println(Constants.closeClient + "/" + user.getUsername());

    }

    void playerOneWon() {
        System.out.println("player one won called");
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println("playerOneWon" + "/" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void playerTwoWon() {
        System.out.println("player two won called");
        try {
            printstream = new PrintStream(mySocket.getOutputStream());
            printstream.println("playerTwoWon" + "/" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showWinningVideo() {
        setStr("");
        Room.setBoardDataAsString("");
        System.out.println("show winning video called" + user.getUsername());
        SceneNavigator.navigate("/views/WinnerOnline.fxml");
    }

    void showLosingVideo() {
        setStr("");
        Room.setBoardDataAsString("");
        System.out.println("show losing video called" + user.getUsername());
        SceneNavigator.navigate("/views/LoserOnline.fxml");
    }

    void appearOffline(String username) {
        printstream.println("appearOffline" + "/" + username);
    }

}
