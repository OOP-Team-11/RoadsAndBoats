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
        if (canProduceCoalWithTrunks(resourceManager)) {
            resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);

            return true;
        }
        else if (canProduceCoalWithBoth(resourceManager)) {
            resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ1);
            resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ1);
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);

            return true;
        }
        else if (canProduceCoalWithBoards(resourceManager)) {
            resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ2);
            resourceManager.addResource(ResourceType.PAPER, PAPER_AMT);

            return true;
        }
        return false;
    }

    private boolean canProduceCoalWithTrunks(ResourceManager resourceManager) {
        if (resourceManager.getResource(ResourceType.TRUNKS) != null) {
            if (resourceManager.getResource(ResourceType.TRUNKS) >= TRUNKS_REQ2) {
                return true;
            }
        }
        return false;
    }

    private boolean canProduceCoalWithBoards(ResourceManager resourceManager) {
        if (resourceManager.getResource(ResourceType.BOARDS) != null) {
            if (resourceManager.getResource(ResourceType.BOARDS) >= BOARDS_REQ2) {
                return true;
            }
        }
        return false;
    }

    private boolean canProduceCoalWithBoth(ResourceManager resourceManager) {
        if ((resourceManager.getResource(ResourceType.TRUNKS) != null) && (resourceManager.getResource(ResourceType.BOARDS) != null)) {
            if ((resourceManager.getResource(ResourceType.TRUNKS) >= TRUNKS_REQ1) && (resourceManager.getResource(ResourceType.BOARDS) >= BOARDS_REQ1)) {
                return true;
            }
        }
        return false;
    }
}
