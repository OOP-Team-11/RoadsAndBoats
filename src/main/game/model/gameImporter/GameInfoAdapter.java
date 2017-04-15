package game.model.gameImporter;

import game.model.tinyGame.Game;
import game.model.Player;
import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.map.RBMap;
import game.model.resources.ResourceManager;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.transport.Transport;

import java.util.*;

public class GameInfoAdapter {

    private Game game;
    public GameInfoAdapter(Game game) {
        this.game = game;
    }

    public List<Exportable> getTiles() {
        List<Exportable> exportables = new ArrayList<>();
        RBMap map = game.getMap();
        for (Location location : game.getMap().getTiles().keySet()) {
            Tile tile = map.getTile(location);
            Terrain terrain = tile.getTerrain();
            RiverConfiguration riverConfiguration = tile.getRiverConfiguration();
//            Exportable exportable = new Exportable(location, terrain.getExportString()
//                    + " " + riverConfiguration.getExportString() + " " + exportables.add(exportable));
        }
        return exportables;
    }

    public List<Exportable> getTransports() {
        List<Player> players = game.getAllPlayers();
        List<Transport> transports = new ArrayList<>();
        List<Exportable> exportables = new ArrayList<>();
        for (Player player : players) {
            Map<TileCompartmentLocation, List<Transport>> transportMap = player.getTransportManager().getTransports();
            Iterator it = transportMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                TileCompartmentLocation tileCompartmentLocation = (TileCompartmentLocation) pair.getKey();
                List<Transport> transportList = transportMap.get(tileCompartmentLocation);
                for (Transport transport : transportList) {
                    Exportable exportable = new Exportable(tileCompartmentLocation.getLocation(), transport.getExportString());
                    exportables.add(exportable);
                }
            }
        }
        return exportables;
    }


}
