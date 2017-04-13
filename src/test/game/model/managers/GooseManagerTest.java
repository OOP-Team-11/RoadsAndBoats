package game.model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.tile.Tile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GooseManagerTest {

    @Test
    public void initializeAndAddGoose() {
        GooseManager gm = new GooseManager();
        Goose goose1 = new Goose(new GooseId());
        Location loc = new Location(0,0,0);
        TileCompartmentDirection tcd1 = TileCompartmentDirection.getNorth();
        TileCompartmentDirection tcd2 = TileCompartmentDirection.getEast();
        gm.addGoose(loc, goose1, tcd1);
        gm.addGoose(loc, goose1, tcd2);
        assertEquals(gm.getMapGeese().get(loc).size(), 2);
    }

    @Test
    public void removeGooseTest() {
        GooseManager gm = new GooseManager();
        Goose goose = new Goose(new GooseId());
        Location loc = new Location(0,0,0);
        TileCompartmentDirection tcd1 = TileCompartmentDirection.getNorth();
        gm.addGoose(loc, goose, tcd1);
        gm.removeGoose(loc, goose);
        assertEquals(gm.getMapGeese().size(), 0);
    }
}
