package game.model.tinyGame;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
import game.model.map.RBMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GameTest {

    private Game game;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        RBMap map = new RBMap();
        TransportAbilityManager transportAbilityManager = new TransportAbilityManager(mock(MainViewController.class), mock(GooseManager.class), map);
        player1 = new Player(transportAbilityManager, new PlayerId(1), "gavin");
        player2 = new Player(transportAbilityManager, new PlayerId(2), "not gavin");
        this.game = new Game(map, player1, player2, mock(GooseManager.class), mock(StructureManager.class));
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
