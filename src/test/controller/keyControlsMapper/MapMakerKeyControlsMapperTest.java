package controller.keyControlsMapper;

import controller.ControlHandler;
import direction.TileEdgeDirection;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

public class MapMakerKeyControlsMapperTest {

    private ControlHandler mockedControlHandler;
    private MapMakerKeyControlsMapper mapMakerKeyControlsMapper;
    private Scene scene;
    private VBox root;


    @Before
    public void setUp() {
        this.mockedControlHandler = mock(ControlHandler.class);
        root = new VBox();
        doNothing().when(this.mockedControlHandler).setDesertTerrain();
        doNothing().when(this.mockedControlHandler).setWoodsTerrain();
        doNothing().when(this.mockedControlHandler).setSeaTerrain();
        doNothing().when(this.mockedControlHandler).setRockyTerrain();
        doNothing().when(this.mockedControlHandler).setPastureTerrain();
        doNothing().when(this.mockedControlHandler).setMountainTerrain();
        this.mapMakerKeyControlsMapper = new MapMakerKeyControlsMapper(this.mockedControlHandler);
        this.scene = new Scene(this.root);
    }

    @Test
    public void setDesertTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT1, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setDesertTerrain();
    }

    @Test
    public void setMountainTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT2, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setMountainTerrain();
    }

    @Test
    public void setPastureTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT3, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setPastureTerrain();
    }

    @Test
    public void setRockyTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT4, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setRockyTerrain();
    }

    @Test
    public void setSeaTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT5, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setSeaTerrain();
    }

    @Test
    public void setWoodsTerrainTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.DIGIT6, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).setWoodsTerrain();
    }

    @Test
    public void rotateTileRightTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.RIGHT, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).rotateTileClockwise();
    }

    @Test
    public void rotateTileLeftTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.LEFT, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).rotateTileCounterClockwise();
    }

    @Test
    public void moveCursorSouthwestTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD1, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouthWest());
    }

    @Test
    public void moveCursorSouthTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD2, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouth());
    }

    @Test
    public void moveCursorSoutheastTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD3, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getSouthEast());
    }

    @Test
    public void moveCursorNorthwestTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD4, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorthWest());
    }

    @Test
    public void moveCursorNorthTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD5, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorth());
    }

    @Test
    public void moveCursorNortheastTest() {
        this.mapMakerKeyControlsMapper.attachToScene(this.scene);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "1", "1", KeyCode.NUMPAD6, false, false, false, false);
        this.root.fireEvent(keyEvent);
        verify(this.mockedControlHandler, times(1)).moveCursor(TileEdgeDirection.getNorthEast());
    }


}