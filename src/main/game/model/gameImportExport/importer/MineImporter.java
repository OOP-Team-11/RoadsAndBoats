package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

public class MineImporter {

    public void importMinesFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("BEGIN MINE"))  throw new MalformedMapFileException("BEGIN MINE not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("END MINE")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line);
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            Mine mine = createMineWithResources(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            structureManager.addStructure(tcl, mine);
        }

        if (!foundEOF) throw new MalformedMapFileException("END RESOURCE not found");
    }

    private String stripIdentifier(String mineString) {
        return mineString;
    }

    private Location getLocation(String mineString) {
        return null;
    }

    private TileCompartmentDirection getTileCompartmentDirection(String mineString) {
        return null;
    }

    private Mine createMineWithResources(String mineString) {
        return new Mine(1,1);
    }
}
