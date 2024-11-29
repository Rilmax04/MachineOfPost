package by.kurilo.machineofpost;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MachineOfPostTest {
    private MachineOfPost machine;
    @Before
    public void setUp() {
        machine = new MachineOfPost();
    }
    @Test
    public void testSplittingTape() {
        machine.splittingTape("1 2 3 4 5");
        assertEquals(0, machine.getCarriageIndex());
        machine.getTape();
    }

    @Test
    public void testIncreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.increaseIndex();
        assertEquals(1, machine.getCarriageIndex());
        machine.increaseIndex();
        assertEquals(2, machine.getCarriageIndex());
        machine.increaseIndex();
        assertEquals(3, machine.getCarriageIndex());
    }

    @Test
    public void testDecreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.decreaseIndex();
        assertEquals(0, machine.getCarriageIndex());
        machine.increaseIndex();
        machine.decreaseIndex();
        assertEquals(0, machine.getCarriageIndex());
    }

    @Test
    public void testConditionWhenValueIsZero() {
        machine.splittingTape("0 1");
        String result = machine.condition("Zero,One");
        assertEquals("Zero", result);
    }

    @Test
    public void testConditionWhenValueIsOne() {
        machine.splittingTape("1 0");
        machine.increaseIndex();
        String result = machine.condition("Zero,One");
        assertEquals("Zero", result);
    }

    @Test
    public void testChargeValue() {
        machine.splittingTape("0 0");
        machine.chargeCarriageValue(5);
        assertEquals(5,  machine.getTapeValue(machine.getCarriageIndex()));
    }

    @Test
    public void testSplittingHead() {
        machine.splittingHead("X | Y Z");
        assertEquals(3, machine.getCarriageIndex());
    }

    @Test
    public void testGetHeadIndex() {
        machine.splittingTape("1 2 3");
        machine.getHeadIndex();
        machine.increaseIndex();
        machine.getHeadIndex();
    }
    @Test
    public void testIncreaseIndexWithEmptyTape() {

        assertEquals(0, machine.getCarriageIndex());

        machine.increaseIndex();
        assertEquals(1, machine.getCarriageIndex());

        assertEquals(0,  machine.getTapeValue(0));
    }

}