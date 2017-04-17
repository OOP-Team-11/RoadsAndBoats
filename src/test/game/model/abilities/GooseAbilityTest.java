package model.abilities;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.*;
import game.model.map.RBMap;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.WagonFactory;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.transport.TransportId;
import game.model.transport.WagonTransport;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GooseAbilityTest {

    //    Test that the reproduce ability will add with just two donkeys in a pasture
    @Test
    public void addGooseReproduceAbilityTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(1);
        StructureManager structureManager = new StructureManager(mainViewController, map);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager, null);
        Player player1 = new Player(transportManager, playerId, "Morty", location);
        Goose goose1 = new Goose(new GooseId());
        Goose goose2 = new Goose(new GooseId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        gooseManager.addGoose(location, goose1);
        gooseManager.addGoose(location, goose2);
        gooseManager.onGooseSelected(goose1, location);
        assertEquals(1, gooseManager.getGooseAbilityManager().getAbilityCount());
    }

    //    Test that the reproduce ability wont be valid when resources are in the tile
    @Test
    public void resourceProhibitBreedingTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(1);
        StructureManager structureManager = new StructureManager(mainViewController, map);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager, null);
        Player player1 = new Player(transportManager, playerId, "Morty", location);
        Goose goose1 = new Goose(new GooseId());
        Goose goose2 = new Goose(new GooseId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        gooseManager.addGoose(location, goose1);
        gooseManager.addGoose(location, goose2);
//        Add resources so gooses cant reproduce
        map.getTile(new Location(0,0,0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.COINS, 12);
        gooseManager.onGooseSelected(goose1, location);
        assertEquals(0, gooseManager.getGooseAbilityManager().getAbilityCount());
    }

    //    Test that the reproduce ability will add with just two donkeys in a pasture
    @Test
    public void structureProhibitBreedingTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(1);
        StructureManager structureManager = new StructureManager(mainViewController, map);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager, null);
        Player player1 = new Player(transportManager, playerId, "Morty", location);
        Goose goose1 = new Goose(new GooseId());
        Goose goose2 = new Goose(new GooseId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        gooseManager.addGoose(location, goose1);
        gooseManager.addGoose(location, goose2);
//        Add a structure so gooses cant reproduce
        map.getTile(new Location(0,0,0)).addStructure(new WagonFactory(location));
        gooseManager.onGooseSelected(goose1, location);
        assertEquals(0, gooseManager.getGooseAbilityManager().getAbilityCount());
    }
    //    Test that the reproduce ability will add with just two donkeys in a pasture
    @Test
    public void transprtsProhibitBreedingTest() {
        TileCompartmentLocation location = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        MainViewController mainViewController = new MainViewController();
        RBMap map = new RBMap();
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(1);
        StructureManager structureManager = new StructureManager(mainViewController, map);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map, structureManager, null);
        Player player1 = new Player(transportManager, playerId, "Morty", location);
        Goose goose1 = new Goose(new GooseId());
        Goose goose2 = new Goose(new GooseId());
        map.placeTile(new Location(0,0,0), new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        gooseManager.addGoose(location, goose1);
        gooseManager.addGoose(location, goose2);
//        Add a transport to the tile so gooses cant reproduce
        player1.getTransportManager().addTransport(new WagonTransport(player1.getPlayerId(), new TransportId()), location);
        gooseManager.addTransportManager(player1.getTransportManager());
        gooseManager.onGooseSelected(goose1, location);
        assertEquals(0, gooseManager.getGooseAbilityManager().getAbilityCount());
    }
}
