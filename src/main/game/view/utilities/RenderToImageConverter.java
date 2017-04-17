package game.view.utilities;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.transport.TransportType;
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

    public Image getBlueTransportImage(TransportType transportType){
        if(transportType.equals(TransportType.TRUCK)){
            return assets.TRUCK_BLUE;
        } else if (transportType.equals(TransportType.WAGON)){
            return assets.WAGON_BLUE;
        } else if(transportType.equals(TransportType.DONKEY)){
            return assets.DONKEY_BLUE;
        } else if(transportType.equals(TransportType.RAFT)){
            return assets.RAFT_BLUE;
        } else if(transportType.equals(TransportType.STEAMSHIP)){
            return assets.STEAMSHIP_BLUE;
        } else if(transportType.equals(TransportType.ROWBOAT)){
            return assets.ROWBOAT_BLUE;
        } else {
            System.out.println("ERROR: Can't match TransportType to image");
            return null;
        }
    }

    public Image getRedTransportImage(TransportType transportType){
        if(transportType.equals(TransportType.TRUCK)){
            return assets.TRUCK_RED;
        } else if (transportType.equals(TransportType.WAGON)){
            return assets.WAGON_RED;
        } else if(transportType.equals(TransportType.DONKEY)){
            return assets.DONKEY_RED;
        } else if(transportType.equals(TransportType.RAFT)){
            return assets.RAFT_RED;
        } else if(transportType.equals(TransportType.STEAMSHIP)){
            return assets.STEAMSHIP_RED;
        } else if(transportType.equals(TransportType.ROWBOAT)){
            return assets.ROWBOAT_RED;
        } else {
            System.out.println("ERROR: Can't match TransportType to image");
            return null;
        }
    }

    public Image getStructureImage(StructureType structureType){
        if(structureType.equals(StructureType.CLAYPIT)){
            return assets.CLAY_PIT_BUILDING;
        } else if(structureType.equals(StructureType.COAL_BURNER)){
            return assets.COAL_BURNER_BUILDING;
        } else if(structureType.equals(StructureType.MINE)){
            return assets.MINE_BUILDING;
        } else if(structureType.equals(StructureType.MINT)){
            return assets.MINT_BUILDING;
        } else if(structureType.equals(StructureType.OIL_RIG)){
            return assets.OIL_RIG_BUILDING;
        } else if(structureType.equals(StructureType.PAPERMILL)){
            return assets.PAPERMILL_BUILDING;
        } else if(structureType.equals(StructureType.RAFT_FACTORY)){
            return assets.RAFT_FACTORY;
        } else if(structureType.equals(StructureType.ROWBOAT_FACTORY)){
            return assets.ROWBOAT_FACTORY;
        } else if(structureType.equals(StructureType.SAWMILL)){
            return assets.SAWMILL_BUILDING;
        } else if(structureType.equals(StructureType.STEAMER_FACTORY)){
            return assets.STEAMER_FACTORY;
        } else if(structureType.equals(StructureType.STOCK_MARKET)){
            return assets.STOCK_EXCHANGE_BUILDING;
        } else if(structureType.equals(StructureType.STONE_QUARRY)){
            return assets.QUARRY_BUILDING;
        } else if(structureType.equals(StructureType.STONE_FACTORY)){
            return assets.STONE_FACTORY_BUILDING;
        } else if(structureType.equals(StructureType.WOODCUTTER)){
            return assets.WOODCUTTER_BUILDING;
        } else if(structureType.equals(StructureType.TRANSPORT_PRODUCER)){
            return null; // NOTHING FOR THIS
        } else if(structureType.equals(StructureType.TRUCK_FACTORY)){
            return assets.TRUCK_FACTORY;
        } else if(structureType.equals(StructureType.WAGON_FACTORY)){
            return assets.WAGON_FACTORY;
        } else {
            System.out.println("ERROR: Can't match structure to image");
            return null;
        }
    }

    public Image getResourceImage(ResourceType resourceType){
        if(resourceType.equals(ResourceType.TRUNKS)){
            return assets.TRUNKS_GOODS;
        } else if(resourceType.equals(ResourceType.BOARDS)){
            return assets.BOARDS_GOODS;
        } else if(resourceType.equals(ResourceType.PAPER)){
            return assets.PAPER_GOODS;
        } else if(resourceType.equals(ResourceType.GOOSE)){
            return assets.GOOSE_GOODS;
        } else if(resourceType.equals(ResourceType.CLAY)){
            return assets.CLAY_GOODS;
        } else if(resourceType.equals(ResourceType.FUEL)){
            return assets.FUEL_GOODS;
        } else if(resourceType.equals(ResourceType.IRON)){
            return assets.IRON_GOODS;
        } else if(resourceType.equals(ResourceType.GOLD)){
            return assets.GOLD_GOODS;
        } else if(resourceType.equals(ResourceType.COINS)){
            return assets.COINS_GOODS;
        } else if(resourceType.equals(ResourceType.STOCKBOND)){
            return assets.STOCK_GOODS;
        } else{
            System.out.println("ERROR: Can't match resource to image");
            return null;
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
                return Assets.getInstance().RIVER_OPPOSITE_R0;
            } else if(riverConfiguration.canConnectNortheast() && riverConfiguration.canConnectSouthwest()){
                return Assets.getInstance().RIVER_OPPOSITE_R1;
            } else if (riverConfiguration.canConnectSoutheast()  && riverConfiguration.canConnectNorthwest()){
                return Assets.getInstance().RIVER_OPPOSITE_R2;
            } else if (riverConfiguration.canConnectNorth() && riverConfiguration.canConnectNortheast()){
                return Assets.getInstance().RIVER_ADJACENT_R0;
            } else if (riverConfiguration.canConnectNortheast() && riverConfiguration.canConnectSoutheast() ){
                return Assets.getInstance().RIVER_ADJACENT_R1;
            } else if (riverConfiguration.canConnectSoutheast() && riverConfiguration.canConnectSouth()){
                return Assets.getInstance().RIVER_ADJACENT_R2;
            } else if (riverConfiguration.canConnectSouth() && riverConfiguration.canConnectSouthwest()){
                return Assets.getInstance().RIVER_ADJACENT_R3;
            } else if (riverConfiguration.canConnectSouthwest() && riverConfiguration.canConnectNorthwest()){
                return Assets.getInstance().RIVER_ADJACENT_R4;
            } else if (riverConfiguration.canConnectNorthwest()&& riverConfiguration.canConnectNorth()){
                return Assets.getInstance().RIVER_ADJACENT_R5;
            } else if (riverConfiguration.canConnectNorth() && riverConfiguration.canConnectSoutheast() ){
                return Assets.getInstance().RIVER_SKIP_R0;
            } else if (riverConfiguration.canConnectNortheast() && riverConfiguration.canConnectSouth()){
                return Assets.getInstance().RIVER_SKIP_R1;
            } else if (riverConfiguration.canConnectSoutheast() && riverConfiguration.canConnectSouthwest()){
                return Assets.getInstance().RIVER_SKIP_R2;
            } else if (riverConfiguration.canConnectSouth() && riverConfiguration.canConnectNorthwest()){
                return Assets.getInstance().RIVER_SKIP_R3;
            } else if (riverConfiguration.canConnectSouthwest() && riverConfiguration.canConnectNorth()){
                return Assets.getInstance().RIVER_SKIP_R4;
            } else {
                return Assets.getInstance().RIVER_SKIP_R5;
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
