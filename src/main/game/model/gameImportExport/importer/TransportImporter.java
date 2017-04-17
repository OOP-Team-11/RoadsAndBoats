package game.model.gameImportExport.importer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.TransportManager;
import game.model.resources.Goose;
import game.model.resources.ResourceType;
import game.model.transport.*;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;
import static game.model.gameImportExport.importer.ParseUtilities.getTransportTypeForString;

public class TransportImporter {

    static void importTransportsFromFile(TransportManager player1TransportManager, TransportManager player2TransportManager, GooseManager gooseManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN TRANSPORT-----"))  throw new MalformedMapFileException("-----BEGIN TRANSPORT----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END TRANSPORT-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            int gooseNum = getGooseNum(line);
            PlayerId playerId = getPlayerId(line);
            Transport transport = createTransportWithResources(line, playerId);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            List<Goose> geese = gooseManager.getMapGeese().get(tcl);
            if (geese.size() < gooseNum) throw new MalformedMapFileException("Transport has more geese than are on the tile: " + line);
            List<Goose> geeseToAdd = new ArrayList<>();
            for (int i = 0; i < gooseNum; i++) {
                geeseToAdd.add(geese.get(i));
            }
            transport.addFollowers(geeseToAdd);
            if (playerId.getPlayerIdNumber() == 1)
                player1TransportManager.addTransport(transport, tcl);
            else
                player2TransportManager.addTransport(transport, tcl);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END TRANSPORT----- not found");
    }

    private static TransportType getTransportType(String transportString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(transportString, "([^\\s]+)");
        if (m.find()) {
            String transportTypeString = m.group(1);
            TransportType transportType = getTransportTypeForString(transportTypeString);
            if (transportType == null) throw new MalformedMapFileException("Could not identify transport type: " + transportString);
            return transportType;
        }

        throw new MalformedMapFileException("Could not identify transport type: " + transportString);
    }

    private static Location getLocation(String structureString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(structureString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for transport: " + structureString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in transport string: " + structureString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for transport: " + structureString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed transport string, could not parse location ints: " + structureString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + structureString);
    }

    private static TileCompartmentDirection getTileCompartmentDirection(String structureString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(structureString, "\\([^)]*\\) ([A-Z])");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = ParseUtilities.getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for transport: " + structureString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed transport string: " + structureString);
    }

    private static PlayerId getPlayerId(String transporterString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(transporterString, "PLAYERID=([0-9])");
        if (m.find()) {
            String playerIdString = m.group(1);
            try {
                return new PlayerId(Integer.parseInt(playerIdString));
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse transport playerId: " + transporterString);
            }
        }
        throw new MalformedMapFileException("Could not parse transport playerId: " + transporterString);
    }

    private static int getGooseNum(String transporterString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(transporterString, "GOOSENUM=([0-9]{1,2})");
        if (m.find()) {
            String gooseNumString = m.group(1);
            try {
                return Integer.parseInt(gooseNumString);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse transport goose num: " + transporterString);
            }
        }
        throw new MalformedMapFileException("Could not parse transport goose num: " + transporterString);
    }

    private static Transport createTransportWithResources(String transporterString, PlayerId playerId) throws MalformedMapFileException {
        TransportType transportType = getTransportTypeForString(transporterString.split(" ")[0]);
        Transport transport = miniTransportFactory(transportType, playerId);

        Matcher m = getMatcherForPatternInString(transporterString, "(\\[.*\\])");
        if (m.find()) {
            String resourcesString = m.group(1);
            Matcher resourceMatcher = getMatcherForPatternInString(resourcesString, "([A-Z][^=]*)=([0-9]{1,2})");

            while (resourceMatcher.find()) {
                try {
                    String resourceName = resourceMatcher.group(1);
                    String stringAmount = resourceMatcher.group(2);
                    ResourceType resourceType = ParseUtilities.getResourceTypeByString(resourceName);
                    int amount = Integer.parseInt(stringAmount);
                    transport.storeResource(resourceType, amount);
                } catch (NumberFormatException e) {
                    throw new MalformedMapFileException("Could not parse resource amount for transport: " + transporterString);
                }
            }

            Matcher transportMatcher = getMatcherForPatternInString(resourcesString, "TRANSPORT_TYPE=(.*)");
            while (transportMatcher.find()) {
                TransportType transportType1 = getTransportType(transportMatcher.group(1));
                Transport transport1 = miniTransportFactory(transportType1, playerId);
                transport.storeTransport(transport1);
            }

            return transport;
        }

        return transport;
    }

    private static Transport miniTransportFactory(TransportType transportType, PlayerId playerId) {
        switch (transportType) {
            case DONKEY:
                return new DonkeyTransport(playerId, new TransportId());
            case RAFT:
                return new RaftTransport(playerId, new TransportId());
            case ROWBOAT:
                return new RowboatTransport(playerId, new TransportId());
            case STEAMSHIP:
                return new SteamShipTransport(playerId, new TransportId());
            case TRUCK:
                return new TruckTransport(playerId, new TransportId());
            case WAGON:
                return new WagonTransport(playerId, new TransportId());
            default:
                return null;
        }
    }
}
