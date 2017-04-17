package game.model.managers;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.resources.ResourceType;
import game.model.transport.Transport;

public class ResearchManager
{
    private final Location home;
    private final PlayerId id;

    private int researchPoints;

    private boolean hasReasearchedRowing;
    private boolean hasReasearchedTrucking;
    private boolean hasReasearchedShipping;
    private boolean hasReasearchedDrilling;
    private boolean hasReasearchedSpecialization;
    private boolean hasReasearchedEnlargement;
    private boolean hasReasearchedNewShafts;
    private boolean hasReasearchedBrightIdea;

    public ResearchManager(Location home, PlayerId id)
    {
        this.home=home;
        this.id=id;

        researchPoints=0;
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

    public int getResearchPoints()
    {
        return researchPoints;
    }

    public boolean hasReasearchedRowing()
    {
        return hasReasearchedRowing;
    }

    public boolean hasReasearchedTrucking()
    {
        return hasReasearchedTrucking;
    }

    public boolean hasReasearchedShipping()
    {
        return hasReasearchedShipping;
    }

    public boolean hasReasearchedDrilling()
    {
        return hasReasearchedDrilling;
    }

    public boolean hasReasearchedSpecialization()
    {
        return hasReasearchedSpecialization;
    }

    public boolean hasReasearchedEnlargement()
    {
        return hasReasearchedEnlargement;
    }

    public boolean hasReasearchedNewShafts()
    {
        return hasReasearchedNewShafts;
    }

    public boolean hasReasearchedBrightIdea()
    {
        return hasReasearchedBrightIdea;
    }

    public boolean spendResearchPointOnRowing()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedRowing = true;
        return true;
    }

    public boolean spendResearchPointOnTrucking()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedTrucking = true;
        return true;
    }

    public boolean spendResearchPointOnShipping()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedShipping = true;
        return true;
    }

    public boolean spendResearchPointOnDrilling()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedDrilling = true;
        return true;
    }

    public boolean spendResearchPointOnSpecialization()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedSpecialization = true;
        return true;
    }

    public boolean spendResearchPointOnEnlargement()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedEnlargement = true;
        return true;
    }

    public boolean spendResearchPointOnNewShafts()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedNewShafts = true;
        return true;
    }

    public boolean spendResearchPointOnBrightIdea()
    {
        if(researchPoints <= 0)
        {
            return false;
        }

        this.hasReasearchedBrightIdea = true;
        return true;
    }
}
