<<<<<<< HEAD:src/test/tile/TileEdgeTest.java
package tile;

=======
import model.TileEdge;
>>>>>>> acba2a77101109b9b698070ca79838000f53ddfe:src/test/TileEdgeTest.java
import org.junit.Test;
import tile.TileEdge;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileEdgeTest {
<<<<<<< HEAD:src/test/tile/TileEdgeTest.java
    //    Test construction of a model.tile.TileEdge that may and may not connect a river
=======
    //    Test construction of a model.TileEdge that may and may not connect a river
>>>>>>> acba2a77101109b9b698070ca79838000f53ddfe:src/test/TileEdgeTest.java
    @Test
    public void constructorTestCanConnectRiver() {
        TileEdge tileEdge = new TileEdge(true);
        assertTrue(tileEdge.canConnectRiver());
    }

    @Test
    public void constructorTestCannotConnectRiver() {
        TileEdge tileEdge = new TileEdge(false);
        assertFalse(tileEdge.canConnectRiver());
    }

    @Test
    public void setCantConnectRiver() {
        TileEdge tileEdge = new TileEdge(true);
        tileEdge.setCanConnectRiver(false);
        assertFalse(tileEdge.canConnectRiver());
    }

    @Test
    public void setCanConnectRiver() {
        TileEdge tileEdge = new TileEdge(false);
        tileEdge.setCanConnectRiver(true);
        assertTrue(tileEdge.canConnectRiver());
    }
}
