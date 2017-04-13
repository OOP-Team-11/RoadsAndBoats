package game.controller;

import game.model.ability.Ability;
import game.view.MainView;
import game.view.render.CameraInfo;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;


public class MainViewController {

    private MainView mainView;
    private int cameraX;
    int cameraY;

    public MainViewController(MainView mainView){
        setMainView(mainView);
        addCameraEvent();
        setCameraValues();
    }

    private void setMainView(MainView mainView){
        this.mainView = mainView;
    }
    private void setCameraValues(){
        this.cameraX = 0;
        this.cameraY = 0;
    }
    private void notifyViewCamera(){
        CameraInfo cameraInfo = new CameraInfo(cameraX, cameraY);
        this.mainView.updateCameraInfo(cameraInfo);
    }

    private void addCameraEvent(){
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if(e.getCharacter().equals("w")){
                    cameraY += 5;
                    notifyViewCamera();
                } else if(e.getCharacter().equals("a")){
                    cameraX += 5;
                    notifyViewCamera();
                } else if (e.getCharacter().equals("s")){
                    cameraY -= 5;
                    notifyViewCamera();
                } else if (e.getCharacter().equals("d")){
                    cameraX -= 5;
                    notifyViewCamera();
                }
            }
        };
        mainView.addEventFilterToMainCanvas(KeyEvent.ANY,eventHandler);
    }

    public void attachControl(KeyCode keyCode, Ability ability) {

    }
}
