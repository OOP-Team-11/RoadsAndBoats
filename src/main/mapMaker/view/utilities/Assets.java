package mapMaker.view.utilities;

import javafx.scene.image.Image;

public class Assets {

    private static Assets assets = new Assets();

    public static Assets getInstance(){
        return assets;
    }

    private Assets(){
        if(loadAssets()){
            // everything is loaded in succesfully loaded in
        } else {
            System.out.println("ERROR: Failed to load Assets");
        }
    }
    // TODO declare images here
    // for example
    // public Image EXAMPLE;

    public Image SEA;

    public Image PASTURE;
    public Image PASTURE_R1_SPRING;
    public Image PASTURE_R2_ADJACENT;
    public Image PASTURE_R3_SKIP;
    public Image PASTURE_R4_OPPOSITE;
    public Image PASTURE_R5_EVERYOTHER;

    public Image WOODS;
    public Image WOODS_R1_SPRING;
    public Image WOODS_R2_ADJACENT;
    public Image WOODS_R3_SKIP;
    public Image WOODS_R4_OPPOSITE;
    public Image WOODS_R5_EVERYOTHER;

    public Image ROCK;
    public Image ROCK_R1_SPRING;
    public Image ROCK_R2_ADJACENT;
    public Image ROCK_R3_SKIP;
    public Image ROCK_R4_OPPOSITE;
    public Image ROCK_R5_EVERYOTHER;

    public Image DESERT;
    public Image DESERT_R1_SPRING;
    public Image DESERT_R2_ADJACENT;
    public Image DESERT_R3_SKIP;
    public Image DESERT_R4_OPPOSITE;
    public Image DESERT_R5_EVERYOTHER;

    public Image MOUNTAIN;
    public Image MOUNTAIN_R1_SPRING;
    public Image MOUNTAIN_R2_ADJACENT;
    public Image MOUNTAIN_R3_SKIP;
    public Image MOUNTAIN_R4_OPPOSITE;
    public Image MOUNTAIN_R5_EVERYOTHER;

    public Image SEA_TERRAIN;
    public Image PASTURE_TERRAIN;
    public Image WOODS_TERRAIN;
    public Image ROCK_TERRAIN;
    public Image DESERT_TERRAIN;
    public Image MOUNTAIN_TERRAIN;

    public Image GREEN_CURSOR;
    public Image RED_CURSOR;

    public Image ARROW_KEYS;


    public Image RIVER_SPRING_R0;
    public Image RIVER_SPRING_R1;
    public Image RIVER_SPRING_R2;
    public Image RIVER_SPRING_R3;
    public Image RIVER_SPRING_R4;
    public Image RIVER_SPRING_R5;

    public Image RIVER_ADJACENT_R0;
    public Image RIVER_ADJACENT_R1;
    public Image RIVER_ADJACENT_R2;
    public Image RIVER_ADJACENT_R3;
    public Image RIVER_ADJACENT_R4;
    public Image RIVER_ADJACENT_R5;

    public Image RIVER_SKIP_R0;
    public Image RIVER_SKIP_R1;
    public Image RIVER_SKIP_R2;
    public Image RIVER_SKIP_R3;
    public Image RIVER_SKIP_R4;
    public Image RIVER_SKIP_R5;

    public Image RIVER_OPPOSITE_R0;
    public Image RIVER_OPPOSITE_R1;
    public Image RIVER_OPPOSITE_R2;

    public Image RIVER_EVERYOTHER_R0;
    public Image RIVER_EVERYOTHER_R1;

    public Image FADED;

    public Image BACKGROUND;

    public Image NAVIGATION_BAR_1;

