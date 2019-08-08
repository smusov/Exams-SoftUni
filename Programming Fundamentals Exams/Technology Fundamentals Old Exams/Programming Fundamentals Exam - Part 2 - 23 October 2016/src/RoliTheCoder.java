import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RoliTheCoder {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Map<Long, Map<String, Set<String>>> events = new LinkedHashMap<>();

        String input;

        while (!"Time for Code".equals(input = read.readLine())) {

            String[] data = input.split(" ");

            long id = Long.parseLong(data[0]);
            String event = data[1];

            if (!events.containsKey(id)) {
                if (event.contains("#")) {
                    events.put(id, new LinkedHashMap<>());
                    events.get(id).put(event, new LinkedHashSet<>());
                    for (int i = 2; i < data.length; i++) {
                        events.get(id).get(event).add(data[i]);
                    }
                }
            } else if (events.containsKey(id)) {

                if (events.get(id).containsKey(event)) {
                    for (int i = 2; i < data.length; i++) {
                        events.get(id).get(event).add(data[i]);
                    }
                }
            }
        }

        Map<String, Set<String>> finalEvents = new LinkedHashMap<>();

        events.forEach((key, value) -> value.forEach(finalEvents::put));

        finalEvents.entrySet().stream().sorted((e1, e2) -> {
            int compare = Integer.compare(e2.getValue().size(), e1.getValue().size());

            if (compare == 0) {
                compare = e1.getKey().compareTo(e2.getKey());
            }
            return compare;
        }).forEach(e -> {
            System.out.println(e.getKey().substring(1) + " - " + e.getValue().size());
            e.getValue().stream().sorted(Comparator.comparing(String::toLowerCase)).forEach(System.out::println);
        });
    }
}
