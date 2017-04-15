package model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
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
        Goose goose2 = new Goose(new GooseId());
        Location loc = new Location(0,0,0);
        TileCompartmentDirection tcd1 = TileCompartmentDirection.getNorth();
        TileCompartmentDirection tcd2 = TileCompartmentDirection.getEast();
        gm.addGoose(new TileCompartmentLocation(loc, tcd1), goose1);
        gm.addGoose(new TileCompartmentLocation(loc, tcd1), goose2);
        assertEquals(gm.getMapGeese().get(new TileCompartmentLocation(loc, tcd1)).size(), 2);
    }

    @Test
    public void removeGooseTest() {
        GooseManager gm = new GooseManager();
        Goose goose = new Goose(new GooseId());
        Location loc = new Location(0,0,0);
        TileCompartmentDirection tcd1 = TileCompartmentDirection.getNorth();
        gm.addGoose(new TileCompartmentLocation(loc, tcd1), goose);
        gm.removeGoose(new TileCompartmentLocation(loc, tcd1), goose);
        assertEquals(gm.getMapGeese().size(), 0);
    }
}
