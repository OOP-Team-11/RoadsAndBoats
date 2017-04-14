package game.controller;

import game.model.ability.Ability;
import game.model.direction.Location;
import game.view.MainView;
import game.view.render.CursorRenderInfo;
import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;
import game.view.render.CameraInfo;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;



public class MainViewController {

    private MainView mainView;
    private static Map<KeyCode, Ability> controls;
    private int cameraX;
    private int cameraY;
    private MouseClickInterpreter mouseClickInterpreter;

    public MainViewController(MainView mainView){
        setMainView(mainView);
        addCameraEvent();
        setCameraValues();
        attachViewKeyboardEvent();
        addMouseClickEventToMap();
        initializeMouseClickInterpreter();
        notifyViewCamera();
    }

    private void setMainView(MainView mainView){
        this.mainView = mainView;
        this.controls = new HashMap<>();
    }

    private void setCameraValues(){
        this.cameraX = 950/2;
        this.cameraY = 800/2;
    }
    private void initializeMouseClickInterpreter(){
        this.mouseClickInterpreter = new MouseClickInterpreter(950,800,128,114);
        this.mouseClickInterpreter.updateCameraOffsetValues(this.cameraX, this.cameraY);
    }

    private void notifyViewCamera(){
        CameraInfo cameraInfo = new CameraInfo(cameraX, cameraY);
        this.mainView.updateCameraInfo(cameraInfo);
    }

    private void addMouseClickEventToMap(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY){
                    // left mouse click
                    Location clicked = mouseClickInterpreter.interpretMouseClick(event.getX(), event.getY());
                    mainView.updateCursorInfo(new CursorRenderInfo(event.getX(),event.getY(), clicked, false));
                } else {
                    // right mouse click
                    Location clicked = mouseClickInterpreter.interpretMouseClick(event.getX(), event.getY());
                    mainView.updateCursorInfo(new CursorRenderInfo(event.getX(),event.getY(), clicked, true));
                }
            }
        };
        mainView.addEventFilterToMainCanvas(MouseEvent.MOUSE_CLICKED,eventHandler);
    }

    private void attachViewKeyboardEvent(){
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (controls.containsKey(e.getCode()))
                {
                    executeControl(controls.get(e.getCode()));
                }
            }
        };
        this.mainView.addEventFilterToMainCanvas(KeyEvent.ANY,eventHandler);
    }


    private void addCameraEvent(){
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {

                if(e.getCharacter().equals("w")){
                    cameraY += 5;
                    mouseClickInterpreter.updateCameraOffsetValues(cameraX,cameraY);
                    notifyViewCamera();
                } else if(e.getCharacter().equals("a")){
                    cameraX += 5;
                    mouseClickInterpreter.updateCameraOffsetValues(cameraX,cameraY);
                    notifyViewCamera();
                } else if (e.getCharacter().equals("s")){
                    cameraY -= 5;
                    mouseClickInterpreter.updateCameraOffsetValues(cameraX,cameraY);
                    notifyViewCamera();
                } else if (e.getCharacter().equals("d")){
                    cameraX -= 5;
                    mouseClickInterpreter.updateCameraOffsetValues(cameraX,cameraY);
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
