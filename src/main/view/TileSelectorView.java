package view;

import javafx.scene.canvas.Canvas;

/**
 * Created by Konrad on 3/26/2017.
 */
public class TileSelectorView {

    private Canvas canvas;
    public TileSelectorView(Canvas canvas){
        this.canvas = canvas;
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        System.out.println("TileSelectorView is rendering");
    }
}
