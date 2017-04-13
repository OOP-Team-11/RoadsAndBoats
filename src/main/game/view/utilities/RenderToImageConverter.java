package game.view.utilities;

import game.model.tile.Terrain;
import javafx.scene.image.Image;

public class RenderToImageConverter {

    private Assets assets;

    public RenderToImageConverter(Assets assets){
        setAssets(assets);
    }

    private void setAssets(Assets assets){
        this.assets = assets;
    }

    public Image getTerrainImage(Terrain terrain){
        if(terrain.equals(Terrain.SEA)){
            return Assets.getInstance().SEA;
        } else if (terrain.equals(Terrain.DESERT)){
            return Assets.getInstance().DESERT;
        } else if(terrain.equals(Terrain.MOUNTAIN)){
            return Assets.getInstance().MOUNTAIN;
        } else if(terrain.equals(Terrain.PASTURE)){
            return Assets.getInstance().PASTURE;
        } else if(terrain.equals(Terrain.ROCK)){
            return Assets.getInstance().ROCK;
        } else {
            return Assets.getInstance().WOODS;
        }

    }


}
