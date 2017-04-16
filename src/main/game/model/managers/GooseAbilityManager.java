package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.GooseReproduceAbility;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.factory.AbilityFactory;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GooseAbilityManager {
    private MainViewController mainViewController;
    private RBMap map;
    private List<TransportManager> transportManagers;
    private AbilityFactory abilityFactory;
    private List<Ability> abilities;

    public GooseAbilityManager(MainViewController mainViewController, RBMap map) {
        this.mainViewController = mainViewController;
        this.map = map;
        this.transportManagers = new ArrayList<>();
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<>();
    }
    public void addTransportManager(TransportManager transportManager) {
        transportManagers.add(transportManager);
    }

    public void addAbilities(Goose goose, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Goose>> tileGeese) {
        addGooseReproduceAbility(goose, tileCompartmentLocation, tileGeese);
    }

    public void addGooseReproduceAbility(Goose goose, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Goose>> tileGeese) {
        Location loc = tileCompartmentLocation.getLocation();
        TileCompartmentDirection gooseCompartmentDirection = tileCompartmentLocation.getTileCompartmentDirection();
        Tile tile = map.getTile(loc);
        // TODO Fix LOD if possible
        boolean noResources = tile.getTileCompartment(gooseCompartmentDirection).hasNoResources();
        boolean noStructure = (tile.getStructure() == null);
        boolean noTransports = true;
//            Check that there's no transports on the tile
        for (TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
            for(TransportManager tm : this.transportManagers) {
                List<Transport> compartmentTransports = tm.getTransports().get(new TileCompartmentLocation(loc, d));
                if (compartmentTransports != null && compartmentTransports.size() > 0) {
                    noTransports = false;
                    break;
                }
            }
        }
        if(!(noResources && noTransports && noStructure && tile.getTerrain().canReproduce()))
            return;

//            Check there is only 1 other goose that can reproduce on the tile
        Map<TileCompartment, List<Goose>> tileCompartmentGeese = new HashMap<TileCompartment, List<Goose>>();
        for(TileCompartmentDirection d : tileGeese.keySet()) {
            TileCompartment currentTileCompartment = tile.getTileCompartment(d);
            if(tileCompartmentGeese.get(currentTileCompartment) == null)
                tileCompartmentGeese.put(currentTileCompartment, tileGeese.get(d));
        }
//            Make sure only the tileCompartment our goose is located in has another goose and theres only 1 other
        if(tileCompartmentGeese.size() == 1
                && tileCompartmentGeese.get(tile.getTileCompartment(gooseCompartmentDirection)).size() == 2) {
            GooseReproduceAbility gooseReproduceAbility = abilityFactory.getGooseReproduceAbility();
            gooseReproduceAbility.attachToController(tileCompartmentLocation);
            abilities.add(gooseReproduceAbility);
        }
        else {
            return;
        }
    }

    public int getAbilityCount() { return this.abilities.size(); }
}
