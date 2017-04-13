package game.model;

import game.model.managers.TransportAbilityManager;
import game.model.map.RBMap;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private RBMap map;
    private TransportAbilityManager transportAbilityManager;

    public Game(RBMap map, Player player1, Player player2, TransportAbilityManager transportAbilityManager) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.map = map;
        this.transportAbilityManager = this.transportAbilityManager;
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
