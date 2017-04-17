package game.model.gameImportExport.importer;

import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

public class GameImporter {

    public static void importGameFromFile(RBMap map, StructureManager structureManager, GooseManager gooseManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        MapImporter.importMapFromFile(map, bufferedReader);
        ResourceImporter.importResourcesFromFile(map, bufferedReader);
        map.finalizeMap();
        MineImporter.importMinesFromFile(structureManager, bufferedReader);
        OilRigImporter.importOilRigsFromFile(structureManager, bufferedReader);
        NonSpecialLimitlessStructures.importNonSpecialLimitlessStructures(structureManager, bufferedReader);
        NonSpecialStructuresWithLimits.importNonSpecialStructuresWithLimits(structureManager, bufferedReader);
        GooseImporter.importGoose(gooseManager, bufferedReader);
    }
}
