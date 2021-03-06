package game.model.managers;

import game.model.gameImportExport.exporter.Serializable;
import game.model.resources.ResourceType;
import game.view.render.ResourceManagerRenderInfo;
import game.model.visitors.TileCompartmentVisitor;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResourceManager implements Serializable, TileCompartmentVisitor {

    private Map<ResourceType, Integer> resourceTypeIntegerMap;

    public ResourceManager() {
        this.resourceTypeIntegerMap = new LinkedHashMap<>();
    }

    public int getWealthPoints() {
        int points = 0;
        for (ResourceType resourceType : resourceTypeIntegerMap.keySet()) {
            points = points + resourceTypeIntegerMap.get(resourceType) * resourceType.getWealthPoints();

        }
        return points;
    }

    public Map<ResourceType, Integer> getResourceTypeIntegerMap() {
        return resourceTypeIntegerMap;
    }

    public void addResource(ResourceType type, Integer numberToAdd) {
        resourceTypeIntegerMap.putIfAbsent(type, 0); // Initialize the entry to 0 if it doesn't exist
        resourceTypeIntegerMap.replace(type, resourceTypeIntegerMap.get(type) + numberToAdd); //Add the specified number
    }

    public boolean removeResource(ResourceType type, Integer numberToRemove) {
        if (resourceTypeIntegerMap.containsKey(type)) {
            int oldCount = resourceTypeIntegerMap.get(type);
            //If there are even enough of that resource to remove the specified amount,
            if (oldCount - numberToRemove >= 0) {
                resourceTypeIntegerMap.replace(type, oldCount - numberToRemove);
                return true;
            } else return false;
        } else return false;
    }

    public boolean hasResource(ResourceType wellDoesIt) {
        return this.resourceTypeIntegerMap.containsKey(wellDoesIt) && this.resourceTypeIntegerMap.get(wellDoesIt) > 0;
    }

    public int getResourceCount(ResourceType desiredType) {
        return (
                this.resourceTypeIntegerMap.containsKey(desiredType) ?
                        resourceTypeIntegerMap.get(desiredType) :
                        0
        );
    }

    public String getExportString() {
        Iterator it = resourceTypeIntegerMap.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ResourceType resource = (ResourceType) pair.getKey();
            Integer resourceCount = (Integer) pair.getValue();
            sb.append(resource.getExportString())
                    .append(":")
                    .append(resourceCount)
                    .append(" ");
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return (resourceTypeIntegerMap.size() == 0);
    }

    public boolean hasNoResources() {
        Iterator it = resourceTypeIntegerMap.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ResourceType resource = (ResourceType) pair.getKey();
            Integer resourceCount = (Integer) pair.getValue();
            count += resourceCount;
        }
        return (count == 0);
    }

    public ResourceManagerRenderInfo getRenderInfo() {
        return new ResourceManagerRenderInfo(resourceTypeIntegerMap);
    }

    @Override
    public void addResourcesVisit(ResourceType resourceType, Integer amountToAdd) {
        this.addResource(resourceType, amountToAdd);
    }

}
