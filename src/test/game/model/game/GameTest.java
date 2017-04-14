package model.game;

import game.model.Player;
import game.model.game.Game;
import game.model.map.RBMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        RBMap map = new RBMap();
        player1 = new Player(1);
        player2 = new Player(2);
        this.game = new Game(map, player1, player2);
    }

    @Test
    public void playerSwitchOnTurnEnded() {
        game.onTurnEnded();
        assertEquals(game.getCurrentPlayer(), player2);
    }

    @Test
    public void playerSwitchOnTurnEndedAgain() {
        game.onTurnEnded();
        assertEquals(game.getCurrentPlayer(), player2);
        game.onTurnEnded();
        assertEquals(game.getCurrentPlayer(), player1);
    }

    @Test
    public void phaseAdvanceEveryOtherTurn() {
        assertEquals(game.getCurrentPhaseName(), "Trading");
        game.onTurnEnded();
        game.onTurnEnded();
        assertEquals(game.getCurrentPhaseName(), "(Re)Production");

        game.onTurnEnded();
        game.onTurnEnded();
        assertEquals(game.getCurrentPhaseName(), "Movement");
    }
}
