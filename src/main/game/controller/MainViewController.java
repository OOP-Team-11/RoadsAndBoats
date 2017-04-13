package game.controller;

import game.model.ability.Ability;
import game.view.MainView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class MainViewController {

    private MainView mainView;
    private static Map<KeyCode, Ability> controls;


    public MainViewController(MainView mainView) {
        this.mainView = mainView;
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

    public void addControl(KeyCode keyCode, Ability ability) {
        controls.put(keyCode, ability);
    }

    public void removeControl(KeyCode keyCode) {
        controls.remove(keyCode);
    }

    private void executeControl(Ability ability) {
        ability.perform();
    }
}
