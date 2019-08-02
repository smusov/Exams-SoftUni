import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TseamAccount {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        List<String> elements = Arrays.stream(read.readLine().split("\\s+")).collect(Collectors.toList());
        
        String input;
        
        while(!"Play!".equals(input=read.readLine())){
            String[] data = input.split("\\s+");

            switch (data[0]){
                case "Install":
                    if (!elements.contains(data[1])){
                        elements.add(data[1]);
                    }
                    break;
                case "Uninstall":
                    elements.remove(data[1]);
                    break;
                case "Update":
                    int index = elements.indexOf(data[1]);
                    if (index!=-1){
                        elements.remove(index);
                        elements.add(elements.size(),data[1]);
                    }
                    break;
                case "Expansion":
                    String[] game = data[1].split("-");
                    int searchGame = elements.indexOf(game[0]);
                    if (searchGame!=-1){
                        elements.add(searchGame+1,game[0]+":"+game[1]);
                    }
                    break;
            }
        }
        System.out.println(elements.toString().replaceAll("[\\[\\],]",""));
    }
}
