package game.model.managers;

import game.model.direction.Location;
import game.utilities.observable.WallInformationObservabel;
import game.utilities.observer.WallRenderInfoObserver;
import game.view.MainView;
import game.view.render.WallInfo;
import game.view.render.WallRenderInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WallManager implements WallInformationObservabel{

    private HashMap<Location,WallInfo> renderInformation;
    private MainView mainView;
    private List<WallRenderInfoObserver> wallRenderInfoObservers;

    public WallManager(){
        this.renderInformation = new HashMap<>();
        this.wallRenderInfoObservers = new ArrayList<>();
    }

    public void addNewWall(Location location, int type, int compartment){
        this.renderInformation.put(location, new WallInfo(type,compartment));
        notifyWallInfoObservers();
    }

    public void notifyWallInfoObservers() {

        // copy over everything
        HashMap<Location,WallInfo> copy = new HashMap<>();
        for (Map.Entry<Location, WallInfo> entry : renderInformation.entrySet()) {
            WallInfo copyWallInfo = new WallInfo(entry.getValue().getType(), entry.getValue().getCompartment());
            Location copyLocation = new Location(entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
            copy.put(copyLocation,copyWallInfo);
        }
        WallRenderInfo wallRenderInfo = new WallRenderInfo(copy);
        for(WallRenderInfoObserver observer : wallRenderInfoObservers){
            observer.updateWallInfo(wallRenderInfo);
        }
    }

    @Override
    public void attach(WallRenderInfoObserver observer) {
        this.wallRenderInfoObservers.add(observer);
    }

    @Override
    public void detach(WallRenderInfoObserver observer) {
        this.wallRenderInfoObservers.remove(observer);
    }
}
