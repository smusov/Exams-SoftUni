import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class BattleManager {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> nameAndHealth = new LinkedHashMap<>();
        Map<String, Integer> nameAndEnergy = new LinkedHashMap<>();

        String input;

        while (!"Results".equals(input = read.readLine())) {

            String[] data = input.split(":+");

            switch (data[0]) {
                case "Add":
                    if (!nameAndEnergy.containsKey(data[1])) {
                        if (Integer.parseInt(data[2])>0&&Integer.parseInt(data[3])>0){
                            nameAndHealth.put(data[1], Integer.parseInt(data[2]));
                            nameAndEnergy.put(data[1], Integer.parseInt(data[3]));
                        }
                    } else {

                        int health = nameAndHealth.get(data[1]) + Integer.parseInt(data[2]);
                        nameAndHealth.put(data[1], health);
                    }
                    break;
                case "Attack":
                    if (nameAndEnergy.containsKey(data[1]) &&
                            nameAndEnergy.containsKey(data[2])&&
                            nameAndHealth.containsKey(data[2])&&
                            nameAndHealth.containsKey(data[1])) {

                        int newHealth = nameAndHealth.get(data[2]) - Integer.parseInt(data[3]);
                        int newEnergy = nameAndEnergy.get(data[1]) - 1;

                        if (newHealth<=0){
                            nameAndEnergy.remove(data[2]);
                            nameAndHealth.remove(data[2]);
                            System.out.println(String.format("%s was disqualified!",data[2]));
                        }
                        if (newEnergy<=0){
                            nameAndEnergy.remove(data[1]);
                            nameAndHealth.remove(data[1]);
                            System.out.println(String.format("%s was disqualified!",data[1]));
                        }
                        if (nameAndHealth.containsKey(data[2])){
                            nameAndHealth.put(data[2],newHealth);
                        }
                        if (nameAndEnergy.containsKey(data[1])){
                            nameAndEnergy.put(data[1],newEnergy);
                        }

                    }
                    break;
                case "Delete":

                    if (data[1].equals("All")) {
                        nameAndEnergy.clear();
                        nameAndHealth.clear();
                    } else {
                        nameAndHealth.remove(data[1]);
                        nameAndEnergy.remove(data[1]);
                    }
                    break;
            }
        }
        System.out.println(String.format("People count: %d", nameAndEnergy.size()));
        nameAndHealth.entrySet().stream().sorted((e1, e2) -> {
            int compare = Integer.compare(e2.getValue(), e1.getValue());

            if (compare == 0) {
                compare = e1.getKey().compareTo(e2.getKey());
            }
            return compare;
        })
                .forEach(e -> System.out.println(String.format("%s - %d - %d", e.getKey(), e.getValue(), nameAndEnergy.get(e.getKey()))));
    }
}