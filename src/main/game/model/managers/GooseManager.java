package game.model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.resources.Goose;

import java.util.*;

public class GooseManager {
    private Map<TileCompartmentLocation, List<Goose>> mapGeese;

    public GooseManager() { this.mapGeese = new HashMap<>();}

    public Map<TileCompartmentLocation, List<Goose>> getMapGeese() {
        return this.mapGeese;
    }

    public void addGoose(TileCompartmentLocation tileCompartmentLocation, Goose goose) {
        if(this.mapGeese.get(tileCompartmentLocation) == null)
            this.mapGeese.put(tileCompartmentLocation, new ArrayList<Goose>() {{add(goose);}});
        else
            this.mapGeese.get(tileCompartmentLocation).add(goose);
    }

    public void removeGoose(TileCompartmentLocation tileCompartmentLocation, Goose goose) {
        List<Goose> gooseList = mapGeese.get(tileCompartmentLocation);
        for (Iterator<Goose> iterator = gooseList.iterator(); iterator.hasNext();) {
            Goose iteratorGoose = iterator.next();
            if (iteratorGoose.getId() == goose.getId()) {
                iterator.remove();
            }
        }
        if(mapGeese.get(tileCompartmentLocation).size() == 0) {
            mapGeese.remove(tileCompartmentLocation);
        }
    }

}
