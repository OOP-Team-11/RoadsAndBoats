package game.model.gameImportExport.importer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.wonder.WonderManager;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class WonderImporter {

    static void importWonderFromFile(WonderManager wonderManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN WONDER-----"))  throw new MalformedMapFileException("-----BEGIN WONDER----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END WONDER-----")) {
                foundEOF = true;
                break;
            }

            PlayerId playerId = getPlayerId(line);
            if (playerId == null)
                wonderManager.addNeutralBrick();
            else
                wonderManager.addBrick(playerId);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END WONDER----- not found");
    }

    private static PlayerId getPlayerId(String brickString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(brickString, "PLAYERID=(-?[0-9])");
        if (m.find()) {
            String playerIdString = m.group(1);
            try {
                int id = Integer.parseInt(playerIdString);
                if (id < 0) return null;
                else return new PlayerId(id);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse wonder brick playerId: " + brickString);
            }
        }
        throw new MalformedMapFileException("Could not parse wonder brick playerId: " + brickString);
    }

}
