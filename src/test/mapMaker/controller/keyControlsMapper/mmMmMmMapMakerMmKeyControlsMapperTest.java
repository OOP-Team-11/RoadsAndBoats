package mapMaker.controller.keyControlsMapper;

import javafx.scene.layout.StackPane;
import mapMaker.controller.mmControlHandler;
import mapMaker.direction.mmTileEdgeDirection;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import mapMaker.view.mmViewInitializer;

import static org.mockito.Mockito.*;

public class mmMmMmMapMakerMmKeyControlsMapperTest extends ApplicationTest {


    private mmControlHandler mockedMmControlHandler;
    private mmMapMakerMmKeyControlsMapper mapMakerKeyControlsMapper;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        Scene testScene = new Scene(new StackPane(), 300,200);
        mmViewInitializer mmViewInitializer = new mmViewInitializer(testScene);
        mmViewInitializer.startAnimationLoop();
        this.scene = mmViewInitializer.getSceneReferense();
        this.mockedMmControlHandler = mock(mmControlHandler.class);
        this.mapMakerKeyControlsMapper = new mmMapMakerMmKeyControlsMapper(this.mockedMmControlHandler);
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        this.stage.show();
    }

    @Test
    public void setSeaTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT1, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setSeaTerrain();
    }

    @Test
    public void setPastureTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT2, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setPastureTerrain();
    }

    @Test
    public void setWoodsTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT3, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setWoodsTerrain();
    }

    @Test
    public void setRockyTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT4, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setRockyTerrain();
    }

    @Test
    public void setDesertTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT5, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setDesertTerrain();
    }

    @Test
    public void setMountainTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT6, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).setMountainTerrain();
    }

    @Test
    public void rotateTileRightTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.RIGHT, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).rotateTileClockwise();
    }

    @Test
    public void rotateTileLeftTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.LEFT, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).rotateTileCounterClockwise();
    }

    @Test
    public void moveCursorSouthwestTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD1, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getSouthWest());
    }

    @Test
    public void moveCursorSouthTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD2, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getSouth());
    }

    @Test
    public void moveCursorSoutheastTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD3, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getSouthEast());
    }

    @Test
    public void moveCursorNorthwestTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD4, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getNorthWest());
    }

    @Test
    public void moveCursorNorthTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD5, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getNorth());
    }

    @Test
    public void moveCursorNortheastTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD6, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).moveCursor(mmTileEdgeDirection.getNorthEast());
    }

    @Test
    public void tryPlaceTileTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.ENTER, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).tryPlaceTile();
    }

    @Test
    public void clearTile() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.BACK_SPACE, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedMmControlHandler, times(1)).clearTile();
    }


}