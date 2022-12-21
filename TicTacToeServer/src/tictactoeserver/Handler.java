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

    public static Vector<String> playerOne = new Vector<String>();
    public static Vector<String> playerTwo = new Vector<String>();

    private int indexHandler;
    private boolean closed = false;

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
        while (!closed) {

            try {
                String str = dataInputStream.readLine();
                if(str==null){
                    closeClient();
                    int x = handlerList.indexOf(this);
                System.out.println("x::" + x);
                Handler removed = handlerList.remove(x);
                }
                System.out.println("Inside Handler, in while loop in try dataInputStream.readLine() in run");
                System.out.println("str recieved by handler: " + str);

                String[] arrString = str.split("/");

                switch (arrString[0]) {
                    case Constants.signUpRequest:
                        //arrString[] = Constants.signUpRequest ,  username , password
                        handlerSignUpResponse(DatabaseAccessLayer.signUp(arrString[1], arrString[2]));
                        break;

                    case Constants.signInRequest:
                        System.out.println("sign in request in handler");
                        //arrString[] = Constants.signInRequest ,  username ,  password
                        if (DatabaseAccessLayer.signIn(arrString[1], arrString[2])) {
                            signInResponse(Constants.userFoundAfterSignInRequest);
                            this.user.setUsername(arrString[1]);
                            this.user.setPassword(arrString[2]);
                            this.isAvailable = 1;
                            this.isPlaying = 0;
                        } else {
                            signInResponse(Constants.userNotFoundAfterSignInRequest);
                        }
                        break;

                    case Constants.refreshOnlineOnSignIn:
                        //arrString[] = "refreshOnlineOnSignIn" ,  username ,  password
                        
                        handlerRefreshAvailablePlayers();
                        break;

                    case Constants.refreshOnlinePlayersWhenRefreshButtonIsClicked:
                        //arrString[] = refreshOnlinePlayersWhenRefreshButtonIsClicked
                        handlerRefreshAvailablePlayers();
                        break;

                    case Constants.invitePlayer:
                        handlerInvitePlayer(str);
                        break;
                    case Constants.invitedPlayerAcceptedGameInvitation:
                        //arrString[] = invitedPlayerAcceptedGameInvitation , username p1 , username p2
                        clientPlayGame(str);
                        break;

                    case Constants.invitedPlayerDeclinedGameInvitation:
                        //arrString[] = invitedPlayerDeclinedGameInvitation , username p1 , username p2
                        clientPlayGame(str);
                        break;
                    case Constants.closeClient:
                        //arrString[] = closeClient , username
                        System.out.println(str);
                        DatabaseAccessLayer.logout(arrString[1]);
                        handleCloseClientRequest(str);

                        break;

                    case Constants.refreshGameBoardAfterEveryTurn:
                        //arrString[]= "refreshGameBoardAfterEveryTurn" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName() + "/" + bString
                        //so arrString[] = "refreshGameBoardAfterEveryTurn" + username1 + username2 + String that loads the game board after every game button clicked.
                        handlerChangeBoard(str);
                        break;
                        
                        case "playerOneWon":
                            goToVideoView(str);
                            break;
                            
                        case "playerTwoWon":
                            goToVideoView(str);
                            break;
                            
                        case "appearOffline":
                            System.out.println("appear offline in handler !!");
                            DatabaseAccessLayer.appearOffline(arrString[1]);
                            break;
                }

            } catch (NullPointerException e) {
                closed = true;
                this.isAvailable=0;
                
                closeClient();
                DatabaseAccessLayer.logout(user.getUsername());
                int x = handlerList.indexOf(this);
                System.out.println("x::" + x);
                Handler removed = handlerList.remove(x);

            } catch (IOException ex) {
                
                DatabaseAccessLayer.logout(user.getUsername());
                closed = true;
                this.isAvailable=0;
                closeClient();
                int x = handlerList.indexOf(this);
                System.out.println("x::" + x);
                if(!handlerList.isEmpty()){
                    Handler removed = handlerList.remove(x);
                }
                

                
            }
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
            printStream.println(Constants.emptyAvailablePlayersList);

            for (Handler handler : handlerList) {
                if (handler.isAvailable == 1) {
                    printStream = new PrintStream(socket.getOutputStream());
                    // used to fill the available players list after emptying it by the emptyAvailablePlayersList function.
                    printStream.println(Constants.refreshAvailablePlayersList + "/" + handler.user.getUsername());

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void handlerInvitePlayer(String str) {
        // arrString[1] is the username of the player that sent the invitation, arrString[2] is the username of the reciever
        String[] arrString = str.split("/");
        for (Handler handler : handlerList) {
            if (handler.user.getUsername().equals(arrString[2])) {
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println(Constants.showAlertForInvitedPlayer + "/" + arrString[1] + "/" + arrString[2]);
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    void clientPlayGame(String str) {
        //arrString[] if p2 accepted = invitedPlayerAcceptedGameInvitation , username p1 , username p2
        //arrString[] if p2 declined = invitedPlayerDeclinedGameInvitation , username p1 , username p2
        String[] arrString = str.split("/");

        if (arrString[0].equals(Constants.invitedPlayerAcceptedGameInvitation)) {

            for (Handler handler : handlerList) {
                if (handler.user.getUsername().equals(arrString[1])) {
                    try {
                        printStream = new PrintStream(socket.getOutputStream());
                        //added "1" at the end of the first player to indicate that this is the first player to play.
                        handler.printStream.println(Constants.takeTheTwoPlayersToTheGameViewSincePlayerTwoAcceptedInvitation + "/" + arrString[1] + "/" + arrString[2] + "/" + "1");
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (handler.user.getUsername().equals(arrString[2])) {
                    try {
                        printStream = new PrintStream(socket.getOutputStream());
                        //added "0" at the end of the first player to indicate that this is the second player to play.
                        handler.printStream.println(Constants.takeTheTwoPlayersToTheGameViewSincePlayerTwoAcceptedInvitation + "/" + arrString[1] + "/" + arrString[2] + "/" + "0");
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        } else {
            for (Handler handler : handlerList) {
                if (handler.user.getUsername().equals(arrString[1])) {
                    try {
                        printStream = new PrintStream(socket.getOutputStream());
                        //arrString[] = player2DeclinedGameInvitation , username p1 , username p2
                        handler.printStream.println(Constants.showDeclineAlertForPlayerOne + "/" + arrString[1] + "/" + arrString[2]);
                    } catch (IOException ex) {
                        Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }
    }

    void handlerChangeBoard(String str) {
        //arrString[]= "refreshGameBoardAfterEveryTurn" + Room.getPlayerOneUserName() + "/" + Room.getPlayerTwoUserName() + "/" + bString
        //so arrString[] = "refreshGameBoardAfterEveryTurn" + username1 + username2 + String that loads the game board after every game button clicked.

        String[] arrString = str.split("/");
        for (Handler handler : handlerList) {
            if (handler.user.getUsername().equals(arrString[1]) || handler.user.getUsername().equals(arrString[2])) {
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println(Constants.refreshGameBoardResponse + "/" + arrString[3]);
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    void handlerSignUpResponse(Boolean bol) {
        try {
            printStream = new PrintStream(socket.getOutputStream());
            if (bol == true) {
                printStream.println(Constants.signUpSucceded);
            } else {
                printStream.println(Constants.signUpFailed);
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void goToVideoView(String str){
        System.out.println(str);
        String[] arrString = str.split("/");
        for (Handler handler : handlerList) {
            System.out.println(handler.user.getUsername());
            if (handler.user.getUsername().equals(arrString[1]) && "playerOneWon".equals(arrString[0])) {
                System.out.println("11111111111111111111 " + handler.user.getUsername());
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("showWinningVideo");
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if (handler.user.getUsername().equals(arrString[1]) && "playerTwoWon".equals(arrString[0])) {
                System.out.println("22222222222222222 " + handler.user.getUsername());
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("showLosingVideo");
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if (handler.user.getUsername().equals(arrString[2]) && "playerTwoWon".equals(arrString[0])) {
                System.out.println("3333333333333333 " + handler.user.getUsername());
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("showWinningVideo");
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if (handler.user.getUsername().equals(arrString[2]) && "playerOneWon".equals(arrString[0])) {
                System.out.println("444444444444444 " + handler.user.getUsername());
                try {
                    printStream = new PrintStream(socket.getOutputStream());
                    handler.printStream.println("showLosingVideo");
                } catch (IOException ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        
    }
        
    }

    void closeClient() {
        try {
            closed = true;
            System.out.println(closed);
            DatabaseAccessLayer.logout(this.getName());
            this.dataInputStream.close();
            this.printStream.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void handleCloseClientRequest(String msg) {

        String[] arr = msg.split("/");
        System.out.println(arr[1]);
        for (Handler handler : handlerList) {
            if (handler.getUser().getUsername().equals(arr[1])) {
                System.out.println(handler.getUser().getUsername() + "closed success");
                handler.closeClient();
                int x = handlerList.indexOf(handler);
                System.out.println("x::" + x);
                Handler removed = handlerList.remove(x);
                System.out.println(removed.getUser().getUsername());
                this.stop();

            }

        }

    }

}
