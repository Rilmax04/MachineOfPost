import java.util.LinkedList;

public class Program {

    private final LinkedList <Program> commandElements=new LinkedList<>();
    private String first;
    private String operator;
    private String second;

    public Program() {
    }

    private Program(String first, String operator, String second) {
        this.first = first;
        this.operator = operator;
        this.second = second;
    }
    public String getFirst(int index) {
        return commandElements.get(index).first;
    }

    public String getSecond(int index) {
        return commandElements.get(index).second;
    }
    public String getOperator(int index) {
        return commandElements.get(index).operator;
    }
    public LinkedList<Program> getCommandElements() {
        return commandElements;
    }

    public void splitting(String str) {
        String[] parts = str.split(" ");
        Program command;
        if (parts.length >= 3) {
            command = new Program(parts[0], parts[1], parts[2]);
        } else
        {
            command = new Program(parts[0], parts[1], "0");
        }
        commandElements.add(command);

    }

    public void getCommand()
    {
        for(Program command:commandElements) {
            System.out.println("First: " + command.first + ",Operator: " + command.operator + ",Second: " + command.second);
        }
    }

    public void programRealisation(MachineOfPost machine1) {
        int commandNumber = 0;

        while (commandNumber < commandElements.size()) {
            Program command = commandElements.get(commandNumber);

            if (".".equals(command.operator) || "0".equals(command.second)) {
                break;
            }

            switch (command.operator) {
                case ("<"): {
                    machine1.decreaseIndex();
                    commandNumber = Integer.parseInt(command.second)-1;
                    break;
                }
                case (">"): {
                    machine1.increaseIndex();
                    commandNumber = Integer.parseInt(command.second) - 1;
                    break;
                }
                case ("?"): {
                    commandNumber = Integer.parseInt(machine1.condition(command.second)) ;
                    break;
                }
                case ("0"): {
                    machine1.chargeValue(0);
                    commandNumber = Integer.parseInt(command.second) - 1;
                    break;
                }
                case ("1"): {
                    machine1.chargeValue(1);
                    commandNumber = Integer.parseInt(command.second) - 1;
                    break;
                }

            }

        }
    }


}
