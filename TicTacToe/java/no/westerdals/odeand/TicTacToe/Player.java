package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 17.03.2017.


import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{

    private String name;
    private int score;
    private List<Integer> playerMoves;
    private boolean singlePlayer;
    private long id;

    public Player(String name, int score, List<Integer> playerMoves) {
        this.name = name;
        this.score = score;
        this.playerMoves = playerMoves;
    }

    public Player() {
    }

    public Player(String name, int score, boolean singlePlayer, long id) {
        this.name = name;
        this.score = score;
        this.singlePlayer = singlePlayer;
        this.id = id;
    }

    public void incrementScore() {
        this.score ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getPlayerMoves() {
        return playerMoves;
    }

    public void setPlayerMoves(List<Integer> playerMoves) {
        this.playerMoves = playerMoves;
    }

    @Override
    public String toString() {
        return String.format("%-30s%-20s%-10s","Player: " + name, "Score: " + score, "Singleplayer: " + singlePlayer);
    }

    public boolean isSinglePlayer() {
        return singlePlayer;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        this.singlePlayer = singlePlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (score != player.score) return false;
        if (singlePlayer != player.singlePlayer) return false;
        if (id != player.id) return false;
        if (!name.equals(player.name)) return false;
        return playerMoves != null ? playerMoves.equals(player.playerMoves) : player.playerMoves == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + score;
        result = 31 * result + (playerMoves != null ? playerMoves.hashCode() : 0);
        result = 31 * result + (singlePlayer ? 1 : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
