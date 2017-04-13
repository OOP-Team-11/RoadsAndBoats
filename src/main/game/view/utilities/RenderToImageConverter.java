package game.view.utilities;

import game.model.tile.RiverConfiguration;
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


    public Image getRiverImage(RiverConfiguration riverConfiguration) {
        int waterCount = 0;

        if(riverConfiguration.canConnectNorth()){
            waterCount++;
        }
        if(riverConfiguration.canConnectNortheast()){
            waterCount++;
        }
        if(riverConfiguration.canConnectSoutheast()){
            waterCount++;
        }
        if(riverConfiguration.canConnectSouth()){
            waterCount++;
        }
        if(riverConfiguration.canConnectSouthwest()){
            waterCount++;
        }
        if(riverConfiguration.canConnectNorthwest()){
            waterCount++;
        }
        if(waterCount == 0){
            return null;
        }

        if(waterCount == 1){
            if(riverConfiguration.canConnectNorth()){
                return assets.RIVER_SPRING_R0;
            } else if (riverConfiguration.canConnectNortheast()){
                return assets.RIVER_SPRING_R1;
            } else if(riverConfiguration.canConnectSoutheast()){
                return assets.RIVER_SPRING_R2;
            } else if(riverConfiguration.canConnectSouth()){
                return assets.RIVER_SPRING_R3;
            } else if(riverConfiguration.canConnectSouthwest()){
                return assets.RIVER_SPRING_R4;
            } else if(riverConfiguration.canConnectNorthwest()){
                return assets.RIVER_SPRING_R5;
            } else {
                return null;
            }

        } else if(waterCount == 2){
            if(riverConfiguration.canConnectNorth() && riverConfiguration.canConnectSouth()){
                return assets.RIVER_OPPOSITE_R0;
            } else if(riverConfiguration.canConnectSoutheast() && riverConfiguration.canConnectSouthwest()){
                return assets.RIVER_OPPOSITE_R1;
            } else if (riverConfiguration.canConnectSoutheast() && riverConfiguration.canConnectNorthwest()){
                return assets.RIVER_OPPOSITE_R2;
            } else if (riverConfiguration.canConnectNorth() && riverConfiguration.canConnectNortheast()){
                return assets.RIVER_ADJACENT_R0;
            } else if (riverConfiguration.canConnectNortheast() && riverConfiguration.canConnectSoutheast()){
                return assets.RIVER_ADJACENT_R1;
            } else if (riverConfiguration.canConnectSoutheast()&& riverConfiguration.canConnectSouth()){
                return assets.RIVER_ADJACENT_R2;
            } else if (riverConfiguration.canConnectSouth()&& riverConfiguration.canConnectSouthwest()){
                return assets.RIVER_ADJACENT_R3;
            } else if (riverConfiguration.canConnectSouthwest() && riverConfiguration.canConnectNorthwest()){
                return assets.RIVER_ADJACENT_R4;
            } else if (riverConfiguration.canConnectNorthwest()&& riverConfiguration.canConnectNorth()){
                return assets.RIVER_ADJACENT_R5;
            } else if (riverConfiguration.canConnectNorth() && riverConfiguration.canConnectSoutheast()){
                return assets.RIVER_SKIP_R0;
            } else if (riverConfiguration.canConnectNortheast() && riverConfiguration.canConnectSouth()){
                return assets.RIVER_SKIP_R1;
            } else if (riverConfiguration.canConnectSoutheast()&& riverConfiguration.canConnectSouthwest()){
                return assets.RIVER_SKIP_R2;
            } else if (riverConfiguration.canConnectSouth() && riverConfiguration.canConnectNorthwest()){
                return assets.RIVER_SKIP_R3;
            } else if (riverConfiguration.canConnectSouthwest() && riverConfiguration.canConnectNorth()){
                return assets.RIVER_SKIP_R4;
            } else {
                return assets.RIVER_SKIP_R5;
            }
        } else {
            // 3 sides
            if(riverConfiguration.canConnectNorth()){
                return assets.RIVER_EVERYOTHER_R0;
            } else {
                return assets.RIVER_EVERYOTHER_R1;
            }
        }
    }


}
