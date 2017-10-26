package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 17.03.2017.


import java.util.ArrayList;
import java.util.List;

public class WinCondition {

    public static boolean hasWon(Player player) {

        if (player.getPlayerMoves().size() < 3) return false;

        for (List<Integer> i: winList()) {
            if (player.getPlayerMoves().containsAll(i)) {
                return true;
            }
        }

        return false;
    }


    private static ArrayList<List<Integer>> winList() {
        ArrayList<List<Integer>> winOption = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(8);
        l1.add(4);
        l1.add(0);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(2);
        l1.add(1);
        l1.add(0);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(5);
        l1.add(4);
        l1.add(3);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(8);
        l1.add(7);
        l1.add(6);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(6);
        l1.add(4);
        l1.add(2);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(6);
        l1.add(3);
        l1.add(0);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(7);
        l1.add(4);
        l1.add(1);
        winOption.add(l1);
        l1 = new ArrayList<>();
        l1.add(8);
        l1.add(5);
        l1.add(2);
        winOption.add(l1);

        return winOption;
    }




}
