package game.model.managers;

import game.controller.MainViewController;
import game.model.ability.Ability;
import game.model.ability.goose.FollowAbility;
import game.model.ability.transport.*;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.factory.AbilityFactory;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.resources.ResourceType;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;
import game.model.visitors.StructureManagerVisitor;
import game.model.visitors.TransportManagerVisitor;

import java.util.*;

public class TransportAbilityManager {
    private GooseManager gooseManager;
    private AbilityFactory abilityFactory;
    private ArrayList<Ability> abilities;
    private RBMap map;
    private MainViewController mainViewController;
    private TransportManagerVisitor transportManagerVisitor;
    private StructureManagerVisitor structureManagerVisitor;

    public TransportAbilityManager(MainViewController mainViewController, GooseManager gooseManager, RBMap map, TransportManagerVisitor transportManagerVisitor, StructureManagerVisitor structureManagerVisitor) {
        this.mainViewController = mainViewController;
        this.abilityFactory = new AbilityFactory(mainViewController);
        this.abilities = new ArrayList<Ability>();
        this.gooseManager = gooseManager;
        this.map = map;
        this.transportManagerVisitor = transportManagerVisitor;
        this.structureManagerVisitor = structureManagerVisitor;
    }

    public RBMap getMap() { return map; }
    public GooseManager getGooseManager() { return this.gooseManager; }

    public int getAbilityCount() { return this.abilities.size(); }

    public void addAbilities(Transport transport, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Transport>> tileTransports) {
        this.abilities.clear();
        this.addFollowAbility(transport, tileCompartmentLocation);
        this.addTransportReproduceAbility(transport, tileCompartmentLocation, tileTransports);
        this.addBuildWoodCutterAbility(transport, tileCompartmentLocation);
//        this.addBuildClayPitAbility(transport, tileCompartmentLocation);      TODO: Once we can check if on river/sea shore
        this.addBuildStoneQuarryAbility(transport, tileCompartmentLocation);
//        this.addBuildOilRigAbility(transport, tileCompartmentLocation);  TODO: After research is implemented
        this.addBuildSawmillAbility(transport, tileCompartmentLocation);
        this.addBuildPapermillAbility(transport, tileCompartmentLocation);
        this.addBuildStoneFactoryAbility(transport, tileCompartmentLocation);
        this.addBuildCoalBurnerAbility(transport, tileCompartmentLocation);
        this.addBuildMineAbility(transport, tileCompartmentLocation);
        this.addBuildMintAbility(transport, tileCompartmentLocation);
        this.addBuildRowboatFactoryAbility(transport, tileCompartmentLocation);
        this.addBuildSteamshipFactoryAbility(transport, tileCompartmentLocation);
        this.addBuildTruckFactoryAbility(transport, tileCompartmentLocation);
        this.addBuildStockExchangeAbility(transport, tileCompartmentLocation);
        this.addPickUpResourceAbility(transport, tileCompartmentLocation);
        this.addDropResourceAbility(transport, tileCompartmentLocation);
    }

    private void addAbility(Ability a) {
        abilities.add(a);
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
            addAbility(followAbility);
        }
    }

    public void addTransportReproduceAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation, Map<TileCompartmentDirection, List<Transport>> tileTransports) {
        if (!transport.canReproduce())
            return;
        else {
            Location loc = tileCompartmentLocation.getLocation();
            TileCompartmentDirection transportCompartmentDirection = tileCompartmentLocation.getTileCompartmentDirection();
            Tile tile = map.getTile(loc);
            boolean noResources = tile.getTileCompartment(transportCompartmentDirection).hasNoResources();
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
                tileCompartmentTransports.put(currentTileCompartment, tileTransports.get(d));
            }
//            Make sure only the tileCompartment our transport is located in has another transport and theres only 1 other
            if(tileCompartmentTransports.size() == 1
                    && tileCompartmentTransports.get(tile.getTileCompartment(transportCompartmentDirection)).size() == 2) {
                for (Transport t : tileCompartmentTransports.get(tile.getTileCompartment(transportCompartmentDirection))) {
                    if(!t.canReproduce() || (t.getPlayerId() != transport.getPlayerId()))
                        return;
                }
            }
            else {
                return;
            }
            TransportReproduceAbility transportReproduceAbility = abilityFactory.getTransportReproduceAbility(transportManagerVisitor);
            transportReproduceAbility.attachToController(transport, tileCompartmentLocation);
            addAbility(transportReproduceAbility);
        }
    }

    public void addBuildWoodCutterAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 1) && tile.getTerrain() == Terrain.WOODS;
            if(canProduce) {
                BuildWoodcutterAbility buildAbility = abilityFactory.getBuildWoodcutterAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildClayPitAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
//            TODO: Check if on shore of river or sea
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 3);
            if(canProduce) {
                BuildClayPitAbility buildAbility = abilityFactory.getBuildClayPitAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildStoneQuarryAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 2 && tile.getTerrain() == Terrain.ROCK);
            if(canProduce) {
                BuildStoneQuarryAbility buildAbility = abilityFactory.getBuildStoneQuarryAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildSawmillAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = transport.getResourceCount(ResourceType.BOARDS) >= 2
                    && transport.getResourceCount(ResourceType.STONE) >= 1
                    && tile.getTerrain() != Terrain.SEA;
            if(canProduce) {
                BuildSawmillAbility buildAbility = abilityFactory.getBuildSawmillAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildPapermillAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = transport.getResourceCount(ResourceType.BOARDS) >= 1
                    && transport.getResourceCount(ResourceType.STONE) >= 1
                    && tile.getTerrain() != Terrain.SEA;
            if(canProduce) {
                BuildPapermillAbility buildAbility = abilityFactory.getBuildPapermillAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildStoneFactoryAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 2 && tile.getTerrain() != Terrain.SEA);
            if(canProduce) {
                BuildStoneFactoryAbility buildAbility = abilityFactory.getBuildStoneFactoryAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildCoalBurnerAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 3 && tile.getTerrain() != Terrain.SEA);
            if(canProduce) {
                BuildCoalBurnerAbility buildAbility = abilityFactory.getBuildCoalBurnerAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildMineAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 3
                    && (transport.getResourceCount(ResourceType.STONE) >= 1)
                    && tile.getTerrain() != Terrain.SEA);
            if(canProduce) {
                BuildMineAbility buildRegularMineAbility = abilityFactory.getBuildMineAbility(this.structureManagerVisitor);
                buildRegularMineAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildRegularMineAbility);

                BuildGoldMineAbility buildGoldMineAbility = abilityFactory.getBuildGoldMineAbility(this.structureManagerVisitor);
                buildGoldMineAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildGoldMineAbility);

                BuildIronMineAbility buildIronMineAbility = abilityFactory.getBuildIronMineAbility(this.structureManagerVisitor);
                buildIronMineAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildIronMineAbility);
            }
        }
    }

    public void addBuildMintAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 2
                    && (transport.getResourceCount(ResourceType.STONE) >= 1)
                    && tile.getTerrain() != Terrain.SEA);
            if(canProduce) {
                BuildMintAbility buildAbility = abilityFactory.getBuildMintAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildRowboatFactoryAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 1
                    && (transport.getResourceCount(ResourceType.STONE) >= 1));
