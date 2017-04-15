package game.model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.structures.Structure;
import game.model.structures.StructureId;
import game.utilities.observable.MapStructureRenderInfoObservable;
import game.utilities.observer.MapStructureRenderInfoObserver;
import game.view.render.MapStructureRenderInfo;
import game.view.render.StructureRenderInfo;

import java.util.*;

public class StructureManager implements MapStructureRenderInfoObservable
{
    private StructureAbilityManager structureAbilityManager;
    private Map<TileCompartmentLocation, Structure> structures;
    private List<MapStructureRenderInfoObserver> structureRenderInfoObservers;

    public StructureManager(StructureAbilityManager structureAbilityManager)
    {
        this.structures = new HashMap<>();
        this.structureRenderInfoObservers = new ArrayList<>();
        this.structureAbilityManager = structureAbilityManager;
    }

    public void addStructure(TileCompartmentLocation tcl, Structure structure)
    {
        // TODO: enforce structure type limits
        structures.put(tcl, structure);
        notifyStructureRenderInfoObservers();
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

    public Structure getStructure(TileCompartmentLocation tcl)
    {
        return structures.get(tcl);
    }

    public Map<TileCompartmentLocation, Structure> getStructures()
    {
        return this.structures;
    }

    public void addAbilities(TileCompartmentLocation tileCompartmentLocation, Structure structure)
    {
        this.structureAbilityManager.addAbilities(tileCompartmentLocation, structure);
    }

    private void notifyStructureRenderInfoObservers() {
        Map<TileCompartmentLocation, StructureRenderInfo> structureRenderInfoMap = new HashMap<>();
        Iterator it = structures.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentLocation tcl = (TileCompartmentLocation) pair.getKey();
            Structure structure = (Structure) pair.getValue();
            StructureRenderInfo structureRenderInfo = new StructureRenderInfo(structure);
            structureRenderInfoMap.put(tcl, structureRenderInfo);
        }
        MapStructureRenderInfo mapStructureRenderInfo = new MapStructureRenderInfo(structureRenderInfoMap);
        for (MapStructureRenderInfoObserver observer : this.structureRenderInfoObservers) {
            observer.updateMapStructureInfo(mapStructureRenderInfo);
        }

    }

    @Override
    public void attach(MapStructureRenderInfoObserver observer) {
        this.structureRenderInfoObservers.add(observer);
    }

    @Override
    public void detach(MapStructureRenderInfoObserver observer) {
        this.structureRenderInfoObservers.remove(observer);
    }
}
