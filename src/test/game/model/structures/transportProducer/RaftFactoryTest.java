package model.structures.transportProducer;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RaftFactory;
import game.model.tile.TileCompartment;
import game.model.transport.*;
import game.model.visitors.TransportManagerVisitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RaftFactoryTest {

    private static final int TRUNKS_REQ = 2;

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
        this.startingLocation = new TileCompartmentLocation(new Location(-2,1,1), TileCompartmentDirection.getNorth());
        this.tileCompartmentLocation = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        this.mainViewController = new MainViewController();
        this.map = new RBMap();
        this.gooseManager = new GooseManager(mainViewController, map);
        this.playerId = new PlayerId(1);
        this.structureManager = new StructureManager(mainViewController, map);
        this.transportManager = mock(TransportManager.class);
        this.player1 = new Player(transportManager, playerId, "Morty", startingLocation);
        this.donkey = new DonkeyTransport(player1.getPlayerId(),new TransportId());
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        RaftFactory raftFactory = new RaftFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());

        steamship.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        assertTrue(raftFactory.produce(transportManager, steamship, tcl));
        verify(transportManager, times(1)).addTransportVisit(any(RaftTransport.class), any(TileCompartmentLocation.class));

//        try {
//            RaftTransport raft = (RaftTransport) raftFactory.produce(steamship);
//        } catch (ClassCastException c) {
//            fail();
//        }
//        assertEquals(steamship.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void produceNothingUsingTransport() {
//        RaftFactory raftFactory = new RaftFactory();
//        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
//        try {
//            RaftTransport raft = (RaftTransport) raftFactory.produce(steamship);
//        } catch (ClassCastException c) {
//            assertEquals(steamship.getResourceCount(ResourceType.TRUNKS), 0);
//        }
    }

    @Test
    public void produceUsingTileCompartment() {
        RaftFactory raftFactory = new RaftFactory(tileCompartmentLocation);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        assertTrue(raftFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        RaftFactory raftFactory = new RaftFactory(tileCompartmentLocation);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(raftFactory.produce(tileCompartment));
    }

}