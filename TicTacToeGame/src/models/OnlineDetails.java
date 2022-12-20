/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Copy Center
 */
public class OnlineDetails {
    private static String pName1;
    private static String pName2;
    private static int pScore1 = 0;
    private static int pScore2 = 0;
    private static int tie = 0;

    public static String getpName1() {
        return pName1;
    }

    public static void setpName1(String pName1) {
        OnlineDetails.pName1 = pName1;
    }

    public static String getpName2() {
        return pName2;
    }

    public static void setpName2(String pName2) {
        OnlineDetails.pName2 = pName2;
    }

    public static int getpScore1() {
        return pScore1;
    }

    public static void setpScore1(int pScore1) {
        OnlineDetails.pScore1 = pScore1;
    }

    public static int getpScore2() {
        return pScore2;
    }

    public static void setpScore2(int pScore2) {
        OnlineDetails.pScore2 = pScore2;
    }

    public static int getTie() {
        return tie;
    }

    public static void setTie(int tie) {
        OnlineDetails.tie = tie;
    }




    
    
}
