package utilities.Observer.CursorObserver;

import utilities.Observer.ObserverSubject;

public interface CursorObserverSubject extends ObserverSubject {
    void registerCursorObserver(CursorObserver o);
    void removeCursorObserver(CursorObserver o);
}
