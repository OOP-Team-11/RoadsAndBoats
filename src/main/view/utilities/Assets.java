package view.utilities;

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

    public Image ARROW_KEYS;

    public boolean loadAssets(){
        try{
            // TODO load in images here
            // for example
            // EXAMPLE = new Image("resources/image.png");

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

            ARROW_KEYS = new Image(this.getClass().getResourceAsStream("/misc/arrowkeys.png"));

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
