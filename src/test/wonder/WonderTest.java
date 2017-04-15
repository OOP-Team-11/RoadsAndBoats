package wonder;


import game.model.PlayerId;
import game.model.wonder.Wonder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WonderTest {
    @Test
    public void addBrick(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        assertEquals(wonder.getBrickCost(player1), 2);
        assertEquals(wonder.getBrickCost(player1), 2);
        assertEquals(wonder.getBrickCost(player1), 2);
        wonder.addBrick(player1);
        assertEquals(wonder.getWonderSize(),2);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),6);
        wonder.addBrick(player2);
        assertEquals(wonder.getWonderSize(),7);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),8);
    }
    @Test
    public void getPlayerWealthPoints(){
        Wonder wonder = new Wonder();
        PlayerId player1 = new PlayerId(0);
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        assertEquals(wonder.getBrickCost(player1), 2);
        assertEquals(wonder.getBrickCost(player1), 2);
        assertEquals(wonder.getBrickCost(player1), 2);
        wonder.addBrick(player1);
        assertEquals(wonder.getWonderSize(),2);
        wonder.addBrick(player2);
        wonder.addBrick(player2);
        wonder.addBrick(player2);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),6);
        wonder.addBrick(player2);
        assertEquals(wonder.getWonderSize(),7);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),8);
        assertEquals(wonder.getWealthPoints(player1), 4);
        assertEquals(wonder.getWealthPoints(player2), 16);
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
        PlayerId player2 = new PlayerId(1);
        wonder.addBrick(player1);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(),2);
        wonder.onTurnEnded();
        assertEquals(wonder.getWonderSize(), 3);
    }
}
