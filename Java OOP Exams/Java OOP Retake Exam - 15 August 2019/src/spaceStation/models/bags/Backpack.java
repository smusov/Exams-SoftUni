package spaceStation.models.bags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static spaceStation.common.ConstantMessages.*;

public class Backpack implements Bag {

    private List<String> items;

    public Backpack() {
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<String> getItems() {
        return this.items;
    }

    @Override
    public String toString() {
        return String.format(REPORT_ASTRONAUT_BAG_ITEMS, items.size() == 0 ? "none" : String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, items));
    }
}
