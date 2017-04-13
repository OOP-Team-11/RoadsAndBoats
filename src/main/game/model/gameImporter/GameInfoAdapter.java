package game.model.gameImporter;

import game.model.Game;
import game.model.Player;
import game.model.tile.Tile;
import game.model.transport.Transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameInfoAdapter {

    private Game game;
    public GameInfoAdapter(Game game) {
        this.game = game;
    }

    public List<Tile> getTiles() {
        return new ArrayList<>(game.getMap().getTiles().values());
    }

    public List<Transport> getTransports() {
        List<Player> players = game.getAllPlayers();
        List<Transport> transports = new ArrayList<>();
        for (Player player : players) {
            transports.addAll(player.getTransportManager().getTransports());
        }
        return transports;
    }

}
