package mapMaker.controller;

import javafx.scene.canvas.Canvas;
import mapMaker.model.mmMap;
import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import org.junit.Before;
import org.junit.Test;
import mapMaker.view.mmMapMakerView;
import mapMaker.view.mmTileSelectorView;

import static org.junit.Assert.*;

public class mmControlHandlerTest {
    private mmMap mockMmMap;// = new mmMap();
    private mmMapMakerView mockMapMakerView;// = new mmMapMakerView(new Canvas());
    private mmTileSelectorView mockTileSelectorView;// = new mmTileSelectorView(new Canvas());
    private mmControlHandler ch;

    @Before
    public void setUp() throws mmInvalidLocationException {
         mockMmMap = new mmMap();
         mockMapMakerView = new mmMapMakerView(new Canvas());
         mockTileSelectorView = new mmTileSelectorView(new Canvas());
         ch = new mmControlHandler(mockMmMap, mockMapMakerView, mockTileSelectorView);
    }

    @Test
    public void constructorTest() throws mmInvalidLocationException {
        assertNotNull(ch);
        assertEquals( ch.getCurrentProtoMmTile().getMmTerrain(), mmTerrain.PASTURE);
        assertEquals( ch.getNextProtoMmTile().getMmTerrain(), mmTerrain.PASTURE);
        assertEquals( ch.getPreviousProtoMmTile().getMmTerrain(), mmTerrain.PASTURE);
    }

    @Test
    public void nextRiverConfigurationTest(){
        mmTile oldCurMmTile = ch.getCurrentProtoMmTile();
        mmTile oldPrevMmTile = ch.getPreviousProtoMmTile();
        mmTile oldNextMmTile = ch.getNextProtoMmTile();

        ch.nextRiverConfiguration();

        assertSame(oldNextMmTile,ch.getCurrentProtoMmTile());
        assertSame(oldCurMmTile,ch.getPreviousProtoMmTile());
    }




/*
    @Test
    public void notifyTileSelectObserverTest() {
        mmTileSelectObserver tso = new mmTileSelectObserver() {
            @Override
            public void updateTileSelect(mmTileSelectorRenderInfo tileSelectorRenderInfo) {
//                assertSame(tileSelectorRenderInfo.getLowerTile(),ch.getNextProtoMmTile());
//                assertSame(tileSelectorRenderInfo.getMiddleTile(),ch.getCurrentProtoMmTile());
//                assertSame(tileSelectorRenderInfo.getTopTile(),ch.getPreviousProtoMmTile());
//                assertSame(tileSelectorRenderInfo.getTerrainTypeSelection(),ch.getCurrentProtoMmTile().getMmTerrain());
                assertTrue(true);
            }
        };
        mmTileRenderInformation previousTileRenderInfo = new mmTileRenderInformation(ch.getPreviousProtoMmTile());
        mmTileRenderInformation currentTileRenderInfo = new mmTileRenderInformation(ch.getCurrentProtoMmTile());
        mmTileRenderInformation nextTileRenderInfo = new mmTileRenderInformation(ch.getNextProtoMmTile());

        ch.registerTileSelectObserver(tso);
        ch.notifyTileSelectObservers(new mmTileSelectorRenderInfo(previousTileRenderInfo, currentTileRenderInfo, nextTileRenderInfo));
    }
    */
}
