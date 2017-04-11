package mapMaker.direction;

public class mmAngle implements Cloneable{

    private int degrees;

    public mmAngle(int degrees){
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

    public boolean equals(mmAngle otherMmAngle){
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
        return (object instanceof mmAngle) &&
                ((mmAngle) object).getDegrees() == this.getDegrees();
    }


}
