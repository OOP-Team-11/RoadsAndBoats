package mapMaker.View;

import mapMaker.direction.mmAngle;
import javafx.scene.image.Image;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import mapMaker.view.render.mmTileRenderInformation;
import org.junit.Test;
import mapMaker.view.render.mmRenderToImageConverter;
import mapMaker.view.utilities.mmAssets;


import static junit.framework.TestCase.assertEquals;



public class ImageTest {

    private static mmAssets mmAssets;

    public ImageTest(){
        mmAssets = mmAssets.getInstance();
        mmAssets.loadAssets();
    }

    @Test
    public void checkTerrain1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.DESERT,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().DESERT, image);
    }

    @Test
    public void checkTerrain2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.WOODS,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().WOODS, image);
    }
    @Test
    public void checkTerrain3(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.MOUNTAIN,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().MOUNTAIN, image);
    }
    @Test
    public void checkTerrain4(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.SEA,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().SEA, image);
    }
    @Test
    public void checkTerrain5(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.PASTURE,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().PASTURE, image);
    }
    @Test
    public void checkTerrain6(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getTerrainImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().ROCK, image);
    }


    @Test
    public void checkRiver1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R0, image);
    }

    @Test
    public void checkRiver2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(2);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R1, image);
    }
    @Test
    public void checkRiver3(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(3);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R2, image);
    }
    @Test
    public void checkRiver4(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(4);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R3, image);
    }
    @Test
    public void checkRiver5(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(5);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R4, image);
    }

    @Test
    public void checkRiver6(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SPRING_R5, image);
    }


    @Test
    public void checkRiver_2_1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1,2);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R0, image);
    }


    @Test
    public void checkRiver_2_2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(2,3);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R1, image);
    }
    @Test
    public void checkRiver_2_3(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(3,4);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R2, image);
    }

    @Test
    public void checkRiver_2_4(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(4,5);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R3, image);
    }

    @Test
    public void checkRiver_2_5(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(5,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R4, image);
    }

    @Test
    public void checkRiver_2_6(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(5,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        testMmTile.rotate(new mmAngle(60));
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_ADJACENT_R5, image);
    }


    @Test
    public void checkRiver_3_1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1,3);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R0, image);
    }

    @Test
    public void checkRiver_3_2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(2,4);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R1, image);
    }

    @Test
    public void checkRiver_3_3(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(3,5);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R2, image);
    }

    @Test
    public void checkRiver_3_4(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(4,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R3, image);
    }

    @Test
    public void checkRiver_3_5(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(4,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        testMmTile.rotate(new mmAngle(60));
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R4, image);
    }

    @Test
    public void checkRiver_3_6(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(4,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        testMmTile.rotate(new mmAngle(120));
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_SKIP_R5, image);
    }

    @Test
    public void checkRiver_4_1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1,4);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_OPPOSITE_R0, image);
    }
    @Test
    public void checkRiver_4_2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(2,5);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_OPPOSITE_R1, image);
    }
    @Test
    public void checkRiver_4_3(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(3,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_OPPOSITE_R2, image);
    }

    @Test
    public void checkRiver_5_1(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(1,3,5);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_EVERYOTHER_R0 , image);
    }

    @Test
    public void checkRiver_5_2(){
        mmRiverConfiguration configuration = new mmRiverConfiguration(2,4,6);
        mmTile testMmTile = new mmTile(mmTerrain.ROCK,configuration);
        mmTileRenderInformation test = new mmTileRenderInformation(testMmTile);
        Image image = mmRenderToImageConverter.getRiverImage(test, mmAssets);
        assertEquals(mmAssets.getInstance().RIVER_EVERYOTHER_R1 , image);
    }

}
