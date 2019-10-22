import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.stream.Collectors;

public class Agency {
    public static void main(String[] args) throws IOException {

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Deque<String> idNumbers = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).forEach(idNumbers::push);

        Deque<String> agents = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).forEach(agents::offer);

        String input;

        while (!"stop".equals(input = read.readLine())) {

            String[] data = input.split("\\s+");

            switch (data[0]) {
                case "add-ID":
                    idNumbers.push(data[1]);
                    break;
                case "add-agent":
                    agents.offer(data[1]);
                    break;
                case "remove-ID":
                    System.out.println(String.format("%s has been removed.", idNumbers.pop()));
                    break;
                case "remove-agent":
                    System.out.println(String.format("%s has left the queue.", agents.removeLast()));
                    break;
                case "sort-ID":
                    idNumbers = idNumbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayDeque::new));
                    break;
            }

        }
        if (agents.size() > idNumbers.size()) {

            print(agents, idNumbers);

            while (!agents.isEmpty()) {
                System.out.println(String.format("%s does not have an ID.", agents.poll()));
            }

        } else if (agents.size() < idNumbers.size()) {

            print(agents, idNumbers);

            System.out.println("No more agents left.");

            while (!idNumbers.isEmpty()) {
                System.out.println(String.format("ID number: %s", idNumbers.pop()));
            }

        } else {

            print(agents, idNumbers);

        }
    }

    private static void print(Deque<String> agents, Deque<String> idNumbers) {
        while (!agents.isEmpty()&&!idNumbers.isEmpty()) {
            System.out.println(String.format("%s takes ID number: %s", agents.poll(), idNumbers.pop()));
        }
    }
}
