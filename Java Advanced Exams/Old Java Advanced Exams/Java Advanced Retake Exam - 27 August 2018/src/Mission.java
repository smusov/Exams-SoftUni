import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mission {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> successMissions = new LinkedHashMap<>();
        Map<String, Integer> failedMissions = new LinkedHashMap<>();

        String input;

        while (!"Decrypt".equals(input = read.readLine())) {

            String message = getMassage(input);

            if (message.equals("MISSION")){

                String missionName = getMissionName(input);
                String consideredType = getConsideredType(input);
                int missionRating = getMissionRating(input);

                if (consideredType.toLowerCase().equals("c")){
                    successMissions.put(missionName,missionRating);
                }else if(consideredType.toLowerCase().equals("x")){
                    failedMissions.put(missionName,missionRating);
                }

            }

        }

        successMissions
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(e-> System.out.println(String.format("Completed mission %s with rating: %d",e.getKey(),e.getValue())));

        failedMissions
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .ifPresent(e-> System.out.println(String.format("Failed Mission %s with rating: %d",e.getKey(),e.getValue())));

    }

    private static int getMissionRating(String input) {
        int rating = 0;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))){
                rating+=Character.getNumericValue(input.charAt(i));
            }
        }

        return rating;
    }

    private static String getConsideredType(String input) {

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == 'C' || input.charAt(i) == 'X') {

                return String.valueOf(input.charAt(i));
            }
        }
        return "";
    }

    private static String getMissionName(String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            if (Character.isLowerCase(input.charAt(i))) {

                builder.append(input.charAt(i));
            }
        }
        return builder.toString();
    }

    private static String getMassage(String input) {
        List<Character> missions = getMissionList();

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            if (missions.contains(input.charAt(i))) {

                message.append(input.charAt(i));
            }
        }

        return message.toString();
    }

    private static List<Character> getMissionList() {
        List<Character> missions = new ArrayList<>();

        String mission = "MISSION";

        for (int i = 0; i < mission.length(); i++) {
            missions.add(mission.charAt(i));
        }

        return missions;
    }
}
