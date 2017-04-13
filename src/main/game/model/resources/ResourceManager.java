package game.model.resources;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        if(resourceTypeIntegerMap.containsKey(type)){
           resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type)+integer);
        }
        else {
            resourceTypeIntegerMap.put(type, integer);
        }
    }

    public void removeResource(ResourceType type, Integer integer){
        if (resourceTypeIntegerMap.get(type) - integer >= 0) {
            resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type) - integer);
        }
        else{
           resourceTypeIntegerMap.remove(type);
        }
    }

    public Integer getResource(ResourceType resourceType) {
        return resourceTypeIntegerMap.get(resourceType);
    }

    public Map<ResourceType, Integer> giveResource(ResourceType resourceType, Integer integer) {
        Map<ResourceType, Integer> resourceMap = new HashMap<>();
        resourceMap.put(resourceType, integer);
        removeResource(resourceType, integer);
        return resourceMap;
    }

    public boolean hasResource() {
        return resourceTypeIntegerMap.size()>0;
    }

    public String getExportString() {
        Iterator it = resourceTypeIntegerMap.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ResourceType resource = (ResourceType) pair.getKey();
            Integer resourceCount = (Integer) pair.getValue();
            sb.append(resource.getName())
                    .append(":")
                    .append(resourceCount)
                    .append(" ");
        }
        return sb.toString();
    }
}
