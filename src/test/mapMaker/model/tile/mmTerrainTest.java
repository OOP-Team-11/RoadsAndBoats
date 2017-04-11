package mapMaker.model.tile;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmTerrainTest
{
    @Test
    public void canConnectRiver_sea_true()
    {
        boolean connectable = mmTerrain.SEA.canConnectRiver();
        assertTrue(connectable);
    }

    @Test
    public void canConnectRiver_pasture_false()
    {
        boolean connectable = mmTerrain.PASTURE.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_woods_false()
    {
        boolean connectable = mmTerrain.WOODS.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_rock_false()
    {
        boolean connectable = mmTerrain.ROCK.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_desert_false()
    {
        boolean connectable = mmTerrain.DESERT.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_mountain_false()
    {
        boolean connectable = mmTerrain.MOUNTAIN.canConnectRiver();
        assertFalse(connectable);
    }
}