//                    && TODO: check that on river or sea shore as well
            if(canProduce) {
                BuildRowboatFactoryAbility buildAbility = abilityFactory.getBuildRowboatFactoryAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildSteamshipFactoryAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.BOARDS) >= 1
                    && (transport.getResourceCount(ResourceType.STONE) >= 1));
//                    && TODO: check that on river or sea shore as well
            if(canProduce) {
                BuildSteamshipFactoryAbility buildAbility = abilityFactory.getBuildSteamshipFactoryAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildTruckFactoryAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.STONE) >= 2
                    && transport.getResourceCount(ResourceType.BOARDS) >= 2
                    && tile.getTerrain() != Terrain.SEA);
//                    && TODO: Enforce research check
            if(canProduce) {
                BuildTruckFactoryAbility buildAbility = abilityFactory.getBuildTruckFactoryAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addBuildStockExchangeAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        if(tile.getStructure() != null)
            return;
        else {
            boolean canProduce = (transport.getResourceCount(ResourceType.STONE) >= 3 && tile.getTerrain() != Terrain.SEA);
            if(canProduce) {
                BuildStockExchangeAbility buildAbility = abilityFactory.getBuildStockExchangeAbility(this.structureManagerVisitor);
                buildAbility.attachToController(transport.getResourceManager(), tileCompartmentLocation);
                addAbility(buildAbility);
            }
        }
    }

    public void addPickUpResourceAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        ResourceManager tileCompartmentRm = tile.getTileCompartment(tileCompartmentLocation.getTileCompartmentDirection()).getResourceManager();
        if(!transport.canStoreResource(1))
            return;
        int validResources = 0;
        for(ResourceType resource : ResourceType.values()) {
            if(tileCompartmentRm.getResourceTypeIntegerMap().get(resource) != null
                    && tileCompartmentRm.getResourceTypeIntegerMap().get(resource) > 0) {
                PickUpResourceAbility pickUpAbility = abilityFactory.getPickUpResourceAbility(resource);
                pickUpAbility.attachToController(tileCompartmentRm, transport.getResourceManager(), validResources);
                addAbility(pickUpAbility);
                validResources++;
            }
        }
    }

    public void addDropResourceAbility(Transport transport, TileCompartmentLocation tileCompartmentLocation) {
        Tile tile = map.getTile(tileCompartmentLocation.getLocation());
        ResourceManager tileCompartmentRm = tile.getTileCompartment(tileCompartmentLocation.getTileCompartmentDirection()).getResourceManager();
        int validResources = 0;
        for(ResourceType resource : ResourceType.values()) {
            if(transport.getResourceManager().getResourceTypeIntegerMap().get(resource) != null
                    && transport.getResourceManager().getResourceTypeIntegerMap().get(resource) > 0) {
                DropResourceAbility dropResourceAbility = abilityFactory.getDropResourceAbility();
                dropResourceAbility.attachToController(tileCompartmentRm, transport.getResourceManager());
                addAbility(dropResourceAbility);
                validResources++;
            }
        }
    }
}
