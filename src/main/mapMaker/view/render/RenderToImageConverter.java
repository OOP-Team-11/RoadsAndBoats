package mapMaker.view.render;

import javafx.scene.image.Image;
import mapMaker.model.tile.Terrain;
import mapMaker.view.utilities.Assets;

public class RenderToImageConverter {

    public static Image getTerrainImage(TileRenderInformation tile, Assets assets){
        if(tile.getTerrain().equals(Terrain.SEA)){
            return assets.SEA;
        } else if (tile.getTerrain().equals(Terrain.DESERT)){
            return assets.DESERT;
        } else if(tile.getTerrain().equals(Terrain.MOUNTAIN)){
            return assets.MOUNTAIN;
        } else if(tile.getTerrain().equals(Terrain.PASTURE)){
            return assets.PASTURE;
        } else if(tile.getTerrain().equals(Terrain.ROCK)){
            return assets.ROCK;
        } else {
            return assets.WOODS;
        }
    }

    public static Image getRiverImage(TileRenderInformation tileRenderInformation, Assets assets) {
        int waterCount = 0;

        if(tileRenderInformation.getNorth()){
            waterCount++;
        }
        if(tileRenderInformation.getNorthEast()){
            waterCount++;
        }
        if(tileRenderInformation.getSouthEast()){
            waterCount++;
        }
        if(tileRenderInformation.getSouth()){
            waterCount++;
        }
        if(tileRenderInformation.getSouthWest()){
            waterCount++;
        }
        if(tileRenderInformation.getNorthWest()){
            waterCount++;
        }
        if(waterCount == 0){
            return null;
        }

        if(waterCount == 1){
            if(tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_SPRING_R0;
            } else if (tileRenderInformation.getNorthEast()){
                return assets.getInstance().RIVER_SPRING_R1;
            } else if(tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_SPRING_R2;
            } else if(tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_SPRING_R3;
            } else if(tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_SPRING_R4;
            } else if(tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_SPRING_R5;
            } else {
                return null;
            }

        } else if(waterCount == 2){
            if(tileRenderInformation.getNorth() && tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_OPPOSITE_R0;
            } else if(tileRenderInformation.getNorthEast() && tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_OPPOSITE_R1;
            } else if (tileRenderInformation.getSouthEast() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_OPPOSITE_R2;
            } else if (tileRenderInformation.getNorth() && tileRenderInformation.getNorthEast()){
                return assets.getInstance().RIVER_ADJACENT_R0;
            } else if (tileRenderInformation.getNorthEast() && tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_ADJACENT_R1;
            } else if (tileRenderInformation.getSouthEast()&& tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_ADJACENT_R2;
            } else if (tileRenderInformation.getSouth() && tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_ADJACENT_R3;
            } else if (tileRenderInformation.getSouthWest() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_ADJACENT_R4;
            } else if (tileRenderInformation.getNorthWest()&& tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_ADJACENT_R5;
            } else if (tileRenderInformation.getNorth() && tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_SKIP_R0;
            } else if (tileRenderInformation.getNorthEast() && tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_SKIP_R1;
            } else if (tileRenderInformation.getSouthEast()&& tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_SKIP_R2;
            } else if (tileRenderInformation.getSouth() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_SKIP_R3;
            } else if (tileRenderInformation.getSouthWest() && tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_SKIP_R4;
            } else {
                return assets.getInstance().RIVER_SKIP_R5;
            }
        } else {
            // 3 sides
            if(tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_EVERYOTHER_R0;
            } else {
                return assets.getInstance().RIVER_EVERYOTHER_R1;
            }
        }
    }



}
