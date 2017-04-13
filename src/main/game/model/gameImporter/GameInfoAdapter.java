package game.model.gameImporter;

import game.model.Game;
import game.model.Player;
import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.structures.Structure;
import game.model.tile.Tile;
import game.model.transport.Transport;
import game.model.transport.TransportLocation;

import java.util.*;

public class GameInfoAdapter {

    private Game game;
    public GameInfoAdapter(Game game) {
        this.game = game;
    }

    public List<Tile> getTiles() {
        return new ArrayList<>(game.getMap().getTiles().values());
    }

    public List<Exportable> getTransports() {
        List<Player> players = game.getAllPlayers();
        List<Transport> transports = new ArrayList<>();
        List<Exportable> exportables = new ArrayList<>();
        for (Player player : players) {
            Map<Location, List<TransportLocation>> transportMap = player.getTransportManager().getTransports();
            Iterator it = transportMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Location location = (Location) pair.getKey();
                List<TransportLocation> transportLocationList = transportMap.get(location);
                for (TransportLocation transportLocation : transportLocationList) {
                    Exportable exportable = new Exportable(location, transportLocation.getTransport().getExportString());
                    exportables.add(exportable);
                }
            }
        }
        return exportables;
    }


}
