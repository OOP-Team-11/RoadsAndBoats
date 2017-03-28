package direction;

public class Angle {

    private int degrees;

    public Angle(int degrees) throws AngleValueOutOfRangeException {
        this.degrees = toPositiveStandardizedDegrees(degrees);
    }

    private int toPositiveStandardizedDegrees(int rawDegrees) {
        int niceDegrees;
        if (rawDegrees >= 0) {
            niceDegrees = rawDegrees % 360;
        } else {
            niceDegrees = 360 - (Math.abs(rawDegrees) % 360);
        }

        return niceDegrees;
    }

    public int getDegrees() {
        return this.degrees;
    }
}
