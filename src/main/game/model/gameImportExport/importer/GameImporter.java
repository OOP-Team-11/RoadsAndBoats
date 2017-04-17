package game.model.gameImportExport.importer;

import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

public class GameImporter {

    public static void importGameFromFile(RBMap map, StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        MapImporter.importMapFromFile(map, bufferedReader);
        ResourceImporter.importResourcesFromFile(map, bufferedReader);
        MineImporter.importMinesFromFile(structureManager, bufferedReader);
        OilRigImporter.importOilRigsFromFile(structureManager, bufferedReader);
        NonSpecialLimitlessStructures.importNonSpecialLimitlessStructures(structureManager, bufferedReader);
    }
}
