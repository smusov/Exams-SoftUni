import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NetherRealms {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.stream(read.readLine().split("[, ]")).filter(e->!e.equals("")).collect(Collectors.toList());

        List<String> demons = new ArrayList<>();

        for (String element : input) {
            int heart = findHeart(element);
            double damage = findDamage(element);

            demons.add(String.format("%s - %d health, %.2f damage",element,heart,damage));

        }

        Collections.sort(demons);

        demons.forEach(System.out::println);
    }

    private static double findDamage (String element) {
        double damage = 0;

        Pattern pattern = Pattern.compile("([\\d.]+)|(-[\\d.]+)");
        Matcher matcher = pattern.matcher(element);

        while (matcher.find()){
            damage+=Double.parseDouble(matcher.group());
        }

        Pattern pattern1 = Pattern.compile("([*/])");
        matcher = pattern1.matcher(element);

        while (matcher.find()){
            if (matcher.group().equals("*")){
                damage*=2;
            }else if (matcher.group().equals("/")&&damage!=0){
                damage/=2;
            }
        }

        return damage;
    }

    private static int findHeart(String element) {

        int heart = 0;

        Pattern pattern = Pattern.compile("([^\\d+\\-*/.])");
        Matcher matcher = pattern.matcher(element);

        while (matcher.find()){
            heart+=matcher.group().charAt(0);
        }

        return heart;
    }
}
