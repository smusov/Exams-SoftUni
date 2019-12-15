package aquarium.factories;

import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;

public class DecorationFactory {
    public static Decoration makeDecoration(String type) {
        Decoration decoration = null;

        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
        }
        return decoration;
    }
}
