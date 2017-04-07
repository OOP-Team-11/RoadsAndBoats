package mapMaker.controller.keyControlsMapper;

import mapMaker.controller.ControlHandler;
import mapMaker.direction.TileEdgeDirection;
import javafx.scene.input.KeyCode;

public class MapMakerKeyControlsMapper extends KeyControlsMapper {

    private ControlHandler controlHandler;

    public MapMakerKeyControlsMapper(ControlHandler controlHandler) {
        this.controlHandler = controlHandler;
        this.initializeControls();
    }

    private void initializeControls() {
        this.addControl(KeyCode.DIGIT1, ()-> this.controlHandler.setSeaTerrain());
        this.addControl(KeyCode.DIGIT2, ()-> this.controlHandler.setPastureTerrain());
        this.addControl(KeyCode.DIGIT3, ()-> this.controlHandler.setWoodsTerrain());
        this.addControl(KeyCode.DIGIT4, ()-> this.controlHandler.setRockyTerrain());
        this.addControl(KeyCode.DIGIT5, ()-> this.controlHandler.setDesertTerrain());
        this.addControl(KeyCode.DIGIT6, ()-> this.controlHandler.setMountainTerrain());


        this.addControl(KeyCode.UP, ()-> this.controlHandler.previousRiverConfiguration());
        this.addControl(KeyCode.DOWN, ()-> this.controlHandler.nextRiverConfiguration());
        this.addControl(KeyCode.RIGHT, ()-> this.controlHandler.rotateTileClockwise());
        this.addControl(KeyCode.LEFT, ()-> this.controlHandler.rotateTileCounterClockwise());

        this.addControl(KeyCode.N, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouthWest()));
        this.addControl(KeyCode.J, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouth()));
        this.addControl(KeyCode.M, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouthEast()));
        this.addControl(KeyCode.H, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorthWest()));
        this.addControl(KeyCode.U, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorth()));
        this.addControl(KeyCode.K, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorthEast()));

        this.addControl(KeyCode.NUMPAD1, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouthWest()));
        this.addControl(KeyCode.NUMPAD2, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouth()));
        this.addControl(KeyCode.NUMPAD3, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getSouthEast()));
        this.addControl(KeyCode.NUMPAD4, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorthWest()));
        this.addControl(KeyCode.NUMPAD5, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorth()));
        this.addControl(KeyCode.NUMPAD6, ()-> this.controlHandler.moveCursor(TileEdgeDirection.getNorthEast()));

        this.addControl(KeyCode.W, ()-> this.controlHandler.moveViewport(0,1));
        this.addControl(KeyCode.A, ()-> this.controlHandler.moveViewport(1,0));
        this.addControl(KeyCode.S, ()-> this.controlHandler.moveViewport(0,-1));
        this.addControl(KeyCode.D, ()-> this.controlHandler.moveViewport(-1,0));

        this.addControl(KeyCode.ENTER, ()-> this.controlHandler.tryPlaceTile());
        this.addControl(KeyCode.BACK_SPACE, ()-> this.controlHandler.clearTile());
    }
}
