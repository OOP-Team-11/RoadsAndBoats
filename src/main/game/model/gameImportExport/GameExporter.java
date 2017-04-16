package game.model.gameImportExport;

import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.tile.Tile;
import game.model.tinyGame.Game;

import java.util.Set;

public class GameExporter {

    private Game game;
    public GameExporter(Game game) {
        this.game = game;
    }

    public void exportGameToPath(String filePath) {

    }

    public String serializeMap(){
        String serializedMap = "";
        RBMap map = game.getMap();
        Set<Location> locSet = map.getAllLocations();
        Location[] locations = new Location[locSet.size()];
        locations = locSet.toArray(locations);

        for(int i = 0; i < locations.length; i++){
            Tile thisTile = map.getTile(locations[i]);

            serializedMap += locations[i].getExportString() + " ";

            //Terrain
            serializedMap += thisTile.getTerrain();


            serializedMap += "\n";
        }



        return serializedMap;
    }
}
