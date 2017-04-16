package game.model.structures;

import game.model.ability.Ability;
import game.model.gameImportExport.exporter.Serializable;

import java.util.Vector;

public abstract class Structure implements Serializable {

    private Vector<Ability> abilities;
    private StructureId id;

    protected Structure() {
        abilities = new Vector<Ability>();
        id = new StructureId();
    }

    public StructureId getId()
    {
        return id;
    }

    // use for view drawing and import/export to file ONLY
    public abstract StructureType getType();

    public abstract String getExportString();
}
