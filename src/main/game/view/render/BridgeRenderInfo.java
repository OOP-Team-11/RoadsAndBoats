package game.view.render;

import game.model.movement.Bridge;

import java.util.Set;

public class BridgeRenderInfo
{
    public final Set<Bridge> bridges;

    public BridgeRenderInfo(Set<Bridge> bridges)
    {
        this.bridges=bridges;
    }
}
