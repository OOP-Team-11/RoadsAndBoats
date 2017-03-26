import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TileEdgeTest {
    //    Test construction of a TileEdge that may and may not connect a river
    @Test
    public void constructorTest() {
        TileEdge tileEdge1 = new TileEdge(true);
        assertTrue(tileEdge1.canConnectRiver());
        // Set it to false and check for opposite response
        tileEdge1.setCanConnectRiver(false);
        assertTrue(!tileEdge1.canConnectRiver());

    }
}
