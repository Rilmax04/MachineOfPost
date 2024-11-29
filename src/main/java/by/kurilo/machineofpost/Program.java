package by.kurilo.machineofpost;

import java.util.LinkedList;

public class Program {

    private final LinkedList <Program> commandElements=new LinkedList<>();
    private String firstCommandElement;
    private String operator;
    private String thirdCommandElement;

    public Program() {
    }

    private Program(String firstCommandElement, String operator, String thirdCommandElement) {
        this.firstCommandElement = firstCommandElement;
        this.operator = operator;
        this.thirdCommandElement = thirdCommandElement;
    }
    public String getFirstCommandElement(int index) {
        return commandElements.get(index).firstCommandElement;
    }

    public String getThirdCommandElement(int index) {
        return commandElements.get(index).thirdCommandElement;
    }
    public String getOperator(int index) {
        return commandElements.get(index).operator;
    }
    public LinkedList<Program> getCommandElements() {
        return commandElements;
    }

    public void splittingProgramCommand(String programCommand) {
        String[] partsProgramCommand = programCommand.split(" ");
        Program command;
        if (partsProgramCommand.length >= 3) {
            command = new Program(partsProgramCommand[0], partsProgramCommand[1], partsProgramCommand[2]);
        } else
        {
            command = new Program(partsProgramCommand[0], partsProgramCommand[1], "0");
        }
        commandElements.add(command);

    }

    public void getCommand()
    {
        for(Program command:commandElements) {
            System.out.println("First: " + command.firstCommandElement + ",Operator: " + command.operator + ",Second: " + command.thirdCommandElement);
        }
    }

    public void programRealisation(MachineOfPost machine1) {
        int commandNumber = 0;
        while (commandNumber < commandElements.size()) {
            Program command = commandElements.get(commandNumber);
            if (".".equals(command.operator) || "0".equals(command.thirdCommandElement))
                break;
            switch (command.operator) {
                case ("<"): {
                    machine1.decreaseIndex();
                    commandNumber = Integer.parseInt(command.thirdCommandElement)-1;
                    break;}
                case (">"): {
                    machine1.increaseIndex();
                    commandNumber = Integer.parseInt(command.thirdCommandElement) - 1;
                    break;}
                case ("?"): {
                    commandNumber = Integer.parseInt(machine1.condition(command.thirdCommandElement)) ;
                    break;}
                case ("0"): {
                    machine1.chargeCarriageValue(0);
                    commandNumber = Integer.parseInt(command.thirdCommandElement) - 1;
                    break;}
                case ("1"): {
                    machine1.chargeCarriageValue(1);
                    commandNumber = Integer.parseInt(command.thirdCommandElement) - 1;
                    break;
                }
            }

        }
    }


}
