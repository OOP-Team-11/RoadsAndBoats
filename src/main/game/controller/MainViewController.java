package game.controller;

import game.model.ability.Ability;
import game.view.MainView;
import javafx.scene.input.KeyCode;

public class MainViewController {

    private MainView mainView;

    public MainViewController(MainView mainView){
        this.mainView = mainView;
    }

    public void attachControl(KeyCode keyCode, Ability ability) {

    }
}
