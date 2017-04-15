package game.model.direction;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TileCompartmentLocationTest {

    @Test
    public void equalTileCompartmentLocations() {
        TileCompartmentLocation tcl1 = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        TileCompartmentLocation tcl2 = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        assertEquals(tcl1.hashCode(), tcl2.hashCode());
        assertTrue(tcl1.equals(tcl2));
    }
}
