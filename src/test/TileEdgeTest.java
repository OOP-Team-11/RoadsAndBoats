import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileEdgeTest {
    //    Test construction of a TileEdge that may and may not connect a river
    @Test
    public void constructorTest() {
        TileEdge tileEdge1 = new TileEdge(true);
        assertTrue(tileEdge1.canConnectRiver());
        // Set it to false and check for opposite response
        tileEdge1.setCanConnectRiver(false);
        assertFalse(tileEdge1.canConnectRiver());

    }
}
