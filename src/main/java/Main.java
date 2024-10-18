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

        for (int j=0;j<list.size();j++) {

            String str = list.get(j);
            if (j==0){
                machine1.splittingHead(str);
            }else
            if (j == 1) {

               machine1.splittingTape(str);
            }
            else
            {
                commands.splitting(str);
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

