package game.view.utilities;

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
    public Image WOODS;
    public Image ROCK;
    public Image DESERT;
    public Image MOUNTAIN;

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
    public Image NAVIGATION_BAR_2;
    public Image NAVIGATION_BAR_3;
    public Image NAVIGATION_BAR_4;
    public Image NAVIGATION_BAR_5;
    public Image NAVIGATION_BAR_6;

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
    public Image GLASS_STONE;

    // model.wonder
    public Image WONDER;
    public Image WONDERBRICK_BLUE;
    public Image WONDERBRICK_NEUTRAL;
    public Image WONDERBRICK_RED;

    // player-owned
    public Image HOUSE_BLUE;
    public Image HOUSE_RED;

    public Image DONKEY_RED;
    public Image RAFT_RED;
    public Image ROWBOAT_RED;
    public Image STEAMSHIP_RED;
    public Image TRUCK_RED;
    public Image WAGON_RED;

    public Image DONKEY_BLUE;
    public Image RAFT_BLUE;
    public Image ROWBOAT_BLUE;
    public Image STEAMSHIP_BLUE;
    public Image TRUCK_BLUE;
    public Image WAGON_BLUE;

    // neutral wall
    public Image WALL_NEUTRAL_NORTH;
    public Image WALL_NEUTRAL_NORTHEAST;
    public Image WALL_NEUTRAL_SOUTHEAST;
    public Image WALL_NEUTRAL_SOUTH;
    public Image WALL_NEUTRAL_SOUTHWEST;
    public Image WALL_NEUTRAL_NORTHWEST;

    // red wall
    public Image WALL_RED_NORTH;
    public Image WALL_RED_NORTHEAST;
    public Image WALL_RED_SOUTHEAST;
    public Image WALL_RED_SOUTH;
    public Image WALL_RED_SOUTHWEST;
    public Image WALL_RED_NORTHWEST;

    // blue wall
    public Image WALL_BLUE_NORTH;
    public Image WALL_BLUE_NORTHEAST;
    public Image WALL_BLUE_SOUTHEAST;
    public Image WALL_BLUE_SOUTH;
    public Image WALL_BLUE_SOUTHWEST;
    public Image WALL_BLUE_NORTHWEST;

    public Image MINE_AMOUNT_0;
    public Image MINE_AMOUNT_1;
    public Image MINE_AMOUNT_2;
    public Image MINE_AMOUNT_3;
    public Image MINE_AMOUNT_4;
    public Image MINE_AMOUNT_5;
    public Image MINE_AMOUNT_6;
    public Image MINE_AMOUNT_7;
    public Image MINE_AMOUNT_8;
    public Image MINE_AMOUNT_9;
    public Image MINE_AMOUNT_10;
    public Image MINE_AMOUNT_UNKOWN;


    public Image ROAD_N;
    public Image ROAD_NE;
    public Image ROAD_SE;
    public Image ROAD_S;
    public Image ROAD_SW;
    public Image ROAD_NW;
    public Image ROAD_N_TO_S;


    public Image PROCESS_CHART;

    public boolean loadAssets(){
        try{
            // tiles
            SEA = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/seaTile.png"));
            PASTURE = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/pastureTile.png"));
            WOODS = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/woodsTile.png"));
            ROCK = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/rockTile.png"));
            DESERT = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/desertTile.png"));
            MOUNTAIN = new Image(this.getClass().getResourceAsStream("/tiles/terrainsHex/mountainTile.png"));

            SEA_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/seaTerrain.png"));
            PASTURE_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/pastureTerrain.png"));
            WOODS_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/woodsTerrain.png"));
            ROCK_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/rockTerrain.png"));
            DESERT_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/desertTerrain.png"));
            MOUNTAIN_TERRAIN = new Image(this.getClass().getResourceAsStream("/terrain/mountainTerrain.png"));

            RIVER_SPRING_R0 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR0.png"));
            RIVER_SPRING_R1 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR1.png"));
            RIVER_SPRING_R2 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR2.png"));
            RIVER_SPRING_R3 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR3.png"));
            RIVER_SPRING_R4 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR4.png"));
            RIVER_SPRING_R5 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river1_SpringR5.png"));

            RIVER_ADJACENT_R0 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR0.png"));
            RIVER_ADJACENT_R1 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR1.png"));
            RIVER_ADJACENT_R2 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR2.png"));
            RIVER_ADJACENT_R3 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR3.png"));
            RIVER_ADJACENT_R4 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR4.png"));
            RIVER_ADJACENT_R5 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river2_AdjacentR5.png"));

            RIVER_SKIP_R0 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR0.png"));
            RIVER_SKIP_R1 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR1.png"));
            RIVER_SKIP_R2 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR2.png"));
            RIVER_SKIP_R3 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR3.png"));
            RIVER_SKIP_R4 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR4.png"));
            RIVER_SKIP_R5 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river3_SkipR5.png"));

            RIVER_OPPOSITE_R0 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river4_OppositeR0.png"));
            RIVER_OPPOSITE_R1 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river4_OppositeR1.png"));
            RIVER_OPPOSITE_R2 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river4_OppositeR2.png"));

            RIVER_EVERYOTHER_R0 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river5_EveryOtherR0.png"));
            RIVER_EVERYOTHER_R1 = new Image(this.getClass().getResourceAsStream("/tiles/riversHex/river5_EveryOtherR1.png"));

            GREEN_CURSOR = new Image(this.getClass().getResourceAsStream("/tiles/greenCursor.png"));
            RED_CURSOR = new Image(this.getClass().getResourceAsStream("/tiles/redCursor.png"));

            ARROW_KEYS = new Image(this.getClass().getResourceAsStream("/misc/arrowkeys.png"));
            FADED = new Image(this.getClass().getResourceAsStream("/tiles/fadedHex.png"));

            BACKGROUND = new Image(this.getClass().getResourceAsStream("/misc/WelcomeImage.png"));

            NAVIGATION_BAR_1 = new Image(this.getClass().getResourceAsStream("/buttons/mainViewButton.png"));
            NAVIGATION_BAR_2 = new Image(this.getClass().getResourceAsStream("/buttons/transportViewButton.png"));
            NAVIGATION_BAR_3 = new Image(this.getClass().getResourceAsStream("/buttons/researchViewButton.png"));
            NAVIGATION_BAR_4 = new Image(this.getClass().getResourceAsStream("/buttons/wonderViewButton.png"));
            NAVIGATION_BAR_5 = new Image(this.getClass().getResourceAsStream("/buttons/saveViewButton.png"));
            NAVIGATION_BAR_6 = new Image(this.getClass().getResourceAsStream("/buttons/informationViewButton.png"));

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
            GLASS_STONE = new Image(this.getClass().getResourceAsStream("/research/glassSmall.png"));

            WONDER = new Image(this.getClass().getResourceAsStream("/wonder/wonder.png"));
            WONDERBRICK_BLUE = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_blue.jpg"));
            WONDERBRICK_NEUTRAL = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_neutral.jpg"));
            WONDERBRICK_RED = new Image(this.getClass().getResourceAsStream("/wonder/wonderbrick_red.jpg"));

            HOUSE_BLUE = new Image(this.getClass().getResourceAsStream("/player/house_blue.png"));
            HOUSE_RED = new Image(this.getClass().getResourceAsStream("/player/house_red.png"));

            DONKEY_RED = new Image(this.getClass().getResourceAsStream("/player/donkey_red.png"));
            RAFT_RED = new Image(this.getClass().getResourceAsStream("/player/raft_red.png"));
            ROWBOAT_RED = new Image(this.getClass().getResourceAsStream("/player/rowboat_red.png"));
            STEAMSHIP_RED = new Image(this.getClass().getResourceAsStream("/player/steamship_red.png"));
            TRUCK_RED = new Image(this.getClass().getResourceAsStream("/player/truck_red.png"));
            WAGON_RED = new Image(this.getClass().getResourceAsStream("/player/wagon_red.png"));

            DONKEY_BLUE = new Image(this.getClass().getResourceAsStream("/player/donkey_blue.png"));
            RAFT_BLUE = new Image(this.getClass().getResourceAsStream("/player/raft_blue.png"));
            ROWBOAT_BLUE = new Image(this.getClass().getResourceAsStream("/player/rowboat_blue.png"));
            STEAMSHIP_BLUE = new Image(this.getClass().getResourceAsStream("/player/steamship_blue.png"));
            TRUCK_BLUE = new Image(this.getClass().getResourceAsStream("/player/truck_blue.png"));
            WAGON_BLUE = new Image(this.getClass().getResourceAsStream("/player/wagon_blue.png"));


            WALL_NEUTRAL_NORTH = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_N.png"));
            WALL_NEUTRAL_NORTHEAST = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_NE.png"));
            WALL_NEUTRAL_SOUTHEAST = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_SE.png"));
            WALL_NEUTRAL_SOUTH = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_S.png"));
            WALL_NEUTRAL_SOUTHWEST = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_SW.png"));
            WALL_NEUTRAL_NORTHWEST = new Image(this.getClass().getResourceAsStream("/walls/neutralWall/neutralwall_NW.png"));

            WALL_RED_NORTH = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_N.png"));
            WALL_RED_NORTHEAST = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_NE.png"));
            WALL_RED_SOUTHEAST = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_SE.png"));
            WALL_RED_SOUTH = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_S.png"));
            WALL_RED_SOUTHWEST = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_SW.png"));
            WALL_RED_NORTHWEST = new Image(this.getClass().getResourceAsStream("/walls/redWall/redwall_NW.png"));

            WALL_BLUE_NORTH = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_N.png"));
           WALL_BLUE_NORTHEAST = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_NE.png"));
            WALL_BLUE_SOUTHEAST = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_SE.png"));
            WALL_BLUE_SOUTH = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_S.png"));
            WALL_BLUE_SOUTHWEST = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_SW.png"));
            WALL_BLUE_NORTHWEST = new Image(this.getClass().getResourceAsStream("/walls/blueWall/bluewall_NW.png"));

            MINE_AMOUNT_0 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number0.png"));
            MINE_AMOUNT_1 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number1.png"));
            MINE_AMOUNT_2 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number2.png"));
            MINE_AMOUNT_3 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number3.png"));
            MINE_AMOUNT_4 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number4.png"));
            MINE_AMOUNT_5 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number5.png"));
            MINE_AMOUNT_6 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number6.png"));
            MINE_AMOUNT_7 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number7.png"));
            MINE_AMOUNT_8 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number8.png"));
            MINE_AMOUNT_9 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number9.png"));
            MINE_AMOUNT_10 = new Image(this.getClass().getResourceAsStream("/buildings/mine_number10.png"));
            MINE_AMOUNT_UNKOWN = new Image(this.getClass().getResourceAsStream("/buildings/mine_unknown.png"));

            ROAD_N = new Image(this.getClass().getResourceAsStream("/roads/roadN.png"));
            ROAD_NE =  new Image(this.getClass().getResourceAsStream("/roads/roadNE.png"));
            ROAD_SE =  new Image(this.getClass().getResourceAsStream("/roads/roadSE.png"));
            ROAD_S =  new Image(this.getClass().getResourceAsStream("/roads/roadS.png"));
            ROAD_SW =  new Image(this.getClass().getResourceAsStream("/roads/roadSW.png"));
            ROAD_NW =  new Image(this.getClass().getResourceAsStream("/roads/roadNW.png"));
            ROAD_N_TO_S = new Image(this.getClass().getResourceAsStream("/roads/roadN_TO_S.png"));

            PROCESS_CHART = new Image(this.getClass().getResourceAsStream("/misc/process_chart.jpg"));

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
