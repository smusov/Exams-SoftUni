import core.ManagerControllerImpl;
import core.interfaces.ManagerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        ManagerController controller = new ManagerControllerImpl();

        String input;


        while (!"Exit".equals(input = read.readLine())) {

            String[] data = input.split("\\s+");

            String output = null;

            try {
                switch (data[0]) {
                    case "AddPlayer":
                        output = controller.addPlayer(data[1], data[2]);
                        break;
                    case "AddCard":
                        output = controller.addCard(data[1], data[2]);
                        break;
                    case "AddPlayerCard":
                        output = controller.addPlayerCard(data[1], data[2]);
                        break;
                    case "Fight":
                        output = controller.fight(data[1], data[2]);
                        break;
                    case "Report":
                        output = controller.report();
                        break;
                }
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
