package game.model.gameImporter.importer;

import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

public class GameImporter {

    public void importGameFromFile(RBMap map, StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        MapImporter mapImporter = new MapImporter();
        ResourceImporter resourceImporter = new ResourceImporter();
        MineImporter mineImporter = new MineImporter();

        mapImporter.importMapFromFile(map, bufferedReader);
        resourceImporter.importResourcesFromFile(map, bufferedReader);
        mineImporter.importMinesFromFile(structureManager, bufferedReader);
    }
}
