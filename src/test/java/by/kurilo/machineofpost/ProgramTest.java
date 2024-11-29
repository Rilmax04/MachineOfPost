package by.kurilo.machineofpost;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramTest {

    private Program program;
    private MachineOfPost machine;

    @Before

    public void setUp() {
        program = new Program();
        machine = new MachineOfPost();
    }

    @Test
    public void splitting() {
        Program object=new Program();
        object.splittingProgramCommand("part1 part2 part3");

        Assert.assertEquals("part1",object.getFirstCommandElement(0));
        Assert.assertEquals("part2",object.getOperator(0));
        Assert.assertEquals("part3",object.getThirdCommandElement(0));


        object.splittingProgramCommand("part3 part4");
        Assert.assertEquals("part3",object.getFirstCommandElement(1));
        Assert.assertEquals("part4",object.getOperator(1));
        Assert.assertEquals("0",object.getThirdCommandElement(1));
    }

    @Test
    public void testMultipleCommands() {

        program.splittingProgramCommand("1 0 2");
        program.splittingProgramCommand("2 1 3");
        program.splittingProgramCommand("3 . 0");

        program.getCommand();

        Assert.assertEquals(3, program.getCommandElements().size());
    }
    @Test
    public void testProgramRealisationWithDecreaseIndex() {
        program.splittingProgramCommand("1 < 2");
        program.splittingProgramCommand("2 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        Assert.assertEquals(0, machine.getCarriageIndex());
    }

    @Test
    public void testProgramRealisationWithIncreaseIndex() {

        program.splittingProgramCommand("1 > 2");
        program.splittingProgramCommand("2 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        Assert.assertEquals(1, machine.getCarriageIndex());
    }

    @Test
    public void testProgramRealisationWithCondition() {

        program.splittingProgramCommand("1 0 2");
        program.splittingProgramCommand("2 ? 3,2");
        program.splittingProgramCommand("3 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        Assert.assertEquals(0, machine.getCarriageIndex());
    }

    @Test
    public void testProgramRealisationChargeValue0() {

        program.splittingProgramCommand("1 0 2");
        program.splittingProgramCommand("2 . 0");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        Assert.assertEquals("0", machine.condition("0,1")); // Условие выберет 0, начальное значение было 1.
    }

    @Test
    public void testProgramRealisationChargeValue1() {

        program.splittingProgramCommand("1 1 2");
        program.splittingProgramCommand("2 . 0");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        Assert.assertEquals("1", machine.condition("0,1")); // Условие выберет 1.
    }

    @Test
    public void testExitConditionWithDot() {

        program.splittingProgramCommand("1 . 2");
        program.splittingProgramCommand("2 0 1");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        Assert.assertEquals(0, machine.getCarriageIndex());
    }
    
    @Test
    public void testSplittingTape() {
        machine.splittingTape("1 2 3 4 5");
        Assert.assertEquals(0, machine.getCarriageIndex());
        machine.getTape();
    }

    @Test
    public void testIncreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.increaseIndex();
        Assert.assertEquals(1, machine.getCarriageIndex());
        machine.increaseIndex();
        Assert.assertEquals(2, machine.getCarriageIndex());
        machine.increaseIndex();
        Assert.assertEquals(3, machine.getCarriageIndex());
    }

    @Test
    public void testDecreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.decreaseIndex();
        Assert.assertEquals(0, machine.getCarriageIndex());
        machine.increaseIndex();
        machine.decreaseIndex();
        Assert.assertEquals(0, machine.getCarriageIndex());
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
        Assert.assertEquals(5,  machine.getTapeValue(machine.getCarriageIndex()));
    }

    @Test
    public void testSplittingHead() {
        machine.splittingHead("X | Y Z");
        Assert.assertEquals(3, machine.getCarriageIndex());
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

        Assert.assertEquals(0, machine.getCarriageIndex());

        machine.increaseIndex();
        Assert.assertEquals(1, machine.getCarriageIndex());

        Assert.assertEquals(0,  machine.getTapeValue(0));
    }


}
