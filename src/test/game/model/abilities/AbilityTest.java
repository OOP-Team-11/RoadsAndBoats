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
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        GooseManager gooseManager = new GooseManager();
        MainViewController mainViewController = new MainViewController();
        Player player1 = new Player(new TransportAbilityManager(mainViewController, gooseManager), new PlayerId(2), "gavin");
        Transport donkey = new DonkeyTransport(player1.getPlayerId(), new TransportId());
        RBMap map = new RBMap();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
//        Add 2 geese to same location that donkey is at so he will have 2 followAbilities. One or both follow.
        gooseManager.addGoose(location, new Goose(new GooseId()));
        gooseManager.addGoose(location, new Goose(new GooseId()));
        player1.getTransportManager().addTransport(donkey, location);
        player1.getTransportManager().onTransportSelected(donkey, location);
        assertEquals(2, player1.getTransportManager().getTransportAbilityManager().getAbilityCount());
    }
}
