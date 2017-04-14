package wonder;


import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.managers.TransportAbilityManager;
import game.model.wonder.Wonder;
import game.model.wonder.WonderManager;
import game.view.MainView;
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
    public void getWealthPoints(){
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
}
