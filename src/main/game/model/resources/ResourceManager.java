package game.model.resources;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<Resource, Integer> resourceTypeIntegerMap;
    public ResourceManager(){
        this.resourceTypeIntegerMap = new HashMap<Resource, Integer>();
    }
    public int getWealthPoints(){
        int points = 0;
        for(Resource resource : resourceTypeIntegerMap.keySet()){
            points = points + resourceTypeIntegerMap.get(resource)*resource.getWealthPoint();

        }
        return points;
    }
    public void addResource(Resource type, Integer integer){
        if(resourceTypeIntegerMap.containsKey(type)){
           resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type)+integer);
        }
        else {
            resourceTypeIntegerMap.put(type, integer);
        }
    }
    public void removeResource(Resource type, Integer integer){
        if (resourceTypeIntegerMap.get(type) - integer >= 0) {
            resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type) - integer);
        }
        else{
           resourceTypeIntegerMap.remove(type);
        }
    }

    public boolean hasResource() {
        return resourceTypeIntegerMap.size()>0;
    }
}
