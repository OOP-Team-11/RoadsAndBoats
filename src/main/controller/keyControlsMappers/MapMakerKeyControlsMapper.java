package controller.keyControlsMappers;

import controller.ControlHandler;

public class MapMakerKeyControlsMapper extends KeyControlsMapper {

    private ControlHandler controlHandler;
    public MapMakerKeyControlsMapper(ControlHandler controlHandler) {
        this.controlHandler = controlHandler;
        this.initializeControls();
    }

    private void initializeControls() {
//        this.addControl(KeyCode.DIGIT0, ()-> this.controlHandler.doSomething());
    }
}
