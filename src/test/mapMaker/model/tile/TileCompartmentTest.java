package mapMaker.model.tile;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileCompartmentTest {

    //    Test construction of a TileCompartment with and without water
    @Test
    public void constructorTestNoWater() {
        TileCompartment tileComp1 = new TileCompartment(false);
        assertFalse(tileComp1.hasWater());
    }

    @Test
    public void constructorTestHasWater() {
        TileCompartment tileComp2 = new TileCompartment(true);
        assertTrue(tileComp2.hasWater());
    }
}
