package game.model;

public enum ResearchType
{
    ROWING("Rowing"),
    TRUCKING("Trucking"),
    SHIPPING("Shipping"),
    DRILLING("Drilling"),
    SPECIALIZATION("Mine Specialization"),
    ENLARGEMENT("Mine Enlargement"),
    NEW_SHAFTS("Mine Shaft Additions"),
    BRIGHT_IDEA("Customizable Research For Expansions");

    private final String name;

    ResearchType(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
}
