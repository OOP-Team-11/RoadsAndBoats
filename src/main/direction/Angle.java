package direction;

import java.util.Objects;

public class Angle {

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

    public boolean equals(Angle otherAngle){
        return (this.getDegrees() == otherAngle.getDegrees());
    }

    public int hashCode(){
        return Objects.hash(this.degrees);
    }

    public int getDegrees() {
        return this.degrees;
    }
}
