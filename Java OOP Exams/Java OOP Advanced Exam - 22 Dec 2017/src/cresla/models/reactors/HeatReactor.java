package cresla.models.reactors;

public class HeatReactor extends BaseReactor {

    private long heatReductionIndex;

    public HeatReactor(int id, int moduleCapacity, long heatReductionIndex) {
        super(id,moduleCapacity);
        this.heatReductionIndex = heatReductionIndex;
    }


    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + heatReductionIndex;
    }
}
