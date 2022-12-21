package models;

public class Room {

    private static String playerOneUserName;
    private static String playerTwoUserName;

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

}
