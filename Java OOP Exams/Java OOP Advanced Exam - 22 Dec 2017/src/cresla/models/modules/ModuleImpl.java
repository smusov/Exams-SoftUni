package cresla.models.modules;

import cresla.interfaces.Module;

public abstract class ModuleImpl implements Module {

    private int id;

    protected ModuleImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s Module – %d",this.getClass().getSimpleName(),this.id);
    }
}
