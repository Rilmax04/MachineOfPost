package by.kurilo.machineofpost;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Path path = Path.of("C:\\Users\\Max\\Desktop\\Новый текстовый документ (2).txt");
        List<String> list = Files.readAllLines(path);

        MachineOfPost machine1=new MachineOfPost();
        Program commands = new Program();

        for (int numberFileLine = 0; numberFileLine <list.size(); numberFileLine++) {

            String fileLine = list.get(numberFileLine);
            if (numberFileLine ==0){
                machine1.splittingHead(fileLine);
            }else
            if (numberFileLine == 1) {

               machine1.splittingTape(fileLine);
            }
            else
            {
                commands.splittingProgramCommand(fileLine);
            }
        }
        machine1.getHeadIndex();
        machine1.getTape();
        System.out.println();
        commands.programRealisation(machine1);
        machine1.getHeadIndex();
        machine1.getTape();

    }

    }

