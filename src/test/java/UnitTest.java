import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitTest {

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
        object.splitting("part1 part2 part3");

        assertEquals("part1",object.getFirst(0));
        assertEquals("part2",object.getOperator(0));
        assertEquals("part3",object.getSecond(0));


        object.splitting("part3 part4");
        assertEquals("part3",object.getFirst(1));
        assertEquals("part4",object.getOperator(1));
        assertEquals("0",object.getSecond(1));
    }

    @Test
    public void testMultipleCommands() {

        program.splitting("1 0 2");
        program.splitting("2 1 3");
        program.splitting("3 . 0");

        program.getCommand();

        assertEquals(3, program.getCommandElements().size());
    }
    @Test
    public void testProgramRealisationWithDecreaseIndex() {
        program.splitting("1 < 2");
        program.splitting("2 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        assertEquals(0, machine.getIndex());
    }

    @Test
    public void testProgramRealisationWithIncreaseIndex() {

        program.splitting("1 > 2");
        program.splitting("2 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        assertEquals(1, machine.getIndex());
    }

    @Test
    public void testProgramRealisationWithCondition() {

        program.splitting("1 0 2");
        program.splitting("2 ? 3,2");
        program.splitting("3 . 0");

        machine.splittingTape("0 0 0");

        program.programRealisation(machine);

        assertEquals(0, machine.getIndex());
    }

    @Test
    public void testProgramRealisationChargeValue0() {

        program.splitting("1 0 2");
        program.splitting("2 . 0");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        assertEquals("0", machine.condition("0,1")); // Условие выберет 0, начальное значение было 1.
    }

    @Test
    public void testProgramRealisationChargeValue1() {

        program.splitting("1 1 2");
        program.splitting("2 . 0");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        assertEquals("1", machine.condition("0,1")); // Условие выберет 1.
    }

    @Test
    public void testExitConditionWithDot() {

        program.splitting("1 . 2");
        program.splitting("2 0 1");

        machine.splittingTape("1 2 3");

        program.programRealisation(machine);

        assertEquals(0, machine.getIndex());
    }
    
    @Test
    public void testSplittingTape() {
        machine.splittingTape("1 2 3 4 5");
        assertEquals(0, machine.getIndex());
        machine.getTape();
    }

    @Test
    public void testIncreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.increaseIndex();
        assertEquals(1, machine.getIndex());
        machine.increaseIndex();
        assertEquals(2, machine.getIndex());
        machine.increaseIndex();
        assertEquals(3, machine.getIndex());
    }

    @Test
    public void testDecreaseIndex() {
        machine.splittingTape("1 2 3 4");
        machine.decreaseIndex();
        assertEquals(0, machine.getIndex());
        machine.increaseIndex();
        machine.decreaseIndex();
        assertEquals(0, machine.getIndex());
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
        machine.chargeValue(5);
        assertEquals(5,  machine.getTapeValue(machine.getIndex()));
    }

    @Test
    public void testSplittingHead() {
        machine.splittingHead("X | Y Z");
        assertEquals(3, machine.getIndex());
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

        assertEquals(0, machine.getIndex());

        machine.increaseIndex();
        assertEquals(1, machine.getIndex());

        assertEquals(0,  machine.getTapeValue(0));
    }


}