    public boolean loadAssets(){
        try{


            // sea tile
            SEA = new Image(this.getClass().getResourceAsStream("/tiles/sea/seaTile.png"));

            // pasture tile
            PASTURE = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTile.png"));
            PASTURE_R1_SPRING = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTileR1_Spring.png"));
            PASTURE_R2_ADJACENT = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTileR2_Adjacent.png"));
            PASTURE_R3_SKIP = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTileR3_Skip.png"));
            PASTURE_R4_OPPOSITE = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTileR4_Opposite.png"));
            PASTURE_R5_EVERYOTHER = new Image(this.getClass().getResourceAsStream("/tiles/pasture/pastureTileR5_EveryOther.png"));

            // woods tile
            WOODS = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTile.png"));
            WOODS_R1_SPRING = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTileR1_Spring.png"));
            WOODS_R2_ADJACENT = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTileR2_Adjacent.png"));
            WOODS_R3_SKIP = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTileR3_Skip.png"));
            WOODS_R4_OPPOSITE = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTileR4_Opposite.png"));
            WOODS_R5_EVERYOTHER = new Image(this.getClass().getResourceAsStream("/tiles/woods/woodsTileR5_EveryOther.png"));

            // rock tile
            ROCK = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTile.png"));
            ROCK_R1_SPRING = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTileR1_Spring.png"));
            ROCK_R2_ADJACENT = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTileR2_Adjacent.png"));
            ROCK_R3_SKIP = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTileR3_Skip.png"));
            ROCK_R4_OPPOSITE = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTileR4_Opposite.png"));
            ROCK_R5_EVERYOTHER = new Image(this.getClass().getResourceAsStream("/tiles/rock/rockTileR5_EveryOther.png"));

            // desert tile
            DESERT = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTile.png"));
            DESERT_R1_SPRING = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTileR1_Spring.png"));
            DESERT_R2_ADJACENT = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTileR2_Adjacent.png"));
            DESERT_R3_SKIP = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTileR3_Skip.png"));
            DESERT_R4_OPPOSITE = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTileR4_Opposite.png"));
            DESERT_R5_EVERYOTHER = new Image(this.getClass().getResourceAsStream("/tiles/desert/desertTileR5_EveryOther.png"));

            // mountain tile
            MOUNTAIN = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTile.png"));
            MOUNTAIN_R1_SPRING = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTileR1_Spring.png"));
            MOUNTAIN_R2_ADJACENT = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTileR2_Adjacent.png"));
            MOUNTAIN_R3_SKIP = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTileR3_Skip.png"));
            MOUNTAIN_R4_OPPOSITE = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTileR4_Opposite.png"));
            MOUNTAIN_R5_EVERYOTHER = new Image(this.getClass().getResourceAsStream("/tiles/mountain/mountainTileR5_EveryOther.png"));

            SEA_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/seaTerrain.png"));
            PASTURE_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/pastureTerrain.png"));
            WOODS_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/woodsTerrain.png"));
            ROCK_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/rockTerrain.png"));
            DESERT_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/desertTerrain.png"));
            MOUNTAIN_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/mountainTerrain.png"));

            RIVER_SPRING_R0 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR0.png"));
            RIVER_SPRING_R1 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR1.png"));
            RIVER_SPRING_R2 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR2.png"));
            RIVER_SPRING_R3 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR3.png"));
            RIVER_SPRING_R4 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR4.png"));
            RIVER_SPRING_R5 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river1_SpringR5.png"));

            RIVER_ADJACENT_R0 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR0.png"));
            RIVER_ADJACENT_R1 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR1.png"));
            RIVER_ADJACENT_R2 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR2.png"));
            RIVER_ADJACENT_R3 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR3.png"));
            RIVER_ADJACENT_R4 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR4.png"));
            RIVER_ADJACENT_R5 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river2_AdjacentR5.png"));

            RIVER_SKIP_R0 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR0.png"));
            RIVER_SKIP_R1 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR1.png"));
            RIVER_SKIP_R2 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR2.png"));
            RIVER_SKIP_R3 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR3.png"));
            RIVER_SKIP_R4 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR4.png"));
            RIVER_SKIP_R5 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river3_SkipR5.png"));

            RIVER_OPPOSITE_R0 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river4_OppositeR0.png"));
            RIVER_OPPOSITE_R1 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river4_OppositeR1.png"));
            RIVER_OPPOSITE_R2 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river4_OppositeR2.png"));

            RIVER_EVERYOTHER_R0 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river5_EveryOtherR0.png"));
            RIVER_EVERYOTHER_R1 = new Image(this.getClass().getResourceAsStream("/tiles/Rivers/river5_EveryOtherR1.png"));

            GREEN_CURSOR = new Image(this.getClass().getResourceAsStream("/tiles/greenCursor.png"));
            RED_CURSOR = new Image(this.getClass().getResourceAsStream("/tiles/redCursor.png"));

            ARROW_KEYS = new Image(this.getClass().getResourceAsStream("/misc/arrowkeys.png"));
            FADED = new Image(this.getClass().getResourceAsStream("/tiles/fadedHex.png"));

            BACKGROUND = new Image(this.getClass().getResourceAsStream("/misc/WelcomeImage.png"));

            NAVIGATION_BAR_1 = new Image(this.getClass().getResourceAsStream("/buttons/mainViewButton.png"));

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
