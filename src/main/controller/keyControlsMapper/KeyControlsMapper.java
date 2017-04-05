package controller.keyControlsMapper;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class KeyControlsMapper {

    private static Map<KeyCode, Runnable> controls;

    KeyControlsMapper() {
        this.controls = new HashMap<>();
    }

    public void attachToScene(Scene s ) {
        s.setOnKeyPressed(event ->{
            if (controls.containsKey(event.getCode()))
            {
                executeControl(controls.get(event.getCode()));
            }
        });
    }

    protected void addControl(KeyCode keyCode, Runnable controlHandler) {
        controls.put(keyCode, controlHandler);
    }

    private void executeControl(Runnable controlHandler) {
        controlHandler.run();
    }
}
