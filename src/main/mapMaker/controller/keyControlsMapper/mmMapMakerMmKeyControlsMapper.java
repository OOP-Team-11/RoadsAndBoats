package mapMaker.controller.keyControlsMapper;

import mapMaker.controller.mmControlHandler;
import mapMaker.direction.mmTileEdgeDirection;
import javafx.scene.input.KeyCode;

public class mmMapMakerMmKeyControlsMapper extends mmKeyControlsMapper {

    private mmControlHandler mmControlHandler;

    public mmMapMakerMmKeyControlsMapper(mmControlHandler mmControlHandler) {
        this.mmControlHandler = mmControlHandler;
        this.initializeControls();
    }

    private void initializeControls() {
        this.addControl(KeyCode.DIGIT1, ()-> this.mmControlHandler.setSeaTerrain());
        this.addControl(KeyCode.DIGIT2, ()-> this.mmControlHandler.setPastureTerrain());
        this.addControl(KeyCode.DIGIT3, ()-> this.mmControlHandler.setWoodsTerrain());
        this.addControl(KeyCode.DIGIT4, ()-> this.mmControlHandler.setRockyTerrain());
        this.addControl(KeyCode.DIGIT5, ()-> this.mmControlHandler.setDesertTerrain());
        this.addControl(KeyCode.DIGIT6, ()-> this.mmControlHandler.setMountainTerrain());


        this.addControl(KeyCode.UP, ()-> this.mmControlHandler.previousRiverConfiguration());
        this.addControl(KeyCode.DOWN, ()-> this.mmControlHandler.nextRiverConfiguration());
        this.addControl(KeyCode.RIGHT, ()-> this.mmControlHandler.rotateTileClockwise());
        this.addControl(KeyCode.LEFT, ()-> this.mmControlHandler.rotateTileCounterClockwise());

        this.addControl(KeyCode.N, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouthWest()));
        this.addControl(KeyCode.J, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouth()));
        this.addControl(KeyCode.M, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouthEast()));
        this.addControl(KeyCode.H, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorthWest()));
        this.addControl(KeyCode.U, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorth()));
        this.addControl(KeyCode.K, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorthEast()));

        this.addControl(KeyCode.NUMPAD1, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouthWest()));
        this.addControl(KeyCode.NUMPAD2, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouth()));
        this.addControl(KeyCode.NUMPAD3, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getSouthEast()));
        this.addControl(KeyCode.NUMPAD4, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorthWest()));
        this.addControl(KeyCode.NUMPAD5, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorth()));
        this.addControl(KeyCode.NUMPAD6, ()-> this.mmControlHandler.moveCursor(mmTileEdgeDirection.getNorthEast()));

        this.addControl(KeyCode.W, ()-> this.mmControlHandler.moveViewport(0,1));
        this.addControl(KeyCode.A, ()-> this.mmControlHandler.moveViewport(1,0));
        this.addControl(KeyCode.S, ()-> this.mmControlHandler.moveViewport(0,-1));
        this.addControl(KeyCode.D, ()-> this.mmControlHandler.moveViewport(-1,0));

        this.addControl(KeyCode.ENTER, ()-> this.mmControlHandler.tryPlaceTile());
        this.addControl(KeyCode.BACK_SPACE, ()-> this.mmControlHandler.clearTile());
    }
}
