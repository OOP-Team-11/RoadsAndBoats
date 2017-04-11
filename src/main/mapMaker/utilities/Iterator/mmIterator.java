package mapMaker.utilities.Iterator;

public interface mmIterator<T> {
    void next();
    void previous();
    T getCurrent();
}
