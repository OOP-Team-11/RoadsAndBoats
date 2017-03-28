package controller.keyControlsMapper;

import controller.ControlHandler;
import direction.TileEdgeDirection;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import view.ViewInitializer;

//import static org.mockito.Mockito.*;

public class MapMakerKeyControlsMapperTest extends ApplicationTest {


    private ControlHandler mockedControlHandler;
    private MapMakerKeyControlsMapper mapMakerKeyControlsMapper;
    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        ViewInitializer viewInitializer = new ViewInitializer(stage);
        viewInitializer.startAnimationLoop();
        this.scene = viewInitializer.getSceneReferense();
        this.mockedControlHandler = mock(ControlHandler.class);
        this.mapMakerKeyControlsMapper = new MapMakerKeyControlsMapper(this.mockedControlHandler);
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @Test
    public void setDesertTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT1, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setDesertTerrain();
    }

    @Test
    public void setMountainTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT2, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setMountainTerrain();
    }

    @Test
    public void setPastureTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT3, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setPastureTerrain();
    }

    @Test
    public void setRockyTerrainTest() {

        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT4, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setRockyTerrain();
    }

    @Test
    public void setSeaTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT5, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setSeaTerrain();
    }

    @Test
    public void setWoodsTerrainTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT6, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).setWoodsTerrain();
    }

    @Test
    public void rotateTileRightTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.RIGHT, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).rotateTileClockwise();
    }

    @Test
    public void rotateTileLeftTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.LEFT, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).rotateTileCounterClockwise();
    }

    @Test
    public void moveCursorSouthwestTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD1, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouthWest());
    }

    @Test
    public void moveCursorSouthTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD2, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouth());
    }

    @Test
    public void moveCursorSoutheastTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD3, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouthEast());
    }

    @Test
    public void moveCursorNorthwestTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD4, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorthWest());
    }

    @Test
    public void moveCursorNorthTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD5, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorth());
    }

    @Test
    public void moveCursorNortheastTest() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD6, false, false, false, false);
        Event.fireEvent(this.scene, keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorthEast());
    }


}