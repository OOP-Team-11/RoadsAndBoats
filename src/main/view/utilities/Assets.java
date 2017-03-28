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
    public Image PASTURE_RIVER1;
    public Image PASTURE_RIVER2;
    public Image PASTURE_RIVER3;
    public Image PASTURE_RIVER4;
    public Image PASTURE_RIVER5;

    public Image WOODS;
    public Image WOODS_RIVER1;
    public Image WOODS_RIVER2;
    public Image WOODS_RIVER3;
    public Image WOODS_RIVER4;
    public Image WOODS_RIVER5;

    public Image ROCK;
    public Image ROCK_RIVER1;
    public Image ROCK_RIVER2;
    public Image ROCK_RIVER3;
    public Image ROCK_RIVER4;
    public Image ROCK_RIVER5;

    public Image DESERT;
    public Image DESERT_RIVER1;
    public Image DESERT_RIVER2;
    public Image DESERT_RIVER3;
    public Image DESERT_RIVER4;
    public Image DESERT_RIVER5;

    public Image MOUNTAIN;
    public Image MOUNTAIN_RIVER1;
    public Image MOUNTAIN_RIVER2;
    public Image MOUNTAIN_RIVER3;
    public Image MOUNTAIN_RIVER4;
    public Image MOUNTAIN_RIVER5;

    public Image SEA_TERRAIN;
    public Image PASTURE_TERRAIN;
    public Image WOODS_TERRAIN;
    public Image ROCK_TERRAIN;
    public Image DESERT_TERRAIN;
    public Image MOUNTAIN_TERRAIN;

    public boolean loadAssets(){
        try{
            // TODO load in images here
            // for example
            // EXAMPLE = new Image("resources/image.png");

            // sea tile
            SEA = new Image("tiles/sea/seaTile.png");

            // pasture tile
            PASTURE = new Image("tiles/pasture/pastureTile.png");
            PASTURE_RIVER1 = new Image("tiles/pasture/pastureTileRiver1.png");
            PASTURE_RIVER2 = new Image("tiles/pasture/pastureTileRiver2.png");
            PASTURE_RIVER3 = new Image("tiles/pasture/pastureTileRiver3.png");
            PASTURE_RIVER4 = new Image("tiles/pasture/pastureTileRiver4.png");
            PASTURE_RIVER5 = new Image("tiles/pasture/pastureTileRiver5.png");

            // woods tile
            WOODS = new Image("tiles/woods/woodsTile.png");
            WOODS_RIVER1 = new Image("tiles/woods/woodsTileRiver1.png");
            WOODS_RIVER2 = new Image("tiles/woods/woodsTileRiver2.png");
            WOODS_RIVER3 = new Image("tiles/woods/woodsTileRiver3.png");
            WOODS_RIVER4 = new Image("tiles/woods/woodsTileRiver4.png");
            WOODS_RIVER5 = new Image("tiles/woods/woodsTileRiver5.png");

            // rock tile
            ROCK = new Image("tiles/rock/rockTile.png");
            ROCK_RIVER1 = new Image("tiles/rock/rockTileRiver1.png");
            ROCK_RIVER2 = new Image("tiles/rock/rockTileRiver2.png");
            ROCK_RIVER3 = new Image("tiles/rock/rockTileRiver3.png");
            ROCK_RIVER4 = new Image("tiles/rock/rockTileRiver4.png");
            ROCK_RIVER5 = new Image("tiles/rock/rockTileRiver5.png");

            // desert tile
            DESERT = new Image("tiles/desert/desertTile.png");
            DESERT_RIVER1 = new Image("tiles/desert/desertTileRiver1.png");
            DESERT_RIVER2 = new Image("tiles/desert/desertTileRiver2.png");
            DESERT_RIVER3 = new Image("tiles/desert/desertTileRiver3.png");
            DESERT_RIVER4 = new Image("tiles/desert/desertTileRiver4.png");
            DESERT_RIVER5 = new Image("tiles/desert/desertTileRiver5.png");

            // mountain tile
            MOUNTAIN = new Image("tiles/mountain/mountainTile.png");
            MOUNTAIN_RIVER1 = new Image("tiles/mountain/mountainTileRiver1.png");
            MOUNTAIN_RIVER2 = new Image("tiles/mountain/mountainTileRiver2.png");
            MOUNTAIN_RIVER3 = new Image("tiles/mountain/mountainTileRiver3.png");
            MOUNTAIN_RIVER4 = new Image("tiles/mountain/mountainTileRiver4.png");
            MOUNTAIN_RIVER5 = new Image("tiles/mountain/mountainTileRiver5.png");

            SEA_TERRAIN = new Image("terrain/seaTerrain.png");
            PASTURE_TERRAIN = new Image("terrain/pastureTerrain.png");
            WOODS_TERRAIN = new Image("terrain/woodsTerrain.png");
            ROCK_TERRAIN = new Image("terrain/rockTerrain.png");
            DESERT_TERRAIN = new Image("terrain/desertTerrain.png");
            MOUNTAIN_TERRAIN = new Image("terrain/mountainTerrain.png");

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
