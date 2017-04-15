package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.ability.transport.TransportReproduceAbility;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.factory.AbilityFactory;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

import java.util.*;

public class TransportAbilityManager {
    private GooseManager gooseManager;
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private RBMap map;
    private MainViewController mainViewController;

    public TransportAbilityManager(MainViewController mainViewController, GooseManager gooseManager, RBMap map) {
        this.mainViewController = mainViewController;
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<Ability>();
        this.gooseManager = gooseManager;
        this.map = map;
    }

    public RBMap getMap() { return map; }
    public GooseManager getGooseManager() { return this.gooseManager; }

    public int getAbilityCount() { return this.abilities.size(); }

    public void addAbilities(Transport transport, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Transport>> tileTransports) {
        this.addFollowAbility(transport, tileCompartmentLocation);
        this.addTransportReproduceAbility(transport, tileCompartmentLocation, tileTransports);
//        fill out with reset of abilities --> "add_____Ability(loc, transport, tcd)"
    }

    public void removeAbilities() {
        for(Ability ability : abilities)
            ability.detachFromController();
    }

    public void addFollowAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        List<Goose> availableGeese = gooseManager.getMapGeese().get(tileCompartmentLocation);
        Vector<Goose> abilityGeese = new Vector<Goose>();
        if(availableGeese == null)
            return;
        for(int gooseCount = 1; gooseCount < availableGeese.size()+1; gooseCount++) {
            abilityGeese.add(availableGeese.get(0));
            FollowAbility followAbility = abilityFactory.getFollowAbility();
            followAbility.attachToController(transport, gooseCount, abilityGeese);
            abilities.add(followAbility);
        }
    }

    public void addTransportReproduceAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Transport>> tileTransports) {
        if (!transport.canReproduce())
            return;
        else {
            Location loc = tileCompartmentLocation.getLocation();
            TileCompartmentDirection transportCompartmentDirection = tileCompartmentLocation.getTileCompartmentDirection();
            Tile tile = map.getTile(loc);
            boolean noResources = tile.getResourceManager().isEmpty();
            boolean noStructure = (tile.getStructure() == null);
            boolean noGoose = true;
//            Check that there's no geese on the tile
            for (TileCompartmentDirection d : TileCompartmentDirection.getAllDirections()) {
                ArrayList<Goose> compartmentGeese = gooseManager.getMapGeese().get(new TileCompartmentLocation(loc, d));
                if (compartmentGeese != null && compartmentGeese.size() > 0) {
                    noGoose = false;
                    break;
                }
            }
            if(!(noResources && noGoose && noStructure && tile.getTerrain().canReproduce()))
                return;

//            Check there is only 1 other transport that can reproduce on the tile
            Map<TileCompartment, List<Transport>> tileCompartmentTransports = new HashMap<TileCompartment, List<Transport>>();
            for(TileCompartmentDirection d : tileTransports.keySet()) {
                TileCompartment currentTileCompartment = tile.getTileCompartment(d);
                if(tileCompartmentTransports.get(currentTileCompartment) == null)
                    tileCompartmentTransports.put(currentTileCompartment, tileTransports.get(d));
            }
//            Make sure only the tileCompartment our transport is located in has another transport and theres only 1 other
            if(tileCompartmentTransports.size() == 1
                    && tileCompartmentTransports.get(tile.getTileCompartment(transportCompartmentDirection)).size() == 2) {
                for (Transport t : tileCompartmentTransports.get(tile.getTileCompartment(transportCompartmentDirection))) {
                    if(!t.canReproduce())
                        return;
                }
            }
            else {
                return;
            }
            TransportReproduceAbility transportReproduceAbility = abilityFactory.getTransportReproduceAbility();
            transportReproduceAbility.attachToController(transport, tileCompartmentLocation);
            abilities.add(transportReproduceAbility);
        }
    }
}
