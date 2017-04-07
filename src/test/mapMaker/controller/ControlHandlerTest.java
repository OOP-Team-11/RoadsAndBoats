package mapMaker.controller;

import javafx.scene.canvas.Canvas;
import mapMaker.model.Map;
import mapMaker.model.tile.InvalidLocationException;
import mapMaker.model.tile.Terrain;
import mapMaker.model.tile.Tile;
import org.junit.Before;
import org.junit.Test;
import mapMaker.view.MapMakerView;
import mapMaker.view.TileSelectorView;

import static org.junit.Assert.*;

public class ControlHandlerTest {
    private Map mockMap;// = new Map();
    private MapMakerView mockMapMakerView;// = new MapMakerView(new Canvas());
    private TileSelectorView mockTileSelectorView;// = new TileSelectorView(new Canvas());
    private ControlHandler ch;

    @Before
    public void setUp() throws InvalidLocationException{
         mockMap = new Map();
         mockMapMakerView = new MapMakerView(new Canvas());
         mockTileSelectorView = new TileSelectorView(new Canvas());
         ch = new ControlHandler(mockMap, mockMapMakerView, mockTileSelectorView);
    }

    @Test
    public void constructorTest() throws InvalidLocationException{
        assertNotNull(ch);
        assertEquals( ch.getCurrentProtoTile().getTerrain(), Terrain.PASTURE);
        assertEquals( ch.getNextProtoTile().getTerrain(), Terrain.PASTURE);
        assertEquals( ch.getPreviousProtoTile().getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void nextRiverConfigurationTest(){
        Tile oldCurTile = ch.getCurrentProtoTile();
        Tile oldPrevTile = ch.getPreviousProtoTile();
        Tile oldNextTile = ch.getNextProtoTile();

        ch.nextRiverConfiguration();

        assertSame(oldNextTile,ch.getCurrentProtoTile());
        assertSame(oldCurTile,ch.getPreviousProtoTile());
    }




/*
    @Test
    public void notifyTileSelectObserverTest() {
        TileSelectObserver tso = new TileSelectObserver() {
            @Override
            public void updateTileSelect(TileSelectorRenderInfo tileSelectorRenderInfo) {
//                assertSame(tileSelectorRenderInfo.getLowerTile(),ch.getNextProtoTile());
//                assertSame(tileSelectorRenderInfo.getMiddleTile(),ch.getCurrentProtoTile());
//                assertSame(tileSelectorRenderInfo.getTopTile(),ch.getPreviousProtoTile());
//                assertSame(tileSelectorRenderInfo.getTerrainTypeSelection(),ch.getCurrentProtoTile().getTerrain());
                assertTrue(true);
            }
        };
        TileRenderInformation previousTileRenderInfo = new TileRenderInformation(ch.getPreviousProtoTile());
        TileRenderInformation currentTileRenderInfo = new TileRenderInformation(ch.getCurrentProtoTile());
        TileRenderInformation nextTileRenderInfo = new TileRenderInformation(ch.getNextProtoTile());

        ch.registerTileSelectObserver(tso);
        ch.notifyTileSelectObservers(new TileSelectorRenderInfo(previousTileRenderInfo, currentTileRenderInfo, nextTileRenderInfo));
    }
    */
}
