/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asalah
 */
public class GameDataPVP {
    String gameDate;
    String player1Name;
    String player1Score;
    String player2Name;
    String player2Score;
    String winner;

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getPlayer1() {
        return player1Name;
    }

    public void setPlayer1(String player1) {
        this.player1Name = player1;
    }

    public String getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(String player1Score) {
        this.player1Score = player1Score;
    }

    public String getPlayer2() {
        return player2Name;
    }

    public void setPlayer2(String player2) {
        this.player2Name = player2;
    }

    public String getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(String player2Score) {
        this.player2Score = player2Score;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public GameDataPVP(String gameDate, String player1, String player1Score, String player2, String player2Score, String winner) {
        this.gameDate = gameDate;
        this.player1Name = player1;
        this.player1Score = player1Score;
        this.player2Name = player2;
        this.player2Score = player2Score;
        this.winner = winner;
    }

    public String getScoreP1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getScoreP2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
