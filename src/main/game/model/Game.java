package game.model;

import game.model.map.RBMap;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private RBMap map;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.map = new RBMap();
    }

    // TODO
    public int getCurrentPhase() {
        return 0;
    }

    // TODO
    public void goToNextPhase() {

    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public RBMap getMap() {
        return this.map;
    }
}
