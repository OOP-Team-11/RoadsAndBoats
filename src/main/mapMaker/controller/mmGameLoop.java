package mapMaker.controller;

import mapMaker.view.mmMapMakerView;
import mapMaker.view.mmTileSelectorView;
import javafx.animation.AnimationTimer;

public class mmGameLoop {

    private mmMapMakerView mapMakerView;
    private mmTileSelectorView tileSelectorView;
    private AnimationTimer animationTimer;

    public mmGameLoop(mmMapMakerView mapMakerView, mmTileSelectorView tileSelectorView){
        this.mapMakerView = mapMakerView;
        this.tileSelectorView = tileSelectorView;

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Tell mmMapMakerView to render
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
