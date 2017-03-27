import model.Terrain;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TerrainTest
{
    @Test
    public void canConnectRiver_sea_true()
    {
        boolean connectable = Terrain.Sea.canConnectRiver();
        assertTrue(connectable);
    }

    @Test
    public void canConnectRiver_pasture_false()
    {
        boolean connectable = Terrain.Pasture.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_woods_false()
    {
        boolean connectable = Terrain.Woods.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_rock_false()
    {
        boolean connectable = Terrain.Rock.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_desert_false()
    {
        boolean connectable = Terrain.Desert.canConnectRiver();
        assertFalse(connectable);
    }

    @Test
    public void canConnectRiver_mountain_false()
    {
        boolean connectable = Terrain.Mountain.canConnectRiver();
        assertFalse(connectable);
    }
}
