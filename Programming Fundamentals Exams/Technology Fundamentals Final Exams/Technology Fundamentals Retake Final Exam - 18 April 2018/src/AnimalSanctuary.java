import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalSanctuary {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());

        Pattern pattern = Pattern.compile("^n:(?<name>[^;]+);t:(?<type>[^;]+);c--(?<country>[A-Za-z ]+)$");

        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            String input = read.readLine();

            Matcher matcher = pattern.matcher(input);

            if (matcher.find()){
                String name = encryptText(matcher.group("name"));
                String type = encryptText(matcher.group("type"));
                totalWeight+=findWeight(input);

                System.out.println(String.format("%s is a %s from %s",name,type,matcher.group("country")));
            }

        }

        System.out.println("Total weight of animals: "+totalWeight+"KG");

    }

    private static int findWeight(String input) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);

        int sum = 0;

        while (matcher.find()) {
            sum+=Integer.parseInt(matcher.group());
        }
        return sum;
    }

    private static String encryptText(String text) {
        Pattern letters = Pattern.compile("[A-Za-z ]");
        Matcher matcher = letters.matcher(text);

        StringBuilder name = new StringBuilder();

        while (matcher.find()) {
            name.append(matcher.group());
        }
        return name.toString();
    }
}
