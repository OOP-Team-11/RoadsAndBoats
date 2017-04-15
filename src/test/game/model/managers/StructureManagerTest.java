package model.managers;

import game.model.Player;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureAbilityManager;
import game.model.managers.StructureManager;
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
    private TileCompartmentLocation tileCompartmentLocation;

    @Before
    public void setUp()
    {
        abilityManager=new StructureAbilityManager(null);
        structureManager=new StructureManager(abilityManager);
        tileCompartmentLocation = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
    }

    @Test
    public void addStructure_OneAdded_SuccessfullyAddedExactlyOne()
    {
        structureManager.addStructure(tileCompartmentLocation,
                new Mine(0, 0));

        assertEquals(1, structureManager.getStructures().size());
    }

    @Test
    public void getStructure_byLocation_correctlyFound()
    {
        Mine mine = new Mine(0, 0);
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,-1,1), TileCompartmentDirection.getNorth());
        structureManager.addStructure(tcl,
                mine);

        assertEquals(mine, structureManager.getStructure(tcl));
    }

    @Test
    public void getStructure_byId_correctlyFound()
    {
        Mine mine = new Mine(0, 0);

        structureManager.addStructure(tileCompartmentLocation,
                mine);

        assertEquals(mine, structureManager.getStructure(mine.getId()));
    }

    @Test
    public void addStructure_TwoAdded_SuccessfullyAddedExactlyTwo()
    {
        structureManager.addStructure(tileCompartmentLocation,
                new Mine(0, 0));

        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,-1,1), TileCompartmentDirection.getNorth());

        structureManager.addStructure(tcl,
                new OilRig());

        assertEquals(2, structureManager.getStructures().size());
    }
}
