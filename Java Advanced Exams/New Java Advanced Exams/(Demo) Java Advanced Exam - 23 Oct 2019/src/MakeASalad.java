import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class MakeASalad {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<String> vegetables = new ArrayDeque<>();
        ArrayDeque<Integer> calories = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).forEach(vegetables::offer);
        Arrays.stream(read.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(calories::push);

        while (!vegetables.isEmpty() && !calories.isEmpty()) {

            int calorie = calories.peek();

            while (calorie > 0 && !vegetables.isEmpty()) {

                String vegetable = vegetables.poll();
                int vegetableCalories = getVegetableCalories(vegetable);

                calorie -= vegetableCalories;
            }
            System.out.print(calories.pop() + " ");
        }
        System.out.println();
        if (!vegetables.isEmpty()) {
            vegetables.forEach(e -> System.out.print(e + " "));
        }
        if (!calories.isEmpty()) {
            calories.forEach(e -> System.out.print(e + " "));
        }

    }

    private static int getVegetableCalories(String vegetable) {

        switch (vegetable) {
            case "tomato":
                return 80;
            case "carrot":
                return 136;
            case "lettuce":
                return 109;
            case "potato":
                return 215;
        }
        return 0;
    }
}
