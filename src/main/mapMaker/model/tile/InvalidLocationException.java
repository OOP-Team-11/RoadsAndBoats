package mapMaker.model.tile;

public class InvalidLocationException extends Exception {

    public InvalidLocationException() {
        super();
    }

    public InvalidLocationException(String message) {
        super(message);
    }
}
