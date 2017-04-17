package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class Papermill extends SecondaryProducer {

    private static final int TRUNKS_REQ2 = 2;   // 2 Trunks input requirement
    private static final int BOARDS_REQ2 = 2;   // 2 Boards input requirement
    private static final int TRUNKS_REQ1 = 1;   // 1 Trunks + 1 Boards input requirement
    private static final int BOARDS_REQ1 = 1;

    private static final int PAPER_AMT = 1;     // 1 Paper output amount

    public Papermill() {

    }

    // 1 Paper <= 2 Trunks
    // 1 Paper <= 2 Boards
    // 1 Paper <= 1 Trunks + 1 Boards
    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProducePaperWithTrunks(tileCompartment)) {
            tileCompartment.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoards(tileCompartment)) {
            tileCompartment.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoth(tileCompartment)) {
            tileCompartment.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProducePaperWithTrunks(transport)) {
            transport.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoards(transport)) {
            transport.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoth(transport)) {
            transport.storeResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        return false;
    }

    private boolean canProducePaperWithTrunks(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
    }

    private boolean canProducePaperWithBoards(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.BOARDS, BOARDS_REQ2);
    }

    private boolean canProducePaperWithBoth(TileCompartment tileCompartment) {
        return tileCompartment.takeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && tileCompartment.takeResource(ResourceType.BOARDS, BOARDS_REQ1);
    }

    private boolean canProducePaperWithTrunks(Transport transport) {
        return transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
    }

    private boolean canProducePaperWithBoards(Transport transport) {
        return transport.takeResource(ResourceType.BOARDS, BOARDS_REQ2);
    }

    private boolean canProducePaperWithBoth(Transport transport) {
        return transport.takeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && transport.takeResource(ResourceType.BOARDS, BOARDS_REQ1);
    }

    @Override
    public StructureType getType() {
        return StructureType.PAPERMILL;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
