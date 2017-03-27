import model.Terrain;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TerrainTest
{
    @Test
    public void canConnectRiver_sea_true()
    {
        boolean connectable = Terrain.SEA.canConnectRiver();
        assertTrue(connectable);
    }

    @Test
    public void canConnectRiver_pasture_false()
    {
        boolean connectable = Terrain.PASTURE.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_woods_false()
    {
        boolean connectable = Terrain.WOODS.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_rock_false()
    {
        boolean connectable = Terrain.ROCK.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_desert_false()
    {
        boolean connectable = Terrain.DESERT.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_mountain_false()
    {
        boolean connectable = Terrain.MOUNTAIN.canConnectRiver();
        assertFalse(connectable);
    }
}
