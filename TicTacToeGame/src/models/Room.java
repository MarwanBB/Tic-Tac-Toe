
package models;


public class Room {
    
    private static String playerOneUserName;
    private static String playerTwoUserName;
    private static String turn;
    private static String boardDataAsString;

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

    
    
}
