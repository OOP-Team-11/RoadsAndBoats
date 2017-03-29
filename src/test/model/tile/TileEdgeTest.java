package model.tile;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileEdgeTest {

    //    Test construction of a TileEdge that may and may not connect a river

    @Test
    public void constructorTestCanConnectRiver() {

        TileEdge tileEdge = new TileEdge(true, true);
        assertTrue(tileEdge.canConnectRiver());
    }

    @Test
    public void constructorTestCannotConnectRiver() {
        TileEdge tileEdge = new TileEdge(false, false);
        assertFalse(tileEdge.canConnectRiver());
    }

    @Test
    public void setCantConnectRiver() {

        TileEdge tileEdge = new TileEdge(true, true);
        tileEdge.setCanConnectRiver(false);
        assertFalse(tileEdge.canConnectRiver());
    }

    @Test
    public void setCanConnectRiver() {
        TileEdge tileEdge = new TileEdge(false, false);
        tileEdge.setCanConnectRiver(true);
        assertTrue(tileEdge.canConnectRiver());
    }
}
