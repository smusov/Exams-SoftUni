import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageEncrypter {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(read.readLine());

        Pattern pattern = Pattern.compile("([*@])(?<name>[A-Z][a-z]+)\\1: (?<first>[\\[A-Za-z\\]]+)\\|(?<second>[\\[A-Za-z\\]]+)\\|(?<third>[\\[A-Za-z\\]]+)\\|$");

        for (int i = 0; i < n; i++) {
            String input = read.readLine();

            Matcher matcher = pattern.matcher(input);

            if (matcher.find()){
                if (matcher.group("name").length()>=3){

                    String first = matcher.group("first").replaceAll("[\\[\\]]","");
                    String second = matcher.group("second").replaceAll("[\\[\\]]","");
                    String third = matcher.group("third").replaceAll("[\\[\\]]","");

                    System.out.println(String.format("%s: %d %d %d",matcher.group("name"),(int)first.charAt(0),(int)second.charAt(0),(int)third.charAt(0)));
                }else {
                    System.out.println("Valid message not found!");
                }

            }else {
                System.out.println("Valid message not found!");
            }
        }
    }
}
