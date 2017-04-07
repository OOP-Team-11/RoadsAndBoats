package mapMaker.utilities.Iterator;

public interface Iterator<T> {
    void next();
    void previous();
    T getCurrent();
}
