package mapMaker.controller;

import mapMaker.view.MapMakerView;
import mapMaker.view.TileSelectorView;
import javafx.animation.AnimationTimer;

public class GameLoop {

    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private AnimationTimer animationTimer;

    public GameLoop(MapMakerView mapMakerView, TileSelectorView tileSelectorView){
        this.mapMakerView = mapMakerView;
        this.tileSelectorView = tileSelectorView;

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Tell MapMakerView to render
                mapMakerView.render();
                // Tell TileSelectoreView to render
                tileSelectorView.render();
            }
        };
    }

    public void startAnimationTimer(){
        animationTimer.start();
    }
    public void stopAnimationTimer(){
        animationTimer.stop();
    }
}
