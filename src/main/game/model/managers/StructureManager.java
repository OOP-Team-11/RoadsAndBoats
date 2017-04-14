package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.structures.Structure;
import game.model.structures.StructureId;

import java.util.*;

public class StructureManager
{
    private Player player;
    private StructureAbilityManager structureAbilityManager;
    private Map<Location, Structure> structures;

    public StructureManager(Player player, StructureAbilityManager structureAbilityManager)
    {
        this.player = player;
        this.structures = new HashMap<Location, Structure>();
        this.structureAbilityManager = structureAbilityManager;
    }

    public PlayerId getPlayerId()
    {
        return this.player.getPlayerId();
    }

    public void addStructure(Location loc, Structure structure)
    {
        // TODO: enforce structure type limits
        structures.put(loc, structure);
    }

    public Structure getStructure(StructureId structureId)
    {
        for (Structure s : structures.values())
        {
            if (s.getId().equals(structureId))
            {
                return s;
            }
        }

        return null;
    }

    public Structure getStructure(Location loc)
    {
        return structures.get(loc);
    }

    public Map<Location, Structure> getStructures()
    {
        return this.structures;
    }

    public void addAbilities(TileCompartmentLocation tileCompartmentLocation, Structure structure)
    {
        this.structureAbilityManager.addAbilities(tileCompartmentLocation, structure);
    }
}
