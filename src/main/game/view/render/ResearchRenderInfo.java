package game.view.render;

public class ResearchRenderInfo
{
    private final boolean hasReasearchedRowing;
    private final boolean hasReasearchedTrucking;
    private final boolean hasReasearchedShipping;
    private final boolean hasReasearchedDrilling;
    private final boolean hasReasearchedSpecialization;
    private final boolean hasReasearchedEnlargement;
    private final boolean hasReasearchedNewShafts;
    private final boolean hasReasearchedBrightIdea;

    public ResearchRenderInfo(boolean hasReasearchedRowing, boolean hasReasearchedTrucking, boolean hasReasearchedShipping, boolean hasReasearchedDrilling, boolean hasReasearchedSpecialization, boolean hasReasearchedEnlargement, boolean hasReasearchedNewShafts, boolean hasReasearchedBrightIdea)
    {
        this.hasReasearchedRowing = hasReasearchedRowing;
        this.hasReasearchedTrucking = hasReasearchedTrucking;
        this.hasReasearchedShipping = hasReasearchedShipping;
        this.hasReasearchedDrilling = hasReasearchedDrilling;
        this.hasReasearchedSpecialization = hasReasearchedSpecialization;
        this.hasReasearchedEnlargement = hasReasearchedEnlargement;
        this.hasReasearchedNewShafts = hasReasearchedNewShafts;
        this.hasReasearchedBrightIdea = hasReasearchedBrightIdea;
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
}
