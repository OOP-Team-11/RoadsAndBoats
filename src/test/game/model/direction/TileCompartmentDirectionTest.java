package game.model.direction;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TileCompartmentDirectionTest {
    
    @Test
    public void equalTileCompartmentDirections() {
        TileCompartmentDirection loc1 = TileCompartmentDirection.getNorth();
        TileCompartmentDirection loc2 = TileCompartmentDirection.getNorth();
        assertTrue(loc1.equals(loc2));
        assertEquals(loc1.hashCode(),loc2.hashCode());
    }
}
