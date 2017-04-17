package game.controller;

import game.view.WonderView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class WonderViewController {

    private WonderView wonderView;

    public WonderViewController(WonderView wonderView){
        setView(wonderView);
    }

    private void setView(WonderView wonderView){
        this.wonderView = wonderView;
    }


}
