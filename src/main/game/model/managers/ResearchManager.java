package game.model.managers;

import game.model.PlayerId;
import game.model.ResearchType;
import game.model.direction.Location;
import game.model.resources.ResourceType;
import game.model.transport.Transport;
import game.utilities.observable.ResearchRenderInfoObservable;
import game.utilities.observer.ResearchRenderInfoObserver;
import game.view.render.ResearchRenderInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ResearchManager implements ResearchRenderInfoObservable
{
    private final Location home;
    private final PlayerId id;

    private int researchPoints;

    private Map<ResearchType, Boolean> research;
    private Collection<ResearchRenderInfoObserver> researchRenderInfoObservers;

    public ResearchManager(Location home, PlayerId id)
    {
        this.home=home;
        this.id=id;

        researchPoints=0;

        research = new HashMap<>();

        researchRenderInfoObservers = new ArrayList<>();

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

    public boolean performResearch(ResearchType researchType)
    {
        research.replace(researchType, true);
        return true;
    }

    @Override
    public void attach(ResearchRenderInfoObserver observer)
    {
        this.researchRenderInfoObservers.add(observer);
        notifyResearchRenderInfoObservers();
    }

    private void notifyResearchRenderInfoObservers()
    {
        for(ResearchRenderInfoObserver observer: researchRenderInfoObservers)
        {
            observer.updateResearchInfo(getRenderInfo());
        }
    }

    private ResearchRenderInfo getRenderInfo()
    {
        return new ResearchRenderInfo(research.get(ResearchType.ROWING),
                research.get(ResearchType.TRUCKING),
                research.get(ResearchType.SHIPPING),
                research.get(ResearchType.DRILLING),
                research.get(ResearchType.SPECIALIZATION),
                research.get(ResearchType.ENLARGEMENT),
                research.get(ResearchType.NEW_SHAFTS),
                research.get(ResearchType.BRIGHT_IDEA));
    }

    @Override
    public void detach(ResearchRenderInfoObserver observer)
    {
        this.researchRenderInfoObservers.remove(observer);
    }
}
