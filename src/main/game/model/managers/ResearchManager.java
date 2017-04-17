package game.model.managers;

import game.model.PlayerId;
import game.model.ResearchType;
import game.model.direction.Location;
import game.model.resources.ResourceType;
import game.model.transport.Transport;

import java.util.HashMap;
import java.util.Map;

public class ResearchManager
{
    private final Location home;
    private final PlayerId id;

    private int researchPoints;

    private Map<ResearchType, Boolean> research;

    public ResearchManager(Location home, PlayerId id)
    {
        this.home=home;
        this.id=id;

        researchPoints=0;

        research = new HashMap<>();

        for(ResearchType rt: ResearchType.values())
        {
            research.put(rt, false);
        }
    }

    public boolean canBuyResearchPoint(Transport transport)
    {
        return transport.getResourceCount(ResourceType.GOOSE) >= 2 && transport.getResourceCount(ResourceType.PAPER) >= 1;
    }

    public boolean buyResearchPoint(Transport transport)
    {
        if(canBuyResearchPoint(transport))
        {
            researchPoints++;
            return true;
        }

        return false;
    }

    public boolean hasResearched(ResearchType researchType)
    {
        return research.get(researchType);
    }

    public boolean canSpendResearchPointOn(ResearchType researchType)
    {
        return researchPoints > 0 && !research.get(researchType);
    }

    public boolean spendResearchPointOn(ResearchType researchType)
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        researchPoints--;
        research.replace(researchType, true);
        return true;
    }
}
