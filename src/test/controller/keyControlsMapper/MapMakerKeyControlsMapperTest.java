package controller.keyControlsMapper;

import controller.ControlHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;

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
        this.root.fireEvent();
        verify(this.mockedControlHandler, times(1)).setDesertTerrain();
    }


}
