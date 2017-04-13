package model.gameImporter;

import game.model.Game;
import game.model.Player;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.gameImporter.Exportable;
import game.model.gameImporter.GameInfoAdapter;
import game.model.gameImporter.MapImporter;
import game.model.managers.AbilityManager;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
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
    RBMap map;
    private static final String FILENAME = "testFiles/gameInfoAdapterTest.txt";

    @Before
    public void setUp() {
        map = new RBMap();
        Player player1 = new Player(null);
        Player player2 = new Player(null);
        TransportManager transportManager = player1.getTransportManager();
        Transport transport = new WagonTransport(player1.getPlayerId(), new TransportId(), 1, 1);
        transport.getResourceManager().addResource(ResourceType.GOLD, 2);
        transportManager.addTransport(transport, new Location(0,0,0), TileCompartmentDirection.getNorth());
        game = new Game(map, player1, player2, null);
    }

    @Test
    public void getTransports() {
        GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(game);
        List<Exportable> exportedTransports = gameInfoAdapter.getTransports();
        assertEquals(exportedTransports.size(), 1);
        assertEquals(exportedTransports.get(0).getExportValue(), "( 0 0 0 ) 1 WAGON GOLD:2 ");
    }

    @Test
    public void getTiles() {
        Tile tile = new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers());
        map.placeTile(new Location(0,0,0), tile);
        GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(game);
        List<Exportable> exportedTiles = gameInfoAdapter.getTiles();
        assertEquals(exportedTiles.size(), 1);
        assertEquals(exportedTiles.get(0).getExportValue(), "( 0 0 0 ) PASTURE ");
    }

}
