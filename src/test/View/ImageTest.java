package View;

import javafx.scene.image.Image;
import model.tile.Terrain;
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
        TileRenderInformation test = new TileRenderInformation(Terrain.DESERT);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().DESERT, image);
    }
    @Test
    public void checkTerrain2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.WOODS);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().WOODS, image);
    }
    @Test
    public void checkTerrain3(){
        TileRenderInformation test = new TileRenderInformation(Terrain.MOUNTAIN);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().MOUNTAIN, image);
    }
    @Test
    public void checkTerrain4(){
        TileRenderInformation test = new TileRenderInformation(Terrain.SEA);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().SEA, image);
    }
    @Test
    public void checkTerrain5(){
        TileRenderInformation test = new TileRenderInformation(Terrain.PASTURE);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().PASTURE, image);
    }
    @Test
    public void checkTerrain6(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        Image image = RenderToImageConverter.getTerrainImage(test,assets);
        assertEquals(assets.getInstance().ROCK, image);
    }

    @Test
    public void checkRiver1(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R0, image);
    }
    @Test
    public void checkRiver2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R1, image);
    }
    @Test
    public void checkRiver3(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R2, image);
    }
    @Test
    public void checkRiver4(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R3, image);
    }
    @Test
    public void checkRiver5(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R4, image);
    }
    @Test
    public void checkRiver6(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SPRING_R5, image);
    }
    @Test
    public void checkRiver_2_1(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R0, image);
    }
    @Test
    public void checkRiver_2_2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R1, image);
    }
    @Test
    public void checkRiver_2_3(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R2, image);
    }

    @Test
    public void checkRiver_2_4(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R3, image);
    }

    @Test
    public void checkRiver_2_5(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R4, image);
    }

    @Test
    public void checkRiver_2_6(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_ADJACENT_R5, image);
    }

    @Test
    public void checkRiver_3_1(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R0, image);
    }

    @Test
    public void checkRiver_3_2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R1, image);
    }

    @Test
    public void checkRiver_3_3(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R2, image);
    }

    @Test
    public void checkRiver_3_4(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R3, image);
    }

    @Test
    public void checkRiver_3_5(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R4, image);
    }

    @Test
    public void checkRiver_3_6(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_SKIP_R5, image);
    }

    @Test
    public void checkRiver_4_1(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R0, image);
    }
    @Test
    public void checkRiver_4_2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R1, image);
    }
    @Test
    public void checkRiver_4_3(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_OPPOSITE_R2, image);
    }

    @Test
    public void checkRiver_5_1(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(true);
        test.setNorthEast(false);
        test.setSouthEast(true);
        test.setSouth(false);
        test.setSouthWest(true);
        test.setNorthWest(false);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_EVERYOTHER_R0 , image);
    }

    @Test
    public void checkRiver_5_2(){
        TileRenderInformation test = new TileRenderInformation(Terrain.ROCK);
        test.setNorth(false);
        test.setNorthEast(true);
        test.setSouthEast(false);
        test.setSouth(true);
        test.setSouthWest(false);
        test.setNorthWest(true);
        Image image = RenderToImageConverter.getRiverImage(test,assets);
        assertEquals(assets.getInstance().RIVER_EVERYOTHER_R1 , image);
    }




}
