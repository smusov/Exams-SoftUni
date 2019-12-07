package cresla.models.modules;

import cresla.interfaces.EnergyModule;

public abstract class EnergyModuleImpl extends ModuleImpl implements EnergyModule {
    private int energyOutput;

    protected EnergyModuleImpl(int id , int energyOutput) {
        super(id);
        this.energyOutput = energyOutput;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }

    @Override
    public String toString() {
        return super.toString()+ System.lineSeparator()+String.format("Energy Output: %d",this.energyOutput);
    }
}
