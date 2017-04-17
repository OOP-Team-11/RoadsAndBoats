package game.model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.map.RBMap;
import game.model.movement.Bridge;
import game.utilities.observable.BridgeRenderInfoObservable;
import game.utilities.observer.BridgeRenderInfoObserver;
import game.view.render.BridgeRenderInfo;

import java.util.HashSet;
import java.util.Set;

public class BridgeManager implements BridgeRenderInfoObservable
{
    private final RBMap map;

    private Set<Bridge> bridges;
    private Set<BridgeRenderInfoObserver> bridgeRenderInfoObservers;

    public BridgeManager(RBMap map)
    {
        this.map=map;
        bridges=new HashSet<>();

        bridgeRenderInfoObservers = new HashSet<>();
    }

    public boolean canBuildBridge(Location loc, TileCompartmentDirection comp1, TileCompartmentDirection comp2)
    {
        return map.getTile(loc).canBuildBridge(new Bridge(loc, comp1, comp2));
    }

    public boolean buildBridge(Location loc, TileCompartmentDirection comp1, TileCompartmentDirection comp2)
    {
        if(!canBuildBridge(loc, comp1, comp2))
        {
            return false;
        }

        map.getTile(loc).buildBridge(new Bridge(loc, comp1, comp2));
        return true;
    }

    @Override
    public void attach(BridgeRenderInfoObserver observer)
    {
        this.bridgeRenderInfoObservers.add(observer);
        notifyResearchRenderInfoObservers();
    }

    private void notifyResearchRenderInfoObservers()
    {
        for(BridgeRenderInfoObserver observer: bridgeRenderInfoObservers)
        {
            observer.updateBridgeInfo(getRenderInfo());
        }
    }

    private BridgeRenderInfo getRenderInfo()
    {
        return new BridgeRenderInfo(bridges);
    }

    @Override
    public void detach(BridgeRenderInfoObserver observer)
    {
        this.bridgeRenderInfoObservers.remove(observer);
    }
}
