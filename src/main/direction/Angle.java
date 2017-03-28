package direction;

public class Angle {

    private int degrees;

    public Angle(int degrees) throws AngleValueOutOfRangeException {
        if (degrees < 0 || degrees > 360)
            throw new AngleValueOutOfRangeException("Angle value must be between 0 and 360 degrees.");

        this.degrees = degrees;
    }

    public int getDegrees() {
        return this.degrees;
    }

    @Override
    public int hashCode() {
        return this.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Angle) &&
                ((Angle) object).getDegrees() == this.getDegrees();
    }


}
