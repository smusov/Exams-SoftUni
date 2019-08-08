import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PracticeSessions {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> racers = new LinkedHashMap<>();
        
        String input;
        
        while(!"END".equals(input=read.readLine())){
        
            String[] data = input.split("->");

            switch (data[0]){
                case "Add":
                    racers.putIfAbsent(data[1],new ArrayList<>());
                    racers.get(data[1]).add(data[2]);
                    break;
                case "Move":
                    if (racers.get(data[1]).contains(data[2])){
                        racers.get(data[1]).remove(data[2]);
                        racers.get(data[3]).add(data[2]);
                    }
                    break;
                case "Close":
                    racers.remove(data[1]);
                    break;
            }
        }

        System.out.println("Practice sessions:");
        racers.entrySet().stream().sorted((e1,e2)->{
            int compare = Integer.compare(e2.getValue().size(),e1.getValue().size());
            if (compare==0){
                compare=e1.getKey().compareTo(e2.getKey());
            }
            return compare;
        }).forEach(e->{
            System.out.println(e.getKey());
            e.getValue().forEach(b-> System.out.println("++"+b));
        });
    }
}