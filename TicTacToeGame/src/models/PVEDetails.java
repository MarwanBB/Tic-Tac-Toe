
package models;


public class PVEDetails {

    private static String pName;
    private static String pScore;
    private static String pcScore;
    private static String tie;

    public static String getpName() {
        return pName;
    }

    public static void setpName(String pName) {
        PVEDetails.pName = pName;
    }

    public static String getpScore() {
        return pScore;
    }

    public static void setpScore(String pScore) {
        PVEDetails.pScore = pScore;
    }

    public static String getPcScore() {
        return pcScore;
    }

    public static void setPcScore(String pcScore) {
        PVEDetails.pcScore = pcScore;
    }

    public static String getTie() {
        return tie;
    }

    public static void setTie(String tie) {
        PVEDetails.tie = tie;
    }
}
