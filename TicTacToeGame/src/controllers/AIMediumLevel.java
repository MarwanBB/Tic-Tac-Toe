package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AIMediumLevel {

    List<List> winningLists;
    List<List> possibleWinLists;
    List possibleTopRow1;
    List possibleTopRow2;
    List possibleMidRow1;
    List possibleMidRow2;
    List possibleBottomRow1;
    List possibleBottomRow2;
    List possibleLeftcol1;
    List possibleMidcol1;
    List possibleRightcol1;
    List possiblediagonalOne1;
    List possibleDiagonalOne2;
    List possibleDiagonalTwo1;
    List possibleDiagonalTwo2;
    List topRow;
    List midRow;
    List bottomRow;
    List leftcol;
    List midcol;
    List rightcol;
    List diagonalOne;
    List diagonalTwo;
    List possibleRightcol2;

    AIMediumLevel() {
        winningLists = new ArrayList<>();
        possibleWinLists = new ArrayList<>();
        possibleTopRow1 = Arrays.asList(1, 2);
        possibleTopRow2 = Arrays.asList(1, 3);
        possibleMidRow1 = Arrays.asList(4, 6);
        possibleMidRow2 = Arrays.asList(4, 5);
        possibleBottomRow1 = Arrays.asList(8, 9);
        possibleBottomRow2 = Arrays.asList(7, 8);
        possibleLeftcol1 = Arrays.asList(1, 7);
        possibleMidcol1 = Arrays.asList(5, 8);
        possibleRightcol1 = Arrays.asList(3, 9);
        possibleRightcol2 = Arrays.asList(3, 6);
        possiblediagonalOne1 = Arrays.asList(5, 9);
        possibleDiagonalTwo1 = Arrays.asList(3, 5);
        possibleDiagonalOne2 = Arrays.asList(1, 5);
        possibleDiagonalTwo2 = Arrays.asList(5, 7);

        possibleWinLists.add(possibleTopRow1);
        possibleWinLists.add(possibleTopRow2);
        possibleWinLists.add(possibleMidRow1);
        possibleWinLists.add(possibleMidRow2);
        possibleWinLists.add(possibleBottomRow1);
        possibleWinLists.add(possibleBottomRow2);
        possibleWinLists.add(possibleLeftcol1);
        possibleWinLists.add(possibleMidcol1);
        possibleWinLists.add(possibleRightcol1);
        possibleWinLists.add(possibleRightcol2);
        possibleWinLists.add(possiblediagonalOne1);
        possibleWinLists.add(possibleDiagonalOne2);
        possibleWinLists.add(possibleDiagonalTwo1);
        possibleWinLists.add(possibleDiagonalTwo2);
        topRow = Arrays.asList(1, 2, 3);
        midRow = Arrays.asList(4, 5, 6);
        bottomRow = Arrays.asList(7, 8, 9);
        leftcol = Arrays.asList(1, 4, 7);
        midcol = Arrays.asList(2, 5, 8);
        rightcol = Arrays.asList(3, 6, 9);
        diagonalOne = Arrays.asList(1, 5, 9);
        diagonalTwo = Arrays.asList(3, 5, 7);
        winningLists.add(topRow);
        winningLists.add(midRow);
        winningLists.add(bottomRow);
        winningLists.add(leftcol);
        winningLists.add(midcol);
        winningLists.add(rightcol);
        winningLists.add(diagonalOne);
        winningLists.add(diagonalTwo);
    }

    public int setTheNextPlay(ArrayList<Integer> playerXSteps, ArrayList<Integer> moves) {
        // System.out.println("setTheNextPlay");
        int move = -1;
        for (List l : possibleWinLists) {
            if (playerXSteps.containsAll(l)) {
                if (l.equals(possibleTopRow1) && !moves.contains(3))// topRow1 = Arrays.asList(1, 2);
                {

                    return 3;
                } else if (l.equals(possibleTopRow2) && !moves.contains(2))//topRow2 = Arrays.asList(1, 3);
                {

                    return 2;
                } else if (l.equals(possibleMidRow1) && !moves.contains(5))//midRow1 = Arrays.asList(4, 6);
                {
                    return 5;
                } else if (l.equals(possibleMidRow2) && !moves.contains(6))//midRow2 = Arrays.asList(4, 5);
                {
                    return 6;
                } else if (l.equals(possibleBottomRow1) && !moves.contains(7)) {//bottomRow1 = Arrays.asList(8, 9);
                    return 7;
                } else if (l.equals(possibleBottomRow2) && !moves.contains(9)) {//bottomRow2 = Arrays.asList(7,8);
                    return 9;
                } else if (l.equals(possibleLeftcol1) && !moves.contains(4)) {//leftcol1 = Arrays.asList(1, 7);
                    return 4;
                } else if (l.equals(possibleMidcol1) && !moves.contains(2)) {//midcol1 = Arrays.asList(5, 8);
                    return 2;
                } else if (l.equals(possibleRightcol2) && !moves.contains(9)) { // rightco2 = Arrays.asList(3,6);
                    return 9;
                } else if (l.equals(possibleRightcol1) && !moves.contains(6)) {//rightco1 = Arrays.asList(3,9);
                    return 6;
                } else if (l.equals(possiblediagonalOne1) && !moves.contains(1)) {//diagonalOne1 = Arrays.asList( 5, 9);
                    return 1;
                } else if (l.equals(possibleDiagonalTwo1) && !moves.contains(7)) {//diagonalTwo1 = Arrays.asList(3, 5);
                    return 7;
                } else if (l.equals(possibleDiagonalOne2) && !moves.contains(9)) {//diagonalOne2 = Arrays.asList( 1, 5);
                    return 9;
                } else if (l.equals(possibleDiagonalTwo2) && !moves.contains(3)) { // diagonalTwo2 = Arrays.asList(5, 7);
                    return 3;
                }

            }
        }
        return move;
    }

    public int isGameOver(ArrayList<Integer> playerXSteps, ArrayList<Integer> playerOSteps) {
        if (playerXSteps.size() + playerOSteps.size() >= 5) {
            for (List l : winningLists) {
                if (playerXSteps.containsAll(l)) {
                    return 1;
                } else if (playerOSteps.containsAll(l)) {
                    return 2;
                }
            }
            if (playerXSteps.size() + playerOSteps.size() == 9) {
                return 0;
            }
        }

        return -1;
    }
}
