package game.model.structures.transportProducer;

import game.model.structures.StructureType;

public class RowboatFactory extends TransportProducer {

    RowboatFactory() {

    }

    @Override
    public StructureType getType() {
        return StructureType.ROWBOAT_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
