<<<<<<< HEAD:src/test/tile/TileCompartmentTest.java
package tile;

=======
import model.TileCompartment;
>>>>>>> acba2a77101109b9b698070ca79838000f53ddfe:src/test/TileCompartmentTest.java
import org.junit.Test;
import tile.TileCompartment;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileCompartmentTest {

<<<<<<< HEAD:src/test/tile/TileCompartmentTest.java
    //    Test construction of a model.tile.TileCompartment with and without water
=======
    //    Test construction of a model.TileCompartment with and without water
>>>>>>> acba2a77101109b9b698070ca79838000f53ddfe:src/test/TileCompartmentTest.java
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
