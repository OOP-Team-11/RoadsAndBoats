package game.model.resource;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceTypeTest {
    @Test
    public void addResource(){
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.addResource(ResourceType.GOLD, 2);
        resourceManager.addResource(ResourceType.GOLD, 4);
        assertTrue(resourceManager.hasResource(ResourceType.GOLD));
    }
    @Test
    public void decrementResource(){
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.addResource(ResourceType.GOLD, 2);
        resourceManager.addResource(ResourceType.GOLD, 4);
        assertTrue(resourceManager.hasResource(ResourceType.GOLD));
    }
    @Test
    public void removeResource(){
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.addResource(ResourceType.GOLD, 2);
        resourceManager.addResource(ResourceType.GOLD, 4);
        resourceManager.removeResource(ResourceType.GOLD, 8);
        assertTrue(!resourceManager.hasResource(ResourceType.GOLD));

    }

    @Test
    public void getWealthPoints(){
        ResourceManager resourceManager = new ResourceManager();
        resourceManager.addResource(ResourceType.IRON, 3);
        resourceManager.addResource(ResourceType.GOLD, 2);
        resourceManager.addResource(ResourceType.GOLD, 4);
        assertEquals(resourceManager.getWealthPoints(), 60);

    }
}
