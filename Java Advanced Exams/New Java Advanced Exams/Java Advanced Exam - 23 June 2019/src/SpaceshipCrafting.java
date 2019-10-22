import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SpaceshipCrafting {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> chemicalLiquids = Arrays.stream(read.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> physicalItems = Arrays.stream(read.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        ArrayDeque<Integer> chemical = new ArrayDeque<>();
        ArrayDeque<Integer> physical = new ArrayDeque<>();

        for (Integer num : chemicalLiquids) {
            chemical.offer(num);
        }

        for (Integer num : physicalItems) {
            physical.push(num);
        }

        Map<String,Integer> materials = new LinkedHashMap<>();

        materials.put("Glass",0);
        materials.put("Aluminium",0);
        materials.put("Lithium",0);
        materials.put("Carbon fiber",0);

        while (!chemical.isEmpty()&&!physical.isEmpty()){
            int chemic = chemical.poll();
            int psysic = physical.pop();

            int sum = chemic+psysic;

            switch (sum){
                case 25:
                    materials.put("Glass",materials.get("Glass")+1);
                    break;
                case 50:
                    materials.put("Aluminium",materials.get("Aluminium")+1);
                    break;
                case 75:
                    materials.put("Lithium",materials.get("Lithium")+1);
                    break;
                case 100:
                    materials.put("Carbon fiber",materials.get("Carbon fiber")+1);
                    break;
                default:
                    physical.push(psysic+3);
                    break;
            }
        }

        if (materials.get("Glass")>=1&&materials.get("Aluminium")>=1&&materials.get("Lithium")>=1&&materials.get("Carbon fiber")>=1){
            System.out.println("Wohoo! You succeeded in building the spaceship!");
            if (chemical.isEmpty()){
                System.out.println("Liquids left: none");
            }else {
                System.out.print("Liquids left: ");
                printChemicals(chemical);
            }
            if (physical.isEmpty()){
                System.out.println("Physical items left: none");
            }else {
                System.out.print("Physical items left: ");
                printPhysical(physical);
            }

            materials.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(e-> System.out.println(e.getKey() + ": " + e.getValue()));

        }else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to build the spaceship.");
            if (chemical.isEmpty()){
                System.out.println("Liquids left: none");
            }else {
                System.out.print("Liquids left: ");
                printChemicals(chemical);
            }
            if (physical.isEmpty()){
                System.out.println("Physical items left: none");
            }else {
                System.out.print("Physical items left: ");
                printPhysical(physical);
            }

            materials.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(e-> System.out.println(e.getKey() + ": " + e.getValue()));
        }

    }

    private static void printPhysical(ArrayDeque<Integer> physical) {
        while (!physical.isEmpty()){
            if (physical.size()==1){
                System.out.print(physical.pop());
            }else {
                System.out.print(physical.pop()+", ");
            }
        }
        System.out.println();
    }

    private static void printChemicals (ArrayDeque<Integer> chemical){
        while (!chemical.isEmpty()){
            if (chemical.size()==1){
                System.out.print(chemical.poll());
            }else {
                System.out.print(chemical.poll()+", ");
            }
        }
        System.out.println();
    }
}
