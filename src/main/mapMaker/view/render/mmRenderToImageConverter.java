package mapMaker.view.render;

import javafx.scene.image.Image;
import mapMaker.model.tile.mmTerrain;
import mapMaker.view.utilities.mmAssets;

public class mmRenderToImageConverter {

    public static Image getTerrainImage(mmTileRenderInformation tile, mmAssets mmAssets){
        if(tile.getMmTerrain().equals(mmTerrain.SEA)){
            return mmAssets.SEA;
        } else if (tile.getMmTerrain().equals(mmTerrain.DESERT)){
            return mmAssets.DESERT;
        } else if(tile.getMmTerrain().equals(mmTerrain.MOUNTAIN)){
            return mmAssets.MOUNTAIN;
        } else if(tile.getMmTerrain().equals(mmTerrain.PASTURE)){
            return mmAssets.PASTURE;
        } else if(tile.getMmTerrain().equals(mmTerrain.ROCK)){
            return mmAssets.ROCK;
        } else {
            return mmAssets.WOODS;
        }
    }

    public static Image getRiverImage(mmTileRenderInformation mmTileRenderInformation, mmAssets mmAssets) {
        int waterCount = 0;

        if(mmTileRenderInformation.getNorth()){
            waterCount++;
        }
        if(mmTileRenderInformation.getNorthEast()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouthEast()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouth()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouthWest()){
            waterCount++;
        }
        if(mmTileRenderInformation.getNorthWest()){
            waterCount++;
        }
        if(waterCount == 0){
            return null;
        }

        if(waterCount == 1){
            if(mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_SPRING_R0;
            } else if (mmTileRenderInformation.getNorthEast()){
                return mmAssets.getInstance().RIVER_SPRING_R1;
            } else if(mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_SPRING_R2;
            } else if(mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_SPRING_R3;
            } else if(mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_SPRING_R4;
            } else if(mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_SPRING_R5;
            } else {
                return null;
            }

        } else if(waterCount == 2){
            if(mmTileRenderInformation.getNorth() && mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R0;
            } else if(mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R1;
            } else if (mmTileRenderInformation.getSouthEast() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R2;
            } else if (mmTileRenderInformation.getNorth() && mmTileRenderInformation.getNorthEast()){
                return mmAssets.getInstance().RIVER_ADJACENT_R0;
            } else if (mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_ADJACENT_R1;
            } else if (mmTileRenderInformation.getSouthEast()&& mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_ADJACENT_R2;
            } else if (mmTileRenderInformation.getSouth() && mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_ADJACENT_R3;
            } else if (mmTileRenderInformation.getSouthWest() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_ADJACENT_R4;
            } else if (mmTileRenderInformation.getNorthWest()&& mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_ADJACENT_R5;
            } else if (mmTileRenderInformation.getNorth() && mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_SKIP_R0;
            } else if (mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_SKIP_R1;
            } else if (mmTileRenderInformation.getSouthEast()&& mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_SKIP_R2;
            } else if (mmTileRenderInformation.getSouth() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_SKIP_R3;
            } else if (mmTileRenderInformation.getSouthWest() && mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_SKIP_R4;
            } else {
                return mmAssets.getInstance().RIVER_SKIP_R5;
            }
        } else {
            // 3 sides
            if(mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_EVERYOTHER_R0;
            } else {
                return mmAssets.getInstance().RIVER_EVERYOTHER_R1;
            }
        }
    }



}
