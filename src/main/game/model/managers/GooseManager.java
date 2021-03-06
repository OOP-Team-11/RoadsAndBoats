package game.model.managers;

import game.controller.MainViewController;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.transport.Transport;
import game.model.visitors.GooseManagerVisitor;

import java.util.*;

public class GooseManager implements GooseManagerVisitor {
    private Map<TileCompartmentLocation, ArrayList<Goose>> mapGeese;
    private GooseAbilityManager gooseAbilityManager;

    public GooseManager(MainViewController mainViewController, RBMap map) {
        this.mapGeese = new HashMap<>();
        this.gooseAbilityManager = new GooseAbilityManager(mainViewController, map, this);
    }

//    ONLY FOR TESTING PURPOSES
    public GooseManager() {
        this.gooseAbilityManager = new GooseAbilityManager(new MainViewController(), new RBMap(), this);
        this.mapGeese = new HashMap<>();
    }

    public Map<TileCompartmentLocation, ArrayList<Goose>> getMapGeese() {
        return this.mapGeese;
    }
    public GooseAbilityManager getGooseAbilityManager() { return this.gooseAbilityManager; }

    public void addGoose(TileCompartmentLocation tileCompartmentLocation, Goose goose) {
        if(this.mapGeese.get(tileCompartmentLocation) == null)
            this.mapGeese.put(tileCompartmentLocation, new ArrayList<Goose>() {{add(goose);}});
        else {
            this.mapGeese.get(tileCompartmentLocation).add(goose);
//            System.out.println("Added goose");
        }
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

    public void addTransportManager(TransportManager transportManager) {
        this.gooseAbilityManager.addTransportManager(transportManager);
    }

    public void onGooseSelected(Goose goose, TileCompartmentLocation tileCompartmentLocation) {
        Map<TileCompartmentDirection, List<Goose>> tileGeese = new HashMap<TileCompartmentDirection, List<Goose>>();
        for(TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            TileCompartmentLocation tilesCompartment = new TileCompartmentLocation(tileCompartmentLocation.getLocation(), d);
//            Check that there is an index for the tileCompartmentLocation as well as exisiting transports
            if(mapGeese.get(tilesCompartment) != null && (mapGeese.get(tilesCompartment).size() > 0)) {
                tileGeese.put(d, mapGeese.get(tilesCompartment));
            }
        }
        this.gooseAbilityManager.addAbilities(goose, tileCompartmentLocation, tileGeese);
    }

    @Override
    public void addGooseVisit(Goose goose, TileCompartmentLocation tileCompartmentLocation) {
        this.addGoose(tileCompartmentLocation, goose);
    }
}
