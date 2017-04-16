package game.model.gameImportExport;

import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.tinyGame.Game;

import java.util.Set;

public class GameExporter {

    private Game game;
    public GameExporter(Game game) {
        this.game = game;
    }

    public void exportGameToPath(String filePath) {

    }

    private String serializeMap(){
        String serializedMap = "";
        RBMap map = game.getMap();
        Set<Location> locSet = map.getAllLocations();
        Location[] locations = new Location[locSet.size()];
        locations = locSet.toArray(locations);

        for(int i = 0; i < locations.length; i++){
            System.out.println(locations[i].toString());
        }



        return serializedMap;
    }
}
