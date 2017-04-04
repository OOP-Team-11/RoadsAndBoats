package View;

import direction.Angle;
import javafx.scene.image.Image;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Test;
import view.render.RenderToImageConverter;
import view.render.TileRenderInformation;
import view.utilities.Assets;


import static junit.framework.TestCase.assertEquals;



public class ImageTest {

    private static Assets assets;

    public ImageTest(){
        assets = assets.getInstance();
        assets.loadAssets();
    }

    @Test
    public void checkTerrain1(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.DESERT,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().DESERT, image);
    }

    @Test
    public void checkTerrain2(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.WOODS,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().WOODS, image);
    }
    @Test
    public void checkTerrain3(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.MOUNTAIN,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().MOUNTAIN, image);
    }
    @Test
    public void checkTerrain4(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.SEA,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().SEA, image);
    }
    @Test
    public void checkTerrain5(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.PASTURE,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().PASTURE, image);
    }
    @Test
    public void checkTerrain6(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().ROCK, image);
    }


    @Test
    public void checkRiver1(){
        RiverConfiguration configuration = new RiverConfiguration(1);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R0, image);
    }

    @Test
    public void checkRiver2(){
        RiverConfiguration configuration = new RiverConfiguration(2);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R1, image);
    }
    @Test
    public void checkRiver3(){
        RiverConfiguration configuration = new RiverConfiguration(3);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R2, image);
    }
    @Test
    public void checkRiver4(){
        RiverConfiguration configuration = new RiverConfiguration(4);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R3, image);
    }
    @Test
    public void checkRiver5(){
        RiverConfiguration configuration = new RiverConfiguration(5);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R4, image);
    }

    @Test
    public void checkRiver6(){
        RiverConfiguration configuration = new RiverConfiguration(6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R5, image);
    }


    @Test
    public void checkRiver_2_1(){
        RiverConfiguration configuration = new RiverConfiguration(1,2);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R0, image);
    }


    @Test
    public void checkRiver_2_2(){
        RiverConfiguration configuration = new RiverConfiguration(2,3);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R1, image);
    }
    @Test
    public void checkRiver_2_3(){
        RiverConfiguration configuration = new RiverConfiguration(3,4);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R2, image);
    }

    @Test
    public void checkRiver_2_4(){
        RiverConfiguration configuration = new RiverConfiguration(4,5);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R3, image);
    }

    @Test
    public void checkRiver_2_5(){
        RiverConfiguration configuration = new RiverConfiguration(5,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R4, image);
    }

    @Test
    public void checkRiver_2_6(){
        RiverConfiguration configuration = new RiverConfiguration(5,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        testTile.rotate(new Angle(60));
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R5, image);
    }


    @Test
    public void checkRiver_3_1(){
        RiverConfiguration configuration = new RiverConfiguration(1,3);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R0, image);
    }

    @Test
    public void checkRiver_3_2(){
        RiverConfiguration configuration = new RiverConfiguration(2,4);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R1, image);
    }

    @Test
    public void checkRiver_3_3(){
        RiverConfiguration configuration = new RiverConfiguration(3,5);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R2, image);
    }

    @Test
    public void checkRiver_3_4(){
        RiverConfiguration configuration = new RiverConfiguration(4,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R3, image);
    }

    @Test
    public void checkRiver_3_5(){
        RiverConfiguration configuration = new RiverConfiguration(4,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        testTile.rotate(new Angle(60));
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R4, image);
    }

    @Test
    public void checkRiver_3_6(){
        RiverConfiguration configuration = new RiverConfiguration(4,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        testTile.rotate(new Angle(120));
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R5, image);
    }

    @Test
    public void checkRiver_4_1(){
        RiverConfiguration configuration = new RiverConfiguration(1,4);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R0, image);
    }
    @Test
    public void checkRiver_4_2(){
        RiverConfiguration configuration = new RiverConfiguration(2,5);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R1, image);
    }
    @Test
    public void checkRiver_4_3(){
        RiverConfiguration configuration = new RiverConfiguration(3,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R2, image);
    }

    @Test
    public void checkRiver_5_1(){
        RiverConfiguration configuration = new RiverConfiguration(1,3,5);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_EVERYOTHER_R0 , image);
    }

    @Test
    public void checkRiver_5_2(){
        RiverConfiguration configuration = new RiverConfiguration(2,4,6);
        Tile testTile = new Tile(Terrain.ROCK,configuration);
        TileRenderInformation test = new TileRenderInformation(testTile);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_EVERYOTHER_R1 , image);
    }

}
