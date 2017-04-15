package model.gameImporter;

import game.model.direction.TileCompartmentLocation;
import game.model.tinyGame.Game;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.gameImporter.Exportable;
import game.model.gameImporter.GameInfoAdapter;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.transport.WagonTransport;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GameInfoAdapterTest {

    Game game;
    RBMap map;
    private static final String FILENAME = "testFiles/gameInfoAdapterTest.txt";

    @Before
    public void setUp() {
        map = new RBMap();
        Player player1 = new Player(null, new PlayerId(1), "gavin");
        Player player2 = new Player(null, new PlayerId(2), "not gavin");
        TransportManager transportManager = player1.getTransportManager();
        Transport transport = new WagonTransport(player1.getPlayerId(), new TransportId());
        transport.getResourceManager().addResource(ResourceType.GOLD, 2);
        transportManager.addTransport(transport, new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth()));
        game = new Game(map, player1, player2, new GooseManager(), mock(StructureManager.class));
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
        Tile tile = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        tile.addResource(ResourceType.GOLD, 2);
        map.placeTile(new Location(0,0,0), tile);
        GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(game);
        List<Exportable> exportedTiles = gameInfoAdapter.getTiles();
        assertEquals(exportedTiles.size(), 1);
        assertEquals(exportedTiles.get(0).getExportValue(), "( 0 0 0 ) PASTURE ( 1 ) GOLD:2 ");
    }

    @Test
    public void getTiles_2() {
        Tile tile = new Tile(Terrain.PASTURE, new RiverConfiguration(2, 4, 6));
        tile.addResource(ResourceType.PAPER, 1);
        tile.addResource(ResourceType.BOARDS, 4);
        tile.addResource(ResourceType.CLAY, 2);
        map.placeTile(new Location(0,0,0), tile);
        GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(game);
        List<Exportable> exportedTiles = gameInfoAdapter.getTiles();
        assertEquals(exportedTiles.size(), 1);
        assertEquals(exportedTiles.get(0).getExportValue(), "( 0 0 0 ) PASTURE ( 2 4 6 ) PAPER:1 BOARDS:4 CLAY:2 ");
    }

}
