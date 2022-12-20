
package models;


public class Room {
    
    private static String playerOneUserName;
    private static String playerTwoUserName;
    private static String turn;
    private static String boardDataAsString;
    private static String playerStartTurn;
    private static String clearOnPlayAgain;
    private static int playerOneWonFlag = 0;
    private static int playerTwoWonFlag = 0;

    public static int getPlayerOneWonFlag() {
        return playerOneWonFlag;
    }

    public static void setPlayerOneWonFlag(int playerOneWonFlag) {
        Room.playerOneWonFlag = playerOneWonFlag;
    }

    public static int getPlayerTwoWonFlag() {
        return playerTwoWonFlag;
    }

    public static void setPlayerTwoWonFlag(int playerTwoWonFlag) {
        Room.playerTwoWonFlag = playerTwoWonFlag;
    }

    
    
    public static String getClearOnPlayAgain() {
        return clearOnPlayAgain;
    }

    public static void setClearOnPlayAgain(String clearOnPlayAgain) {
        Room.clearOnPlayAgain = clearOnPlayAgain;
    }

    
    
    public static String getPlayerOneUserName() {
        return playerOneUserName;
    }

    public static void setPlayerOneUserName(String playerOneUserName) {
        Room.playerOneUserName = playerOneUserName;
    }

    public static String getPlayerTwoUserName() {
        return playerTwoUserName;
    }

    public static void setPlayerTwoUserName(String playerTwoUserName) {
        Room.playerTwoUserName = playerTwoUserName;
    }

    public static String getTurn() {
        return turn;
    }

    public static void setTurn(String turn) {
        Room.turn = turn;
    }

    public static String getBoardDataAsString() {
        return boardDataAsString;
    }

    public static void setBoardDataAsString(String boardDataAsString) {
        Room.boardDataAsString = boardDataAsString;
    }

    public static String getPlayerStartTurn() {
        return playerStartTurn;
    }

    public static void setPlayerStartTurn(String playerStartTurn) {
        Room.playerStartTurn = playerStartTurn;
    }

    
    
}
