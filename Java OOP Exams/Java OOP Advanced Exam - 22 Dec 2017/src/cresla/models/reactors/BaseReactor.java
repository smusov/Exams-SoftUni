package cresla.models.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

import java.lang.reflect.Field;
import java.util.LinkedList;

public abstract class BaseReactor implements Reactor {

    private int id;
    private Container moduleContainer;


    protected BaseReactor(int id, int moduleCapacity) {
        this.id = id;
        this.moduleContainer = new ModuleContainer(moduleCapacity);
    }


    @Override
    public long getTotalEnergyOutput() {
//        if (this.moduleContainer.getTotalEnergyOutput() > this.moduleContainer.getTotalHeatAbsorbing()) {
//            return 0;
//        }
        return this.moduleContainer.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.moduleContainer.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {

        int moduleCount = 0;

        try {
            Field modulesByInput = this.moduleContainer.getClass().getDeclaredField("modulesByInput");

            modulesByInput.setAccessible(true);
            LinkedList<Module> modules = (LinkedList<Module>) modulesByInput.get(this.moduleContainer);
            moduleCount = modules.size();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return moduleCount;
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

//        {reactorType} – {reactorId}
//        Energy Output: {energyOutput}
//        Heat Absorbing: {heatAbsorbing}
//        Modules: {moduleCount}

        sb.append(String.format("%s - %d", this.getClass().getSimpleName(), getId())).append(System.lineSeparator());
        sb.append("Energy Output: ").append(getTotalEnergyOutput()).append(System.lineSeparator());
        sb.append("Heat Absorbing: ").append(getTotalHeatAbsorbing()).append(System.lineSeparator());
        sb.append("Modules: ").append(getModuleCount());

        return sb.toString();
    }
}
