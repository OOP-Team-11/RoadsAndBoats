package game.model.managers;

import game.model.resources.ResourceType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceManagerTest {

    private ResourceManager rm;

    @Before
    public void setShitUp(){
        rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS,1);
    }

    @Test
    public void hasResourceAndActuallyDoesTest() {
        assertTrue(rm.hasResource(ResourceType.BOARDS));
    }

    @Test
    public void hasResourceButDoesntReallyTest() {
        assertTrue(!(rm.hasResource(ResourceType.GOLD)));
    }

    @Test
    public void getResourceCountPositiveTest() {
        assertEquals(1, rm.getResourceCount(ResourceType.BOARDS));
    }

    @Test
    public void getResourceCountZeroTest() {
        assertEquals(0, rm.getResourceCount(ResourceType.GOLD));
    }

    @Test
    public void addResourceTest() {
        assertTrue(! rm.hasResource(ResourceType.CLAY));
        assertEquals(0, rm.getResourceCount(ResourceType.CLAY));
        rm.addResource(ResourceType.CLAY,1);
        assertTrue(rm.hasResource(ResourceType.CLAY));
        assertEquals(1, rm.getResourceCount(ResourceType.CLAY));
    }

    @Test
    public void removeResourceWhenResourceCanBeRemovedTest() {
        rm.addResource(ResourceType.BOARDS,1);
        boolean didRemove = rm.removeResource(ResourceType.BOARDS,1);
        assertTrue(didRemove);
        assertEquals(1,rm.getResourceCount(ResourceType.BOARDS));
        didRemove = rm.removeResource(ResourceType.BOARDS,1);
        assertTrue(didRemove);
        assertEquals(0,rm.getResourceCount(ResourceType.BOARDS));
    }

    @Test
    public void removeResourceButThereAreNoneToRemoveTest() {
        boolean didRemove = rm.removeResource(ResourceType.BOARDS,1);
        assertTrue(didRemove);
        assertEquals(0,rm.getResourceCount(ResourceType.BOARDS));
        didRemove = rm.removeResource(ResourceType.BOARDS,1);
        assertTrue(!didRemove);
        assertEquals(0,rm.getResourceCount(ResourceType.BOARDS));
    }

    @Test
    public void hasNoResources() {
        ResourceManager rm2 = new ResourceManager();
        assertTrue(rm2.hasNoResources());
    }

    @Test
    public void hasNoResourcesAfterAdding() {
        rm.addResource(ResourceType.GOLD, 5);
        assertFalse(rm.hasNoResources());
    }

    @Test
    public void hasNoResourcesAfterAddingRemoving() {
        rm.addResource(ResourceType.GOLD, 5);
        rm.removeResource(ResourceType.GOLD, 5);
        assertFalse(rm.hasNoResources());
    }

}
