package controller.keyControlsMapper;

import controller.ControlHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class KeyControlsMapper {

    private Map<KeyCode, Runnable> controls;

    KeyControlsMapper() {
        this.controls = new HashMap<>();
    }

    public void attachToScene(Scene s) {
        s.setOnKeyPressed(event -> {
            if (this.controls.containsKey(event.getCode())) {
                this.executeControl(this.controls.get(event.getCode()));
            }
        });
    }

    protected void addControl(KeyCode keyCode, Runnable controlHandler) {
        this.controls.put(keyCode, controlHandler);
    }

    private void executeControl(Runnable controlHandler) {
        controlHandler.run();
    }
}
