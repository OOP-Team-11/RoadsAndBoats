package game.model.structures;

import game.utilities.observer.StructureRenderInfoObserver;

public interface StructureObservable  {

    void attach(StructureRenderInfoObserver structureRenderInfoObserver);
    void detach(StructureRenderInfoObserver structureRenderInfoObserver);

}
