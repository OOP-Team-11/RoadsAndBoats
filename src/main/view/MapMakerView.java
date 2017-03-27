package view;

import javafx.scene.canvas.Canvas;
import util.Observer.CursorObserver.CursorObserver;

public class MapMakerView implements CursorObserver{

    private Canvas canvas;
    public MapMakerView(Canvas canvas){
        this.canvas = canvas;
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        System.out.println("MapMakerView is rendering");
    }
}
