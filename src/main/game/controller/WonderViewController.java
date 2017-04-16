package game.controller;

import game.view.WonderView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class WonderViewController {

    private WonderView wonderView;

    public WonderViewController(WonderView wonderView){
        setView(wonderView);
        initializeEvents();
    }

    private void setView(WonderView wonderView){
        this.wonderView = wonderView;
    }

    private void initializeEvents(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when a brick was added
                System.out.println("Brick was added to model.wonder");
            }
        };
        this.wonderView.addEventFilterToAddBrickButton(eventHandler);
    }

}
