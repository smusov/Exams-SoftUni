package aquarium.models.decorations;

import java.util.Objects;

public abstract class BaseDecoration implements Decoration {

    private int comfort;
    private double price;

    protected BaseDecoration(int comfort, double price) {
        this.comfort = comfort;
        this.price = price;
    }

    @Override
    public int getComfort() {
        return this.comfort;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDecoration that = (BaseDecoration) o;
        return comfort == that.comfort &&
                Double.compare(that.price, price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comfort, price);
    }
}
