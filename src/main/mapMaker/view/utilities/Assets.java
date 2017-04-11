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
    // primary
    public Image CLAY_PIT_BUILDING;
    public Image OIL_RIG_BUILDING;
    public Image QUARRY_BUILDING;
    public Image WOODCUTTER_BUILDING;
    public Image MINE_BUILDING;

    // secondary
    public Image COAL_BURNER_BUILDING;
    public Image MINT_BUILDING;
    public Image PAPERMILL_BUILDING;
    public Image SAWMILL_BUILDING;
    public Image STOCK_EXCHANGE_BUILDING;
    public Image STONE_FACTORY_BUILDING;

    // factory buildings
    public Image RAFT_FACTORY;
    public Image ROWBOAT_FACTORY;
    public Image STEAMER_FACTORY;
    public Image TRUCK_FACTORY;
    public Image WAGON_FACTORY;

    // goods
    public Image BOARDS_GOODS;
    public Image CLAY_GOODS;
    public Image COINS_GOODS;
    public Image FUEL_GOODS;
    public Image GOLD_GOODS;
    public Image GOOSE_GOODS;
    public Image IRON_GOODS;
    public Image PAPER_GOODS;
    public Image STOCK_GOODS;
    public Image STONE_GOODS;
    public Image TRUNKS_GOODS;

    // research
    public Image BIG_MINE_RESEARCH;
    public Image NEW_SHAFT_RESEARCH;
    public Image OIL_RIG_RESEARCH;
    public Image ROWBOAT_RESEARCH;
    public Image SPECIALIZE_MINE_RESEARCH;
    public Image STEAMSHIP_RESEARCH;
    public Image TRUCK_RESEARCH;
    public Image BRIGHT_IDEA_RESEARCH;
    public Image RESEARCH_TABLE;

    // wonder
    public Image WONDER;
    public Image WONDERBRICK_BLUE;
    public Image WONDERBRICK_NEUTRAL;
    public Image WONDERBRICK_RED;

    // player-owned
    public Image HOUSE_BLUE;
    public Image HOUSE_RED;

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

            CLAY_PIT_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/clay_pit.png"));
            OIL_RIG_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/oil_rig.png"));
            QUARRY_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/quarry.png"));
            WOODCUTTER_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/woodcutter.png"));
            MINE_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/mine.png"));

            COAL_BURNER_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/coal_burner.png"));
            MINT_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/mint.png"));
            PAPERMILL_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/papermill.png"));
            SAWMILL_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/sawmill.png"));
            STOCK_EXCHANGE_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/stock_exchange.png"));
            STONE_FACTORY_BUILDING = new Image(this.getClass().getResourceAsStream("/buildings/stone_factory.png"));

            RAFT_FACTORY = new Image(this.getClass().getResourceAsStream("/buildings/raft_factory.png"));
            ROWBOAT_FACTORY = new Image(this.getClass().getResourceAsStream("/buildings/rowboat_factory.png"));
            STEAMER_FACTORY = new Image(this.getClass().getResourceAsStream("/buildings/steamer_factory.png"));
            TRUCK_FACTORY = new Image(this.getClass().getResourceAsStream("/buildings/truck_factory.png"));
            WAGON_FACTORY = new Image(this.getClass().getResourceAsStream("/buildings/wagon_factory.png"));

            BOARDS_GOODS = new Image(this.getClass().getResourceAsStream("/goods/boards.png"));
            CLAY_GOODS = new Image(this.getClass().getResourceAsStream("/goods/clay.png"));
            COINS_GOODS = new Image(this.getClass().getResourceAsStream("/goods/coins.png"));
            FUEL_GOODS = new Image(this.getClass().getResourceAsStream("/goods/fuel.png"));
            GOLD_GOODS = new Image(this.getClass().getResourceAsStream("/goods/gold.png"));
            GOOSE_GOODS = new Image(this.getClass().getResourceAsStream("/goods/goose.png"));
            IRON_GOODS = new Image(this.getClass().getResourceAsStream("/goods/iron.png"));
            PAPER_GOODS = new Image(this.getClass().getResourceAsStream("/goods/paper.png"));
            STOCK_GOODS = new Image(this.getClass().getResourceAsStream("/goods/stock.png"));
            STONE_GOODS = new Image(this.getClass().getResourceAsStream("/goods/stone.png"));
            TRUNKS_GOODS = new Image(this.getClass().getResourceAsStream("/goods/trunks.png"));

            BIG_MINE_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/big_mine_research.png"));
            NEW_SHAFT_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/new_shaft_research.png"));
            OIL_RIG_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/oil_rig_research.png"));
            ROWBOAT_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/rowboat_research.png"));
            SPECIALIZE_MINE_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/specialize_mine_research.png"));
            STEAMSHIP_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/steamship_research.png"));
            TRUCK_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/truck_research.png"));
            BRIGHT_IDEA_RESEARCH = new Image(this.getClass().getResourceAsStream("/research/bright_idea.png"));
            RESEARCH_TABLE = new Image(this.getClass().getResourceAsStream("/research/research_table.png"));

            WONDER = new Image(this.getClass().getResourceAsStream("/wonder/wonder.png"));
            WONDERBRICK_BLUE = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_blue.jpg"));
            WONDERBRICK_NEUTRAL = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_neutral.jpg"));
            WONDERBRICK_RED = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_red.jpg"));

            HOUSE_BLUE = new Image(this.getClass().getResourceAsStream("/player/house_blue.jpg"));
            HOUSE_RED = new Image(this.getClass().getResourceAsStream("/player/house_red.jpg"));

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
