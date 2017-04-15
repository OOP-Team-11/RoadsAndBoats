package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceProducer;

public class Papermill extends ResourceProducer {

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
    public boolean produce(ResourceManager resourceManager) {
        if (canProducePaperWithTrunks(resourceManager)) {
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoth(resourceManager)) {
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        else if (canProducePaperWithBoards(resourceManager)) {
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);
            return true;
        }
        return false;
    }

    private boolean canProducePaperWithTrunks(ResourceManager resourceManager) {
        return resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
    }

    private boolean canProducePaperWithBoards(ResourceManager resourceManager) {
        return resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ2);
    }

    private boolean canProducePaperWithBoth(ResourceManager resourceManager) {
        return (resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ1));
    }
}
