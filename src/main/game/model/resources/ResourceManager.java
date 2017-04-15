package game.model.resources;


import game.model.gameImporter.Serializable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    public void addResource(ResourceType type, Integer numberToRemove){
        resourceTypeIntegerMap.putIfAbsent(type,0); // Initialize the entry to 0 if it doesn't exist
        resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type)+numberToRemove); //Add the specified number
    }

    public boolean removeResource(ResourceType type, Integer numberToRemove){
        int oldCount = resourceTypeIntegerMap.get(type);
        //If there are even enough of that resource to remove the specified amount,
        if(oldCount - numberToRemove >= 0){
            resourceTypeIntegerMap.replace(type, oldCount - numberToRemove);
            return true;
        } else return false;


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
