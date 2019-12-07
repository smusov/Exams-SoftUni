package cresla.identificator;

public class Identificator {
    private static int id;

    private void incrementId(){
        id++;
    }
    public int getId (){
        incrementId();
        return id;
    }
}
