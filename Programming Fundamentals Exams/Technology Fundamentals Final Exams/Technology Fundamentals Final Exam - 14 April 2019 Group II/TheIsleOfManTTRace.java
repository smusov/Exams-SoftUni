import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheIsleOfManTTRace {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        boolean isFound = false;

        Pattern pattern = Pattern.compile("^([#$%*&])(?<name>[A-Za-z]+)\\1=(?<geohashLength>[\\d]+)!!(?<geohash>.+)$");

        while(!isFound){

            String input = read.readLine();

            Matcher matcher = pattern.matcher(input);

            if (matcher.find()){
                int geohashLegth = Integer.parseInt(matcher.group("geohashLength"));
                String geohash = matcher.group("geohash");
                if (geohashLegth==geohash.length()){
                    System.out.println(String.format("Coordinates found! %s -> %s",matcher.group("name"),encryptGeohash(geohash,geohashLegth)));
                    isFound=true;
                }else {
                    System.out.println("Nothing found!");
                }
            }else {
                System.out.println("Nothing found!");
            }
        }
    }

    private static String encryptGeohash(String geohash, int geohashLegth) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < geohash.length(); i++) {
            int newChar = geohash.charAt(i)+geohashLegth;
            result.append((char)newChar);
        }
        return result.toString();
    }
}
