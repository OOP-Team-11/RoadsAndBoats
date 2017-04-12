package mapMaker.model.tile;

import game.model.TileEdge;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmMmTileEdgeTest {

    //    Test construction of a mmTileEdge that may and may not connect a river

    @Test
    public void constructorTestCanConnectRiver() {

        TileEdge mmTileEdge = new TileEdge(true, true);
        assertTrue(mmTileEdge.canConnectRiver());
    }

    @Test
    public void constructorTestCannotConnectRiver() {
        TileEdge mmTileEdge = new TileEdge(false, false);
        assertFalse(mmTileEdge.canConnectRiver());
    }

    @Test
    public void setCantConnectRiver() {

        TileEdge mmTileEdge = new TileEdge(true, true);
        mmTileEdge.setCanConnectRiver(false);
        assertFalse(mmTileEdge.canConnectRiver());
    }

    @Test
    public void setCanConnectRiver() {
        TileEdge mmTileEdge = new TileEdge(false, false);
        mmTileEdge.setCanConnectRiver(true);
        assertTrue(mmTileEdge.canConnectRiver());
    }
}
