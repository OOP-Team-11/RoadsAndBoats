import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TileCompartmentTest {

    //    Test construction of a TileCompartment with and without water
    @Test
    public void constructorTest() {
        TileCompartment tileComp1 = new TileCompartment(false);
        assertTrue(!tileComp1.hasWater());
        TileCompartment tileComp2 = new TileCompartment(true);
        assertTrue(tileComp2.hasWater());

    }
}
