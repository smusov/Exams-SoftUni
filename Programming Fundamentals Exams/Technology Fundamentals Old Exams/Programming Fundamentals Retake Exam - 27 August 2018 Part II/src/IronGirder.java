import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IronGirder {
    public static class Town{
        private String name ;
        private int time;
        private int passengers;

        public Town(String name, int time, int passengers) {
            this.name = name;
            this.time = time;
            this.passengers = passengers;
        }
        public String getName() {
            return name;
        }
        public int getPassengers() {
            return passengers;
        }
        public int getTime() {
            return time;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setTime(int time) {
            this.time = time;
        }
        public void setPassengers(int passengers) {
            this.passengers = passengers;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        List<Town> towns = new ArrayList<>();

        String input;

        while(!"Slide rule".equals(input=read.readLine())){

            String[] firstSplit = input.split("->");
            String[] secondSplit = firstSplit[0].split(":");

            String name = secondSplit[0];
            int passengers = Integer.parseInt(firstSplit[1]);
            String timeOrAmbush = secondSplit[1];

            if (!timeOrAmbush.equals("ambush")){

                int time = Integer.parseInt(timeOrAmbush);
                int index = checkContains(towns,name);

                if (index==-1){

                    towns.add(new Town(name,time,passengers));

                }else {

                    int oldTime = towns.get(index).getTime();
                    
                    if (time<oldTime){
                        towns.get(index).setTime(time);
                    }else if (oldTime==0){
                        towns.get(index).setTime(time);
                    }
                    
                    int newPassengers = towns.get(index).getPassengers()+passengers;
                    towns.get(index).setPassengers(newPassengers);
                }

            }else {
                int index = checkContains(towns,name);
                if (index!=-1){
                    int newPassengers = towns.get(index).getPassengers()-passengers;
                    towns.get(index).setTime(0);
                    towns.get(index).setPassengers(newPassengers);
                }
            }
        }

        towns.stream().filter(e->e.getTime()>0&&e.getPassengers()>0)
                .sorted(Comparator.comparingInt(Town::getTime).thenComparing(Town::getName))
                .forEach(e-> System.out.println(String.format("%s -> Time: %d -> Passengers: %d",e.getName(),e.getTime(),e.getPassengers())));
    }

    private static int checkContains(List<Town> towns,String townName) {
        for (int i = 0; i < towns.size(); i++) {
            if (townName.equals(towns.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
}
