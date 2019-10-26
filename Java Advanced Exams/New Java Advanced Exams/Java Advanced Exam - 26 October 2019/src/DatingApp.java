import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class DatingApp {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> males = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(males::push);

        ArrayDeque<Integer> females = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(females::offer);

        int matches = 0;

        while (!males.isEmpty() && !females.isEmpty()) {

            int male = males.peek();
            int female = females.peek();

            if (male <= 0 ) {

                males.pop();
                continue;
            }
            if (female<=0){

                females.poll();
                continue;
            }

            if (male % 25 == 0) {

                males.pop();
                males.pop();
                continue;

            }

            if (female % 25 == 0) {

                females.poll();
                females.poll();
                continue;

            }

            if (male == female) {

                males.pop();
                females.poll();
                matches++;

            } else {

                females.poll();
                int pop = males.pop();
                males.push(pop - 2);

            }
        }

        System.out.println(String.format("Matches: %d", matches));

        if (males.size()>0){

            System.out.println("Males left: "+males.toString().replaceAll("[\\[\\]]",""));

        }else {

            System.out.println("Males left: none");

        }
        if (females.size()>0){

            System.out.println(String.format("Females left: %s",females.toString().replaceAll("[\\[\\]]","")));
        }else {

            System.out.println("Females left: none");
        }
    }
}
