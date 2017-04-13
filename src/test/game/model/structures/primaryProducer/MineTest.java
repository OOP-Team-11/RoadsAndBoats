package model.structures.primaryProducer;

import game.model.resources.Gold;
import game.model.resources.Iron;
import game.model.resources.Resource;
import game.model.structures.primaryProducer.Mine;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MineTest {

    @Test
    public void produceRandom() {
        Mine mine = new Mine(3, 3);
        Map<Resource, Integer> resources = new HashMap<>();
        Map<Resource, Integer> mineOutput;

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mineOutput = mine.produce();

            if (mineOutput.containsKey(new Gold())) {
                if (resources.get(new Gold()) == null) {
                    resources.put(new Gold(), mineOutput.get(new Gold()));
                }
                else {
                    resources.put(new Gold(), mineOutput.get(new Gold()) + resources.get(new Gold()));
                }
            }

            if (mineOutput.containsKey(new Iron())) {
                if (resources.get(new Iron()) == null) {
                    resources.put(new Iron(), mineOutput.get(new Iron()));
                }
                else {
                    resources.put(new Iron(), mineOutput.get(new Iron()) + resources.get(new Iron()));
                }
            }
        }

        assertEquals(mine.getCurrentGoldCount(), 0);
        assertEquals(mine.getCurrentIronCount(), 0);
        assertEquals(resources.get(new Gold()), new Integer(3));
        assertEquals(resources.get(new Iron()), new Integer(3));
    }

    @Test
    public void addShaft() {
        Mine mine = new Mine(5, 5);

        Map<Resource, Integer> resources = new HashMap<>();
        Map<Resource, Integer> mineOutput;

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mineOutput = mine.produce();

            if (mineOutput.containsKey(new Gold())) {
                if (resources.get(new Gold()) == null) {
                    resources.put(new Gold(), mineOutput.get(new Gold()));
                }
                else {
                    resources.put(new Gold(), mineOutput.get(new Gold()) + resources.get(new Gold()));
                }
            }

            if (mineOutput.containsKey(new Iron())) {
                if (resources.get(new Iron()) == null) {
                    resources.put(new Iron(), mineOutput.get(new Iron()));
                }
                else {
                    resources.put(new Iron(), mineOutput.get(new Iron()) + resources.get(new Iron()));
                }
            }
        }

        mine.addShaft();

        assertEquals(mine.getCurrentGoldCount(), 5);
        assertEquals(mine.getCurrentIronCount(), 5);
        assertEquals(resources.get(new Gold()), new Integer(5));
        assertEquals(resources.get(new Gold()), new Integer(5));
    }

}
