import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClubParty {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int maxCapacity = Integer.parseInt(read.readLine());

        ArrayDeque<String> data = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).forEach(data::push);

        Map<String, List<Integer>> hallAndPeople = new LinkedHashMap<>();

        ArrayDeque<String> halls = new ArrayDeque<>();

        int dataSize = data.size();

        for (int i = 0; i < dataSize; i++) {
            String element = data.pop();

            if (Character.isAlphabetic(element.charAt(0))) {

                hallAndPeople.put(element, new ArrayList<>());
                halls.offer(element);

            } else if (!hallAndPeople.isEmpty()) {

                String hall = halls.poll();

                int count = Integer.parseInt(element);
                int space = hallAndPeople.get(hall).stream().mapToInt(e -> e).sum();

                if (count + space <=maxCapacity) {

                    hallAndPeople.get(hall).add(count);
                    halls.addFirst(hall);

                } else if (count + space == maxCapacity) {

                    hallAndPeople.get(hall).add(count);
                    System.out.println(hall + " -> " + hallAndPeople.get(hall).toString().replaceAll("[\\[\\]]", ""));
                    hallAndPeople.remove(hall);

                } else {

                    System.out.println(hall + " -> " + hallAndPeople.get(hall).toString().replaceAll("[\\[\\]]", ""));
                    hallAndPeople.remove(hall);

                    if (!halls.isEmpty()){
                        hallAndPeople.get(halls.peek()).add(count);
                    }

                }
            }
        }

    }
}
