package game.model.resources;


import game.model.gameImporter.Serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResourceManager implements Serializable {
    private Map<ResourceType, Integer> resourceTypeIntegerMap;

    public ResourceManager(){
        this.resourceTypeIntegerMap = new LinkedHashMap<>();
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

    public boolean removeResource(ResourceType type, Integer amount){
        if (resourceTypeIntegerMap.containsKey(type) && resourceTypeIntegerMap.get(type) >= amount) {
            resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type) - amount);
            return true;
        } else{
           return false;
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
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ResourceType resource = (ResourceType) pair.getKey();
            Integer resourceCount = (Integer) pair.getValue();
            sb.append(resource.getExportString())
                    .append(":")
                    .append(resourceCount)
                    .append(" ");
        }
        return sb.toString();
    }
}
