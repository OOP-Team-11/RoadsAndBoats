package game.model.abilities;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.TransportAbilityManager;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.WagonFactory;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransportAbilityTest {

    @Test
    public void addFollowAbilityTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(),new TransportId());
//        Add 2 geese to same location that donkey is at so he will have 2 followAbilities. One or both follow.
        gooseManager.addGoose(location, new Goose(new GooseId()));
        gooseManager.addGoose(location, new Goose(new GooseId()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().onTransportSelected(donkey, location);
        assertEquals(2, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

//    Test that the reproduce ability will add with just two donkeys in a pasture
    @Test
    public void addTransportReproduceAbilityTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(),new TransportId());
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(),new TransportId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().addTransport(donkey2, location);
        player1.getTransportManager().onTransportSelected(donkey, location);
        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void cantReproduceDueToResourcesTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().addTransport(donkey2, location);
//        ADD RESOURCES SO THE DONKEYS MAY NOT REPRODUCE
        map.getTile(new Location(0,0,0)).getResourceManager().addResource(ResourceType.COINS, 10);
        player1.getTransportManager().onTransportSelected(donkey, location);
        assertEquals(0, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }
    @Test
    public void cantReproduceDueToStructureTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().addTransport(donkey2, location);
//        ADD STRUCTURE SO THE DONKEYS MAY NOT REPRODUCE
        map.getTile(new Location(0,0,0)).addStructure(new WagonFactory());
        player1.getTransportManager().onTransportSelected(donkey, location);
        assertEquals(0, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void cantReproduceDueToGeeseTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(),new TransportId());
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(),new TransportId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().addTransport(donkey2, location);
//        ADD GOOSE SO THE DONKEYS MAY NOT REPRODUCE
        gooseManager.addGoose(location, new Goose(new GooseId()));
        player1.getTransportManager().onTransportSelected(donkey, location);
//        Should be 1 as the goose should be able to follow the donkey!
        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }
}
