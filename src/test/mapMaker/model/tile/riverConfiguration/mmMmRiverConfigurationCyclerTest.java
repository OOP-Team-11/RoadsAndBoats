package mapMaker.model.tile.riverConfiguration;

import mapMaker.model.tile.mmTerrain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class mmMmRiverConfigurationCyclerTest {

    private mmRiverConfigurationCycler mmRiverConfigurationCycler;

    @Before
    public void setUp()
    {
        mmRiverConfigurationCycler =new mmRiverConfigurationCycler(mmTerrain.MOUNTAIN);
    }

    @Test
    public void getCurrent_beforeNext_noRiver()
    {
        mmRiverConfiguration rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getNoRivers(), rc);
    }

    @Test
    public void getCurrent_6Nexts_properConfigurations()
    {
        mmRiverConfiguration rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getNoRivers(), rc);
        mmRiverConfigurationCycler.next();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getSpringHead(), rc);
        mmRiverConfigurationCycler.next();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getAdjacentFaces(), rc);
        mmRiverConfigurationCycler.next();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getSkipAFace(), rc);
        mmRiverConfigurationCycler.next();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getOppositeFaces(), rc);
        mmRiverConfigurationCycler.next();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getEveryOtherFace(), rc);
        mmRiverConfigurationCycler.next();
    }

    @Test
    public void getCurrent_6Previous_properConfigurations()
    {
        mmRiverConfiguration rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getNoRivers(), rc);
        mmRiverConfigurationCycler.previous();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getEveryOtherFace(), rc);
        mmRiverConfigurationCycler.previous();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getOppositeFaces(), rc);
        mmRiverConfigurationCycler.previous();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getSkipAFace(), rc);
        mmRiverConfigurationCycler.previous();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getAdjacentFaces(), rc);
        mmRiverConfigurationCycler.previous();

        rc = mmRiverConfigurationCycler.getCurrent();
        assertEquals(mmRiverConfiguration.getSpringHead(), rc);
        mmRiverConfigurationCycler.previous();
    }

    @Test
    public void next_calledOnce_differentConfiguration()
    {
        mmRiverConfiguration rc = mmRiverConfigurationCycler.getCurrent();
        mmRiverConfigurationCycler.next();

        assertNotEquals(rc, mmRiverConfigurationCycler.getCurrent());
    }

    @Test
    public void previous_calledOnce_differentConfiguration()
    {
        mmRiverConfiguration rc = mmRiverConfigurationCycler.getCurrent();
        mmRiverConfigurationCycler.previous();

        assertNotEquals(rc, mmRiverConfigurationCycler.getCurrent());
    }
}
