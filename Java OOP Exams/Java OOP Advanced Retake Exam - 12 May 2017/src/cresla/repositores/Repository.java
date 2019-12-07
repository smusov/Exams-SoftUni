package cresla.repositores;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;
import cresla.interfaces.Reactor;
import cresla.models.modules.AbsorbingModuleImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class Repository {

    private Map<Integer, Reactor> reactors;
    private Map<Integer, Module> modules;

    public Repository() {
        this.reactors = new LinkedHashMap<>();
        this.modules = new LinkedHashMap<>();
    }

    public void addReactor(int reactorId, Reactor reactor) {
        this.reactors.put(reactorId, reactor);
    }

    public void addModule(int reactorId, Module module) {

        if (module instanceof AbsorbingModuleImpl) {
            this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        } else {
            this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
        }

        this.modules.put(module.getId(), module);
    }

    public String getReport(int id) {

        if (this.reactors.containsKey(id)) {
            return this.reactors.get(id).toString();
        } else {
            return this.modules.get(id).toString();
        }
    }

    public String getReport() {
        StringBuilder sb = new StringBuilder();

        sb.append("Cryo Reactors: ").append(getCryoReactorsCount()).append(System.lineSeparator());
        sb.append("Heat Reactors: ").append(getHeatReactorsCount()).append(System.lineSeparator());
        sb.append("Energy Modules: ").append(getEnergyModulesCount()).append(System.lineSeparator());
        sb.append("Absorbing Modules: ").append(getAbsorbingModulesCount()).append(System.lineSeparator());
        sb.append("Total Energy Output: ").append(getTotalEnergyOutput()).append(System.lineSeparator());
        sb.append("Total Heat Absorbing: ").append(getTotalHeatAbsorbing());

        return sb.toString();
    }

    private long getCryoReactorsCount() {
        return this.reactors.values().stream().filter(e -> e.getClass().getSimpleName().contains("Cryo")).count();
    }

    private long getHeatReactorsCount() {
        return this.reactors.values().stream().filter(e -> e.getClass().getSimpleName().contains("Heat")).count();
    }

    private long getEnergyModulesCount() {
        return this.modules.values().stream().filter(e -> e.getClass().getSuperclass().getSimpleName().contains("Energy")).count();
    }

    private long getAbsorbingModulesCount() {
        return this.modules.values().stream().filter(e -> e.getClass().getSuperclass().getSimpleName().contains("Absorbing")).count();
    }

    private long getTotalEnergyOutput() {
        return this.reactors.values().stream().mapToLong(Reactor::getTotalEnergyOutput).sum();
    }

    private long getTotalHeatAbsorbing() {
        return this.reactors.values().stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();
    }
}
