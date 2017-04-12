package resource;


import game.model.resources.Gold;
import game.model.resources.Iron;
import game.model.resources.ResourceManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResourceTest {
    @Test
    public void addResource(){
        ResourceManager resourceManager = new ResourceManager();
        Gold gold = new Gold();
        resourceManager.addResource(gold, 2);
        resourceManager.addResource(gold, 4);
    }
    @Test
    public void decrementResource(){
        ResourceManager resourceManager = new ResourceManager();
        Gold gold = new Gold();
        resourceManager.addResource(gold, 2);
        resourceManager.addResource(gold, 4);
        assertTrue(resourceManager.hasResource());
    }
    @Test
    public void removeResource(){
        ResourceManager resourceManager = new ResourceManager();
        Gold gold = new Gold();
        resourceManager.addResource(gold, 2);
        resourceManager.addResource(gold, 4);
        resourceManager.removeResource(gold, 8);
        assertTrue(!resourceManager.hasResource());
    }
    @Test
    public void getWealthPoints(){
        ResourceManager resourceManager = new ResourceManager();
        Gold gold = new Gold();
        Iron iron = new Iron();
        resourceManager.addResource(iron, 3);
        resourceManager.addResource(gold, 2);
        resourceManager.addResource(gold, 4);
        assertEquals(resourceManager.getWealthPoints(), 60);

    }
}
