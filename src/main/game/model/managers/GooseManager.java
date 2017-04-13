package game.model.managers;

import game.model.direction.GooseLocation;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.resources.Goose;

import java.util.*;

public class GooseManager {
    private Map<Location, List<GooseLocation>> mapGeese;

    public GooseManager() { this.mapGeese = new HashMap<>();}

    public Map<Location, List<GooseLocation>> getMapGeese() {
        return this.mapGeese;
    }

    public void addGoose(Location loc, Goose goose, TileCompartmentDirection tcd) {
        if(this.mapGeese.get(loc) == null)
            this.mapGeese.put(loc, new ArrayList<GooseLocation>() {{add(new GooseLocation(goose, tcd));}});
        else
            this.mapGeese.get(loc).add(new GooseLocation(goose, tcd));
    }

    public void removeGoose(Location loc, Goose goose) {
        List<GooseLocation> gooseList = mapGeese.get(loc);
        for (Iterator<GooseLocation> iterator = gooseList.iterator(); iterator.hasNext();) {
            GooseLocation gooseLoc = iterator.next();
            if (gooseLoc.getGoose().getId() == goose.getId()) {
                iterator.remove();
            }
        }
        if(mapGeese.get(loc).size() == 0) {
            mapGeese.remove((loc));
        }
    }

}
