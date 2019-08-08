import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HornetAssault {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        List<Long> bees = Arrays.stream(read.readLine().split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
        List<Long> hornets = Arrays.stream(read.readLine().split("\\s+")).map(Long::parseLong).collect(Collectors.toList());

        long power = hornets.stream().mapToLong(e -> e).sum();

        for (int i = 0; i < bees.size(); i++) {
            bees.set(i,bees.get(i)-power);

            if (bees.get(i)>=0){
                hornets.remove(0);
                power = hornets.stream().mapToLong(e -> e).sum();
            }
            if (bees.isEmpty()||hornets.isEmpty()){
                break;
            }
        }
        
        long beesSize = bees.stream().filter(e->e>0).count();
        
        if (beesSize>0) {
            bees.stream().filter(e->e>0).forEach(e-> System.out.print(e+" "));
        }else {
            hornets.stream().filter(e->e>0).forEach(e-> System.out.print(e+" "));
        }

    }
}
