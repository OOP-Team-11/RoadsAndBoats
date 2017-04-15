package game.model.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceManager {
    private Map<ResourceType, Integer> resourceTypeIntegerMap;

    public ResourceManager(){
        this.resourceTypeIntegerMap = new HashMap<>();
    }

    public int getWealthPoints(){
        int points = 0;
        for(ResourceType resourceType : resourceTypeIntegerMap.keySet()){
            points = points + resourceTypeIntegerMap.get(resourceType)* resourceType.getWealthPoints();

        }
        return points;
    }

    public void addResource(ResourceType type, Integer integer){
        resourceTypeIntegerMap.putIfAbsent(type,0); // Initialize the entry to 0 if it doesn't exist
        resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type)+integer); //Add the specified number
    }

    public boolean removeResource(ResourceType type, Integer integer){
        int oldCount = resourceTypeIntegerMap.get(type);
        resourceTypeIntegerMap.replace(type, (
                (resourceTypeIntegerMap.get(type) - integer >= 0)   ?
                        resourceTypeIntegerMap.get(type) - integer  :
                        resourceTypeIntegerMap.get(type)
        ));

        int newCount = resourceTypeIntegerMap.get(type);

        return (1 == oldCount-newCount);

    }

    public boolean hasResource(ResourceType wellDoesIt) {
        return this.resourceTypeIntegerMap.containsKey(wellDoesIt) && this.resourceTypeIntegerMap.get(wellDoesIt) > 0;
    }

    public int getResourceCount(ResourceType desiredType){
        return (
                this.resourceTypeIntegerMap.containsKey(desiredType)?
                resourceTypeIntegerMap.get(desiredType) :
                0
        );
    }

    //  wtf even is this
//    public Map<ResourceType, Integer> giveResource(ResourceType resourceType, Integer integer) {
//        Map<ResourceType, Integer> resourceMap = new HashMap<>();
//        resourceMap.put(resourceType, integer);
//        removeResource(resourceType, integer);
//        return resourceMap;
//    }
}
