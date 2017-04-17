package game.view.render;

import game.model.resources.ResourceType;

import java.util.Map;

public class ResourceManagerRenderInfo {

    private Map<ResourceType, Integer> resourceMap;
    public ResourceManagerRenderInfo(Map<ResourceType, Integer> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public Map<ResourceType, Integer> getResourceMap() {
        return this.resourceMap;
    }
}
