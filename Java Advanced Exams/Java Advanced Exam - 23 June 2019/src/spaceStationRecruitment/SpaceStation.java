package spaceStationRecruitment;

import java.util.*;

public class SpaceStation {

    private Map<String,Astronaut> data;
    private String name;
    private int capacity;

    public SpaceStation(String name, int capacity) {

        this.data = new LinkedHashMap<>();
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCount (){
        return this.data.size();
    }
    public void add (Astronaut astronaut){
        if (getCount()<getCapacity()){
            this.data.put(astronaut.getName(),astronaut);
        }
    }
    public boolean remove (String name){
        if (this.data.containsKey(name)){
            this.data.remove(name);
            return true;
        }
        return false;
    }
    public Astronaut getOldestAstronaut(){
        return this.data.values().stream().max(Comparator.comparingInt(Astronaut::getAge)).orElse(null);
    }
    public Astronaut getAstronaut(String name){
       return this.data.get(name);
    }
    public String report(){
        StringBuilder builder = new StringBuilder(String.format("Astronauts working at Space Station %s:%n",this.name));

        this.data.values().forEach(e->builder.append(e.toString()).append(System.lineSeparator()));

        return builder.toString().trim();
    }
}
