package view;

import javafx.scene.canvas.Canvas;
import util.Observer.TileSelectObserver.TileSelectObserver;

public class TileSelectorView implements TileSelectObserver{

    private Canvas canvas;
    public TileSelectorView(Canvas canvas){
        this.canvas = canvas;
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        System.out.println("TileSelectorView is rendering");
    }
}
