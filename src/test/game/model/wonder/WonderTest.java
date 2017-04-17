package model.wonder;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.wonder.Wonder;
import game.model.wonder.WonderManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WonderTest {
    @Test
    public void addOneBrick(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        // at the beginning of the Wonder Phase, all player's brick cost to build a wonder brick is reset to 1
        assertEquals(wonder.getCurrentBrickCost(player1), 1);
        wonder.addBrick(player1);
        // every brick a player adds on the same turn increments the brick cost by 1
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        assertEquals(wonder.getWonderSize(), 1);
    }

    @Test
    public void addMultipleBricks() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        // 1 brick <= 1 cost
        // 2 brick <= 2 cost
        // 3 brick <= 3 cost
        assertEquals(wonder.getCurrentBrickCost(player1), 1);
        wonder.addBrick(player1);
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        wonder.addBrick(player1);
        assertEquals(wonder.getCurrentBrickCost(player1), 3);
        wonder.addBrick(player1);
        assertEquals(wonder.getCurrentBrickCost(player1), 4);
        assertEquals(wonder.getWonderSize(), 3);
    }

    @Test
    public void onTurnEnd() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        PlayerId player2 = new PlayerId(2);
        wonder.onTurnEnded();
        // on turn end, wonder adds a neutral brick, players' brick cost reset to 1
        assertEquals(wonder.getWonderSize(), 1);
        assertEquals(wonder.getCurrentBrickCost(player1), 1);
        assertEquals(wonder.getCurrentBrickCost(player2), 1);
    }

    @Test
    public void addMultipleBricks_onTurnEnd() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        PlayerId player2 = new PlayerId(2);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        assertEquals(wonder.getCurrentBrickCost(player1), 4);
        assertEquals(wonder.getCurrentBrickCost(player2), 1);
        assertEquals(wonder.getWonderSize(), 3);
        wonder.onTurnEnded();
        // on turn end, wonder adds a neutral brick, players' brick cost reset to 1
        assertEquals(wonder.getWonderSize(), 4);
        assertEquals(wonder.getCurrentBrickCost(player1), 1);
        assertEquals(wonder.getCurrentBrickCost(player2), 1);
    }

    @Test
    public void increaseBrickCost_withWonder() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        PlayerId player2 = new PlayerId(2);
        // add 17 bricks (completes first 4 rows)
        for (int i = 0; i < 17; ++i) {
            wonder.onTurnEnded();
        }
        // 18th brick increases brick cost
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(), 18);
        // brick cost is now 2
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        assertEquals(wonder.getCurrentBrickCost(player2), 2);
    }

    @Test
    public void increaseBrickCost_withPlayer() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        PlayerId player2 = new PlayerId(2);
        // add 17 bricks (completes first 4 rows)
        for (int i = 0; i < 17; ++i) {
            wonder.onTurnEnded();
        }
        wonder.addBrick(player1);
        assertEquals(wonder.getWonderSize(), 18);
        assertEquals(wonder.getCurrentBrickCost(player1), 3);
        assertEquals(wonder.getCurrentBrickCost(player2), 2);
    }

    @Test
    public void increaseBrickCost_Reset() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(1);
        PlayerId player2 = new PlayerId(2);
        // add 17 bricks (completes first 4 rows)
        for (int i = 0; i < 18; ++i) {
            wonder.onTurnEnded();
        }
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        assertEquals(wonder.getWonderSize(), 21);
        // brick cost increases by 1 from brick cost of 2
        assertEquals(wonder.getCurrentBrickCost(player1), 5);
        assertEquals(wonder.getCurrentBrickCost(player2), 2);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(), 22);
        // brick cost is now 2
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        assertEquals(wonder.getCurrentBrickCost(player2), 2);
    }

    @Test
    public void turnDesertIntoPasture_WithWonder() {
        RBMap map = new RBMap();
        Tile desertTile = new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());
        map.placeTile(new Location(0,0,0), desertTile);
        WonderManager wonderManager = new WonderManager(map);
        // add 44 bricks (completes first 9 rows)
        for (int i = 0; i < 44; ++i) {
            wonderManager.onTurnEnded();
        }
        wonderManager.onTurnEnded();
        // 45th brick turns all Desert tiles to Pasture
        assertEquals(desertTile.getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void turnDesertIntoPasture_WithPlayer() {
        RBMap map = new RBMap();
        Tile desertTile = new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());
        map.placeTile(new Location(0,0,0), desertTile);
        WonderManager wonderManager = new WonderManager(map);
        // add 44 bricks (completes first 9 rows)
        for (int i = 0; i < 44; ++i) {
            wonderManager.onTurnEnded();
        }
        wonderManager.onTurnEnded();
        // 45th brick turns all Desert tiles to Pasture
        assertEquals(desertTile.getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void getPlayerWealthPoints_OneRow() {
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        wonder.addBrick(player2);
        wonder.onTurnEnded();
        wonder.onTurnEnded();
        assertEquals(wonder.getWealthPoints(player1), 5);
        assertEquals(wonder.getWealthPoints(player2), 5);
    }

    @Test
    public void getPlayerWealthPoints_Max() {
        // one player owns all rows on wonder
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        for (int i = 0; i < 57; ++i) {
            wonder.addBrick(player1);
        }
        assertEquals(wonder.getWealthPoints(player1), 120);
        assertEquals(wonder.getWealthPoints(player2), 0);
    }



    @Test
    public void getPlayerWealthPoints(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        assertEquals(wonder.getCurrentBrickCost(player1), 2);
        wonder.addBrick(player1);
        assertEquals(wonder.getWonderSize(),2);
        wonder.onTurnEnded();
        wonder.addBrick(player2);
        wonder.addBrick(player2);
        wonder.addBrick(player2);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),7);
        wonder.addBrick(player2);
        assertEquals(wonder.getWonderSize(),8);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),9);
        assertEquals(wonder.getWealthPoints(player1), 6);
        assertEquals(wonder.getWealthPoints(player2), 13);
    }
    @Test
    public void getWonderBricks(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        wonder.addBrick(player2);
        assertEquals(wonder.getWonderSize(), 4);
    }
    @Test
    public void getPlayerBricks(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        wonder.addBrick(player2);
        assertEquals(wonder.getPlayerBricks(player1).size(), 2);
        assertEquals(wonder.getPlayerBricks(player2).size(), 1);
    }
    @Test
    public void getWealthPointEachRowWithNoOtherPlayerTile(){
        Wonder wonder = new Wonder();
        PlayerId player = new PlayerId(0);
        wonder.addBrick(player);
        wonder.addBrick(player);
        wonder.addBrick(player);
        wonder.onTurnEnded();
        assertEquals(wonder.getWealthPoints(player), 10);
    }
    @Test
    public void getWealthPointEachRowWithOtherPlayerTile(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        wonder.addBrick(player2);
        assertEquals(wonder.getWealthPoints(player1), 6);
        assertEquals(wonder.getWealthPoints(player2), 3);
    }
    @Test
    public void addNeutralTileOnTurnEnd(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),2);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(), 3);
    }

}
