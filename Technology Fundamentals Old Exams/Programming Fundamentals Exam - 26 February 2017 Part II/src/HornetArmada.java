import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class HornetArmada {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());

        Map<String, Map<String, Long>> legionsSoliderAndCount = new LinkedHashMap<>();
        Map<String, Long> legionsAndActivity = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String[] input = read.readLine().split(" -> ");
            String[] lastActivityAndLegionName = input[0].split(" = ");
            String[] soldierTypeAndSoldierCount = input[1].split(":");

            long lastActivity = Long.parseLong(lastActivityAndLegionName[0]);
            String legionName = lastActivityAndLegionName[1];
            String soldierType = soldierTypeAndSoldierCount[0];
            long soldierCount = Long.parseLong(soldierTypeAndSoldierCount[1]);
            boolean update = false;

            if (!legionsSoliderAndCount.containsKey(legionName)) {

                legionsSoliderAndCount.put(legionName, new LinkedHashMap<>());
                legionsSoliderAndCount.get(legionName).put(soldierType, soldierCount);
                legionsAndActivity.put(legionName, lastActivity);

            } else if (legionsSoliderAndCount.containsKey(legionName)) {

                if (!legionsSoliderAndCount.get(legionName).containsKey(soldierType)) {
                    legionsSoliderAndCount.get(legionName).put(soldierType, soldierCount);

                } else if (legionsSoliderAndCount.get(legionName).containsKey(soldierType)){
                    long newCount = legionsSoliderAndCount.get(legionName).get(soldierType) + soldierCount;
                    legionsSoliderAndCount.get(legionName).put(soldierType, newCount);

                    if (legionsAndActivity.get(legionName) < lastActivity) {
                        legionsAndActivity.put(legionName, lastActivity);
                        update = true;
                    }
                }
                if (legionsAndActivity.get(legionName) < lastActivity && !update) {
                    legionsAndActivity.put(legionName, lastActivity);
                }
            }
        }

        String[] finalInput = read.readLine().split("\\\\");

        if (finalInput.length > 1) {

            Map<String, Long> legionsAndSoldiers = new LinkedHashMap<>();

            for (Map.Entry<String, Long> entry : legionsAndActivity.entrySet()) {

                if (entry.getValue() < Long.parseLong(finalInput[0])) {

                    String legion = entry.getKey();

                    legionsSoliderAndCount.get(legion)
                            .entrySet()
                            .stream()
                            .filter(e -> e.getKey().equals(finalInput[1]))
                            .forEach(e -> legionsAndSoldiers.put(legion, e.getValue()));
                }
            }

            legionsAndSoldiers.entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .forEach(e -> System.out.println(String.format("%s -> %d", e.getKey(), e.getValue())));
        } else {
            Map<String, Long> legionsAndActivityFinal = new LinkedHashMap<>();

            for (Map.Entry<String, Map<String, Long>> entry : legionsSoliderAndCount.entrySet()) {
                for (Map.Entry<String, Long> KVP : entry.getValue().entrySet()) {
                    if (KVP.getKey().equals(finalInput[0])) {

                        String legion = entry.getKey();
                        long activity = legionsAndActivity.get(legion);

                        legionsAndActivityFinal.put(legion, activity);
                    }
                }
            }

            legionsAndActivityFinal
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .forEach(e -> System.out.println(String.format("%d : %s", e.getValue(), e.getKey())));
        }
    }
}
