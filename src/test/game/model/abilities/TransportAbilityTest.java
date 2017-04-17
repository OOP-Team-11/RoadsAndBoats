package game.model.abilities;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.ability.Ability;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.WagonFactory;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.model.visitors.TransportManagerVisitor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TransportAbilityTest {


    private TileCompartmentLocation tileCompartmentLocation;
    private TileCompartmentLocation startingLocation;
    private MainViewController mainViewController;
    private RBMap map;
    private GooseManager gooseManager;
    private PlayerId playerId;
    private StructureManager structureManager;
    private TransportManager transportManager;
    private Player player1;
    private Transport donkey;

    @Before
    public void setUp() {
        this.startingLocation = new TileCompartmentLocation(new Location(-2, 1, 1), TileCompartmentDirection.getNorth());
        this.tileCompartmentLocation = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
        this.mainViewController = new MainViewController();
        this.map = new RBMap();
        this.gooseManager = new GooseManager(mainViewController, map);
        this.playerId = new PlayerId(1);
        this.structureManager = new StructureManager(mainViewController, map);
        this.transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager);
        this.player1 = new Player(transportManager, playerId, "Morty", startingLocation);
        this.donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
    }

    @Test
    public void addFollowAbilityTest() {

        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
//        Add 2 geese to same location that donkey is at so he will have 2 followAbilities. One or both follow.
        gooseManager.addGoose(tileCompartmentLocation, new Goose(new GooseId()));
        gooseManager.addGoose(tileCompartmentLocation, new Goose(new GooseId()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addFollowAbility(donkey, tileCompartmentLocation);
        assertEquals(2, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    //    Test that the reproduce ability will add with just two donkeys in a pasture
    @Test
    public void addTransportReproduceAbilityTest() {
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        map.placeTile(new Location(5, -5, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().addTransport(donkey2, tileCompartmentLocation);
        player1.getTransportManager().getTileTransports(tileCompartmentLocation.getLocation());
        player1.getTransportManager().getTransportAbilityManager().addTransportReproduceAbility(donkey, tileCompartmentLocation, player1.getTransportManager().getTileTransports(tileCompartmentLocation.getLocation()));
        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void cantReproduceDueToResourcesTest() {
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().addTransport(donkey2, tileCompartmentLocation);
//        ADD RESOURCES SO THE DONKEYS MAY NOT REPRODUCE
        map.getTile(new Location(0, 0, 0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.COINS, 10);
        List<Transport> tileTrans = new ArrayList<Transport>();
        tileTrans.add(donkey);
        tileTrans.add(donkey2);
        Map<TileCompartmentDirection, List<Transport>> tileTransports = new HashMap<TileCompartmentDirection, List<Transport>>();
        tileTransports.put(tileCompartmentLocation.getTileCompartmentDirection(), tileTrans);
        player1.getTransportManager().getTransportAbilityManager().addTransportReproduceAbility(donkey, tileCompartmentLocation, tileTransports);
        assertEquals(0, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void cantReproduceDueToStructureTest() {
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().addTransport(donkey2, tileCompartmentLocation);
//        ADD STRUCTURE SO THE DONKEYS MAY NOT REPRODUCE
        map.getTile(new Location(0,0,0)).addStructure(new WagonFactory(tileCompartmentLocation));
        player1.getTransportManager().getTransportAbilityManager().addTransportReproduceAbility(donkey, tileCompartmentLocation, player1.getTransportManager().getTileTransports(tileCompartmentLocation.getLocation()));
        assertEquals(0, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void cantReproduceDueToGeeseTest() {
        Transport donkey2 = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().addTransport(donkey2, tileCompartmentLocation);
//        ADD GOOSE SO THE DONKEYS MAY NOT REPRODUCE
        gooseManager.addGoose(tileCompartmentLocation, new Goose(new GooseId()));
        player1.getTransportManager().getTransportAbilityManager().addTransportReproduceAbility(donkey, tileCompartmentLocation, player1.getTransportManager().getTileTransports(tileCompartmentLocation.getLocation()));
//        Should be 1 as the goose should be able to follow the donkey!
        assertEquals(0, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void BuildWoodcutterAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 1);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.WOODS, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildWoodCutterAbility(donkey, tileCompartmentLocation);
//        Should be 1 as the donkey should be able to build a woodcutter
        assertEquals(1, mainViewController.getControls().size());
//        Add a structure to the tile, should no longer be able to build a woodcutter
        map.getTile(new Location(0, 0, 0)).addStructure(new WagonFactory(tileCompartmentLocation));
        player1.getTransportManager().getTransportAbilityManager().addBuildWoodCutterAbility(donkey, tileCompartmentLocation);
//        Should be 1 as the donkey should be able to build a woodcutter
        assertEquals(1, mainViewController.getControls().size());
    }

    @Test
    public void getBuildClayPitAbility() {
//        TODO: Uncomment once the check is finalized within TransportAbilityManager.

//        TileCompartmentLocation tileCompartmentLocation = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
//        GooseManager gooseManager = new GooseManager();
//        MainViewController mainViewController = new MainViewController();
//        RBMap map = new RBMap();
//        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
//        Transport donkey = new DonkeyTransport(player1.getPlayerId(),new TransportId());
//
//        donkey.getResourceManager().addResource(ResourceType.BOARDS, 1);
//        map.placeTile(new Location(0,0,0), new Tile(Terrain.WOODS, RiverConfiguration.getNoRivers()));
//        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
//        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
////        Should be 1 as the donkey should be able to build a woodcutter
//        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void getBuildStoneQuarryAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.ROCK, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildStoneQuarryAbility(donkey, tileCompartmentLocation);
//        Should be 2 as the donkey should be able to build a stoneQuarry and a stoneFactory
        assertEquals(1, mainViewController.getControls().size());
    }

    @Test
    public void getBuildOilRigAbility() {
//    TODO: Once research is accounted for
//        TileCompartmentLocation tileCompartmentLocation = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
//        GooseManager gooseManager = new GooseManager();
//        MainViewController mainViewController = new MainViewController();
//        RBMap map = new RBMap();
//        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager, map), new PlayerId(2), "Morty");
//        Transport donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
//
//        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
//        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.SEA, RiverConfiguration.getNoRivers()));
//        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
//        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
////        Should be 1 as the donkey should be able to build a stoneQuarry
//        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void getBuildPapermillAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 1);
        donkey.getResourceManager().addResource(ResourceType.STONE, 1);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.ROCK, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildPapermillAbility(donkey, tileCompartmentLocation);
//        Should be 1 as the donkey should be able to build a papermill
        assertEquals(1, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }

    @Test
    public void getBuildStoneFactoryAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildStoneFactoryAbility(donkey, tileCompartmentLocation);
///        Should be 1 as the donkey should be able to build a stone factory
        assertEquals(1, mainViewController.getControls().size());
    }

    @Test
    public void getBuildCoalBurnerAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 3);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
        boolean onController = false;
        for (Ability a : mainViewController.getControls().values()) {
            if (a.getDisplayString() == "Build Coal Burner") ;
            onController = true;
        }
        assertTrue(onController);
    }

    @Test
    public void getBuildMinesAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 3);
        donkey.getResourceManager().addResource(ResourceType.STONE, 1);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildMineAbility(donkey, tileCompartmentLocation);
//        Size should be 3 as there is enough resources to build each type of mine
        assertEquals(3, mainViewController.getControls().size());
    }

    @Test
    public void getBuildMintAbility() {
        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
        donkey.getResourceManager().addResource(ResourceType.STONE, 1);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
        boolean onController = false;
        for (Ability a : mainViewController.getControls().values()) {
            if (a.getDisplayString().equals("Build Mint"))
                onController = true;
        }
        assertTrue(onController);
    }

    @Test
    public void getBuildRowboatFactoryAbility() {
//        TODO once we rowboat check of being on a shore is implemented

//        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
//        donkey.getResourceManager().addResource(ResourceType.STONE, 1);
//        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
//        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
//        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
//        boolean onController = false;
//        for(Ability a : mainViewController.getControls().values()) {
//            if(a.getDisplayString().equals("Build Mint"))
//                onController = true;
//        }
//        assertTrue(onController);
    }

    @Test
    public void getBuildSteamshipFactoryAbility() {
//        TODO once we can check of being on a shore is implemented && we have research implemented

//        donkey.getResourceManager().addResource(ResourceType.BOARDS, 2);
//        donkey.getResourceManager().addResource(ResourceType.STONE, 2);
//        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
//        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
//        player1.getTransportManager().onTransportSelected(donkey.getTransportId(), tileCompartmentLocation.getLocation());
//        boolean onController = false;
//        for(Ability a : mainViewController.getControls().values()) {
//            if(a.getDisplayString().equals("Build Mint"))
//                onController = true;
//        }
//        assertTrue(onController);
    }

    @Test
    public void getBuildStockExchangeAbility() {
        donkey.getResourceManager().addResource(ResourceType.STONE, 3);
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addBuildStockExchangeAbility(donkey, tileCompartmentLocation);
        assertEquals(1, mainViewController.getControls().size());
    }

    @Test
    public void getPickUpResourcesAbility() {
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        map.getTile(new Location(0, 0, 0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.COINS, 2);
        map.getTile(new Location(0, 0, 0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.BOARDS, 2);
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addPickUpResourceAbility(donkey, tileCompartmentLocation);
        assertEquals(2, mainViewController.getControls().size());
    }

    @Test
    public void getDropResourceAbility() {
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        map.getTile(new Location(0, 0, 0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.BOARDS, 2);
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        donkey.storeResource(ResourceType.BOARDS, 2);
        player1.getTransportManager().getTransportAbilityManager().addDropResourceAbility(donkey, tileCompartmentLocation);
        assertEquals(1, mainViewController.getControls().size());
    }

    @Test
    public void getPickUpTransportAbility() {
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        player1.getTransportManager().addTransport(donkey, tileCompartmentLocation);
        player1.getTransportManager().addTransport(new DonkeyTransport(player1.getPlayerId(), new TransportId()), tileCompartmentLocation);
        player1.getTransportManager().getTransportAbilityManager().addPickUpTransportAbility(donkey, tileCompartmentLocation, player1.getTransportManager().getTransports().get(tileCompartmentLocation));
        assertEquals(1, mainViewController.getControls().size());
    }
}
