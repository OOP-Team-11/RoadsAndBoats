package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.structures.resourceProducer.primaryProducer.OilRig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StructureManagerTest
{
    private StructureManager structureManager;
    private Player player;
    private StructureAbilityManager abilityManager;

    @Before
    public void setUp()
    {
        abilityManager=new StructureAbilityManager(null);
        structureManager=new StructureManager(abilityManager);
    }

    @Test
    public void addStructure_OneAdded_SuccessfullyAddedExactlyOne()
    {
        structureManager.addStructure(new Location(0,0,0),
                new Mine(0, 0));

        assertEquals(1, structureManager.getStructures().size());
    }

    @Test
    public void getStructure_byLocation_correctlyFound()
    {
        Mine mine = new Mine(0, 0);

        structureManager.addStructure(new Location(0,-1,1),
                mine);

        assertEquals(mine, structureManager.getStructure(new Location(0,-1,1)));
    }

    @Test
    public void getStructure_byId_correctlyFound()
    {
        Mine mine = new Mine(0, 0);

        structureManager.addStructure(new Location(0,0,0),
                mine);

        assertEquals(mine, structureManager.getStructure(mine.getId()));
    }

    @Test
    public void addStructure_TwoAdded_SuccessfullyAddedExactlyTwo()
    {
        structureManager.addStructure(new Location(0,0,0),
                new Mine(0, 0));

        structureManager.addStructure(new Location(3,-1,-2),
                new OilRig());

        assertEquals(2, structureManager.getStructures().size());
    }
}
