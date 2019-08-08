import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FeedTheAnimals {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> animalsAndFood = new LinkedHashMap<>();
        Map<String, List<String>> animalsAndArea = new LinkedHashMap<>();

        String input;

        while (!"Last Info".equals(input = read.readLine())) {

            String[] data = input.split(":");

            switch (data[0]) {
                case "Add":

                    animalsAndFood.putIfAbsent(data[1], 0);
                    animalsAndFood.put(data[1], animalsAndFood.get(data[1]) + Integer.parseInt(data[2]));

                    animalsAndArea.putIfAbsent(data[3], new ArrayList<>());

                    if (!animalsAndArea.get(data[3]).contains(data[1])) {
                        animalsAndArea.get(data[3]).add(data[1]);
                    }

                    break;
                case "Feed":

                    if ((animalsAndFood.containsKey(data[1]))) {
                        animalsAndFood.put(data[1], animalsAndFood.get(data[1]) - Integer.parseInt(data[2]));

                        if (animalsAndFood.get(data[1]) <= 0) {

                            animalsAndFood.remove(data[1]);
                            animalsAndArea.get(data[3]).remove(data[1]);
                            System.out.println(String.format("%s was successfully fed", data[1]));

                        }
                    }
                    break;
            }
        }

        System.out.println("Animals:");
        animalsAndFood.entrySet().stream().sorted((e1, e2) -> {
            int compare = Integer.compare(e2.getValue(), e1.getValue());

            if (compare == 0) {
                compare = e1.getKey().compareTo(e2.getKey());
            }
            return compare;

        }).forEach(e -> System.out.println(String.format("%s -> %dg", e.getKey(), e.getValue())));

        System.out.println("Areas with hungry animals:");

        animalsAndArea.entrySet().stream()
                .filter(e -> e.getValue().size() > 0)
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(e -> System.out.println(String.format("%s : %d", e.getKey(), e.getValue().size())));
    }
}
