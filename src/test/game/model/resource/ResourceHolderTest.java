package game.model.resource;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceHolder;
import game.model.structures.resourceProducer.primaryProducer.OilRig;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceHolderTest {
    OilRig oilRig;
    @Before
    public void setUp(){
        oilRig = new OilRig();
        oilRig.storeResource(ResourceType.BOARDS,5);
        oilRig.storeResource(ResourceType.COINS,2);
        oilRig.storeResource(ResourceType.PAPER,6);
        oilRig.storeResource(ResourceType.TRUNKS,1);
        oilRig.storeResource(ResourceType.GOLD,29);
    }

    @Test
    public void getResourceCountTest(){
        HashMap<ResourceType,Integer> counts = oilRig.getResourceCounts();
        assertEquals(5,(int)counts.get(ResourceType.BOARDS));
        assertEquals(2,(int)counts.get(ResourceType.COINS));
        assertEquals(6,(int)counts.get(ResourceType.PAPER));
        assertEquals(1,(int)counts.get(ResourceType.TRUNKS));
        assertEquals(29,(int)counts.get(ResourceType.GOLD));
    }
}
