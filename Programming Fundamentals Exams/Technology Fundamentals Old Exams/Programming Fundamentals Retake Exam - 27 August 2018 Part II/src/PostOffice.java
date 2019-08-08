import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostOffice {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String[] input = read.readLine().split("\\|");

        String firstPart = input[0];
        Pattern letters = Pattern.compile("([#$%*&])(?<letters>[A-Z]+)\\1");
        Matcher matcher = letters.matcher(firstPart);

        List<Character> findLetters = new ArrayList<>();

        while (matcher.find()) {

            String match = matcher.group("letters");

            for (int i = 0; i < match.length(); i++) {
                findLetters.add(match.charAt(i));
            }
        }

        String secondPart = input[1];

        Pattern charAndLength = Pattern.compile("(?<char>\\d\\d):(?<length>\\d\\d)");
        matcher = charAndLength.matcher(secondPart);

        Map<Character, Integer> letterAndLength = new LinkedHashMap<>();

        while (matcher.find()) {

            char sym = (char) Integer.parseInt(matcher.group("char"));
            int length = Integer.parseInt(matcher.group("length"));

            if (length>=1&&length<=20){
                if (findLetters.contains(sym)) {
                    letterAndLength.put(sym, length + 1);
                }
            }
        }

        String[] threePart = input[2].split(" ");

        for (String word : threePart) {
            if (letterAndLength.containsKey(word.charAt(0))) {
                if (letterAndLength.get(word.charAt(0)) == word.length()) {
                    System.out.println(word);
                }
            }
        }
    }
}
