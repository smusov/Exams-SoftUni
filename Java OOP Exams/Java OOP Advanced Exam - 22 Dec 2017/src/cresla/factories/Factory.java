package cresla.factories;

import cresla.identificator.Identificator;

public abstract class Factory {
    private Identificator identificator;

    public Factory() {
        this.identificator = new Identificator();
    }

    public int getNextId(){
        return this.identificator.getId();
    }
}
