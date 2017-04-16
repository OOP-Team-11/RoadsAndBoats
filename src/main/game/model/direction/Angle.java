package game.model.direction;

public class Angle implements Cloneable{

    private int degrees;

    public Angle(int degrees){
        this.degrees = toPositiveStandardizedDegrees(degrees);
    }

    private int toPositiveStandardizedDegrees(int rawDegrees) {
        int niceDegrees;
        if (rawDegrees >= 0) {
            niceDegrees = rawDegrees % 360;
        } else {
            niceDegrees = (360 - (Math.abs(rawDegrees) % 360))%360;
        }

        return niceDegrees;
    }
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    public boolean equals(Angle otherMmAngle){
        return (this.getDegrees() == otherMmAngle.getDegrees());
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

    @Override
    public String toString()
    {
        return ""+degrees;
    }
}
