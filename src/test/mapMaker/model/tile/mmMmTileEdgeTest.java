package mapMaker.model.tile;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmMmTileEdgeTest {

    //    Test construction of a mmTileEdge that may and may not connect a river

    @Test
    public void constructorTestCanConnectRiver() {

        mmTileEdge mmTileEdge = new mmTileEdge(true, true);
        assertTrue(mmTileEdge.canConnectRiver());
    }

    @Test
    public void constructorTestCannotConnectRiver() {
        mmTileEdge mmTileEdge = new mmTileEdge(false, false);
        assertFalse(mmTileEdge.canConnectRiver());
    }

    @Test
    public void setCantConnectRiver() {

        mmTileEdge mmTileEdge = new mmTileEdge(true, true);
        mmTileEdge.setCanConnectRiver(false);
        assertFalse(mmTileEdge.canConnectRiver());
    }

    @Test
    public void setCanConnectRiver() {
        mmTileEdge mmTileEdge = new mmTileEdge(false, false);
        mmTileEdge.setCanConnectRiver(true);
        assertTrue(mmTileEdge.canConnectRiver());
    }
}
