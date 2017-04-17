package game.model.abilities;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.*;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbilityTest {

    @Test
    public void addFollowAbilityTest() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(2);
        StructureManager structureManager = new StructureManager(mainViewController, map);
        ResearchManager researchManager = new ResearchManager(tcl.getLocation(), playerId);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager, researchManager);
        Player player1 = new Player(transportManager, new PlayerId(2), "gavin", tcl);
        Transport donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
//        Add 2 geese to same location that donkey is at so he will have 2 followAbilities. One or both follow.
        gooseManager.addGoose(tcl, new Goose(new GooseId()));
        gooseManager.addGoose(tcl, new Goose(new GooseId()));
        player1.getTransportManager().addTransport(donkey, tcl);
        player1.getTransportManager().getTransportAbilityManager().addFollowAbility(donkey, tcl);
        assertEquals(2, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }
}
