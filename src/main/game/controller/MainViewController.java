package game.controller;

import game.model.ability.Ability;
import game.view.MainView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;
import game.view.render.CameraInfo;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class MainViewController {

    private MainView mainView;
    private static Map<KeyCode, Ability> controls;
    private int cameraX;
    private int cameraY;

    public MainViewController(MainView mainView){
        setMainView(mainView);
        addCameraEvent();
        setCameraValues();
    }

    private void setMainView(MainView mainView){
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
