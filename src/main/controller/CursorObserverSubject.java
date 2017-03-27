package controller;

public interface CursorObserverSubject extends ObserverSubject{
    void registerCursorObserver();
    void removeCursorObserver();
}
