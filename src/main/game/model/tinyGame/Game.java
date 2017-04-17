package game.model.tinyGame;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.PrayerManager;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.model.structures.Structure;
import game.model.wonder.TurnObserver;
import game.utilities.observable.PhaseRenderInfoObservable;
import game.utilities.observable.PlayerRenderInfoObservable;
import game.utilities.observer.PhaseRenderInfoObserver;
import game.utilities.observer.PlayerRenderInfoObserver;
import game.utilities.observer.WallRenderInfoObserver;
import game.view.render.PhaseRenderInfo;
import game.view.render.PlayerRenderInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Game implements PlayerRenderInfoObservable, PhaseRenderInfoObservable, TurnObserver {

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Phase phase;
    private RBMap map;
    private PrayerManager prayerManager;
    private GooseManager gooseManager;
    private StructureManager structureManager;
    private List<PlayerRenderInfoObserver> playerRenderInfoObservers;
    private List<PhaseRenderInfoObserver> phaseRenderInfoObservers;
    private List<WallRenderInfoObserver> wallRenderInfObservers;
    private int turnCount;

    public Game(RBMap map, Player player1, Player player2, GooseManager gooseManager, StructureManager structureManager) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.map = map;
        this.gooseManager = gooseManager;
        this.structureManager = structureManager;
        this.phase = new Phase();
        this.playerRenderInfoObservers = new ArrayList<>();
        this.phaseRenderInfoObservers = new ArrayList<>();
        this.wallRenderInfObservers = new ArrayList<>();
        ArrayList<PlayerId> playerIds=new ArrayList<PlayerId>();
        playerIds.add(player1.getPlayerId());
        playerIds.add(player2.getPlayerId());
        prayerManager=new PrayerManager(playerIds);
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String getCurrentPhaseName() {
        return this.phase.getCurrentPhaseName();
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public RBMap getMap() {
        return this.map;
    }

    @Override
    public void attachPlayerInfoObserver(PlayerRenderInfoObserver observer) {
        this.playerRenderInfoObservers.add(observer);
        notifyPlayerObservers();
    }

    @Override
    public void detachPlayerInfoObserver(PlayerRenderInfoObserver observer) {
        this.playerRenderInfoObservers.remove(observer);
    }

    private void notifyPhaseObservers() {
        PhaseRenderInfo phaseRenderInfo = new PhaseRenderInfo(phase);
        for (PhaseRenderInfoObserver observer : this.phaseRenderInfoObservers) {
            observer.updatePhaseInfo(phaseRenderInfo);
        }
    }

    @Override
    public void attachPhaseInfoObserver(PhaseRenderInfoObserver observer) {
        this.phaseRenderInfoObservers.add(observer);
        notifyPhaseObservers();
    }

    @Override
    public void detachPhaseInfoObserver(PhaseRenderInfoObserver observer) {
        this.phaseRenderInfoObservers.remove(observer);
    }

    @Override
    public void onTurnEnded() {
        changePlayers();
        incrementTurn();
    }

    public Map<TileCompartmentLocation, Structure> getStructureSet(){
        Map<TileCompartmentLocation, Structure> structures = structureManager.getStructures();
        return structures;
    }

    private void incrementTurn() {
        if (++turnCount % 2 == 0) {
            advancePhase();
        }
    }

    private void changePlayers() {
        if(currentPlayer.equals(player1)) currentPlayer = player2;
        else currentPlayer = player1;

        notifyPlayerObservers();
    }

    private void advancePhase() {
        this.phase.goToNextPhase();
        notifyPhaseObservers();
    }

    private void notifyPlayerObservers() {
        PlayerRenderInfo playerRenderInfo = new PlayerRenderInfo(currentPlayer);
        for (PlayerRenderInfoObserver observer : this.playerRenderInfoObservers) {
            observer.updatePlayerInfo(playerRenderInfo);
        }
    }
}
