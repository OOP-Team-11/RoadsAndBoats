package game.model.managers;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.resources.Goose;

import java.util.HashMap;
import java.util.Map;

public class GooseManager {
    private Map<Location, Map<Goose, TileCompartmentDirection>> mapGeese = new HashMap<>();

    public Map<Location, Map<Goose, TileCompartmentDirection>> getMapGeese() {
        return this.mapGeese;
    }

    public void addGoose(Location loc, TileCompartmentDirection tcd, Goose goose) {
        this.mapGeese.put(loc, new HashMap(){{put(goose, tcd);}});
    }
}
