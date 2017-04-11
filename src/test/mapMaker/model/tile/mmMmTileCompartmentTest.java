package mapMaker.model.tile;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmMmTileCompartmentTest {

    //    Test construction of a mmTileCompartment with and without water
    @Test
    public void constructorTestNoWater() {
        mmTileCompartment tileComp1 = new mmTileCompartment(false);
        assertFalse(tileComp1.hasWater());
    }

    @Test
    public void constructorTestHasWater() {
        mmTileCompartment tileComp2 = new mmTileCompartment(true);
        assertTrue(tileComp2.hasWater());
    }
}
