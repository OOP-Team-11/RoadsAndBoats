package game.model.gameImporter.importer;

import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

public class GameImporter {

    public GameImporter(RBMap map, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        MapImporter mapImporter = new MapImporter();
        ResourceImporter resourceImporter = new ResourceImporter();

        mapImporter.importMapFromFile(map, bufferedReader);
        resourceImporter.importResourcesFromFile(map, bufferedReader);
    }
}
