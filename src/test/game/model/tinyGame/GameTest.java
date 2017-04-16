package model.tinyGame;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.map.RBMap;
import game.model.tinyGame.Game;
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
        MainViewController mainViewController = mock(MainViewController.class);
        GooseManager gooseManager = new GooseManager(mainViewController, map);
        PlayerId playerId = new PlayerId(1);
        TransportManager transportManager = new TransportManager(playerId, mainViewController, gooseManager, map);
        PlayerId playerId2 = new PlayerId(2);
        TransportManager transportManager2 = new TransportManager(playerId2, mainViewController, gooseManager, map);
        player1 = new Player(transportManager, playerId, "Morty");
        player2 = new Player(transportManager, playerId, "Morty");
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
