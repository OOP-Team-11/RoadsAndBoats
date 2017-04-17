package game.model.managers;

import game.model.direction.Location;
import game.utilities.observable.WallInformationObservabel;
import game.utilities.observer.WallRenderInfoObserver;
import game.view.MainView;
import game.view.render.WallInfo;
import game.view.render.WallRenderInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WallManager implements WallInformationObservabel{

    private HashMap<Location,ArrayList<WallInfo>> renderInformation;
    private MainView mainView;
    private List<WallRenderInfoObserver> wallRenderInfoObservers;

    public WallManager(){
        this.renderInformation = new HashMap<>();
        this.wallRenderInfoObservers = new ArrayList<>();
    }

    public void addNewWall(Location location, ArrayList<WallInfo> info){
        this.renderInformation.put(location, info);
        notifyWallInfoObservers();
    }

    public void notifyWallInfoObservers() {

        WallRenderInfo wallRenderInfo = new WallRenderInfo(renderInformation);

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
