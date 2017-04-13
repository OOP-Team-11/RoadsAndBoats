package model.gameImporter;

import game.model.Game;
import game.model.Player;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.gameImporter.Exportable;
import game.model.gameImporter.GameInfoAdapter;
import game.model.gameImporter.MapImporter;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.resources.Gold;
import game.model.tile.Tile;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.transport.WagonTransport;
import game.utilities.exceptions.MalformedMapFileException;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameInfoAdapterTest {

    Game game;
    private static final String FILENAME = "testFiles/gameInfoAdapterTest.txt";

    @Before
    public void setUp() {
        MapImporter mapImporter = new MapImporter();
        RBMap map = new RBMap();
        Player player1 = new Player();
        Player player2 = new Player();
        TransportManager transportManager = player1.getTransportManager();
        Transport transport = new WagonTransport(player1.getPlayerId(), new TransportId(), 1, 1);
        transport.getResourceManager().addResource(new Gold(), 2);
        transportManager.addTransport(transport, new Location(0,0,0), TileCompartmentDirection.getNorth());
        game = new Game(map, player1, player2);
    }

    @Test
    public void getTransports() {
        GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(game);
        List<Exportable> exportedTransports = gameInfoAdapter.getTransports();
        for (Exportable exportable : exportedTransports) {
            assertEquals(exportable.getExportValue(), "( 0 0 0 ) 1 WAGON GOLD:2");
        }
    }
}
