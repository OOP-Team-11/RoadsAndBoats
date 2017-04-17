package game.utilities.observable;

import game.utilities.observer.ResearchRenderInfoObserver;

public interface ResearchRenderInfoObservable
{
    void attach(ResearchRenderInfoObserver observer);
    void detach(ResearchRenderInfoObserver observer);
}
