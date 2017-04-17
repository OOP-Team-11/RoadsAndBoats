package game.model.managers;

import game.controller.MainViewController;
import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.map.RBMap;
import game.model.structures.Structure;
import game.model.structures.StructureId;
import game.model.structures.resourceProducer.PrimaryProducer;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.visitors.StructureManagerVisitor;
import game.model.visitors.TileCompartmentVisitor;
import game.utilities.observable.MapStructureRenderInfoObservable;
import game.utilities.observer.MapStructureRenderInfoObserver;
import game.utilities.observer.WonderPhaseEndedObserver;
import game.view.render.MapStructureRenderInfo;
import game.view.render.StructureRenderInfo;

import java.util.*;

public class StructureManager implements MapStructureRenderInfoObservable, StructureManagerVisitor, WonderPhaseEndedObserver
{
    private StructureAbilityManager structureAbilityManager;
    private Map<TileCompartmentLocation, Structure> structures;
    private List<MapStructureRenderInfoObserver> structureRenderInfoObservers;
    private List<PrimaryProducer> primaryProducers;
    private RBMap map;

    public StructureManager(MainViewController mainViewController, RBMap map) {
        this.structures = new HashMap<>();
        this.structureRenderInfoObservers = new ArrayList<>();
        this.structureAbilityManager = new StructureAbilityManager(mainViewController, map, this);
        this.primaryProducers = new Vector<>();
        this.map = map;
    }

    public void addStructure(TileCompartmentLocation tcl, Structure structure) {
        // POOP to show the OOP
        try {
            PrimaryProducer primaryProducer = (PrimaryProducer) structure;
            this.primaryProducers.add(primaryProducer);
        } catch (ClassCastException e) {
            // ignore
        }
        // TODO: enforce structure type limits
        structures.put(tcl, structure);
        notifyStructureRenderInfoObservers();
    }

    public Structure getStructure(StructureId structureId) {
        for (Structure s : structures.values()) {
            if (s.getId().equals(structureId)) {
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

    private void primaryProducersDoProduction() {
        for (PrimaryProducer primaryProducer : this.primaryProducers) {
            for (Map.Entry<TileCompartmentLocation, Structure> entry : this.structures.entrySet()) {
                if (entry.getValue().equals(primaryProducer)) {
                    TileCompartmentLocation tcl = entry.getKey();
                    TileCompartment tileCompartment = map.getTile(tcl.getLocation()).getTileCompartment(tcl.getTileCompartmentDirection());
                    primaryProducer.produce(tileCompartment);
                }
            }
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

    @Override
    public void addStructureVisit(Structure structure, TileCompartmentLocation tileCompartmentLocation) {
        this.addStructure(tileCompartmentLocation, structure);
    }

    @Override
    public void onWonderPhaseEnded() {
        primaryProducersDoProduction();
    }
}
