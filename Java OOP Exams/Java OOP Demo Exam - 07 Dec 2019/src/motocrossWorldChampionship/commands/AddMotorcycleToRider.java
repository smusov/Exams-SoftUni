package motocrossWorldChampionship.commands;

import motocrossWorldChampionship.commands.interfaces.Command;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;

import java.util.List;

public class AddMotorcycleToRider implements Command {

    @Override
    public String execute(List<String> args, ChampionshipController controller) {
        return controller.addMotorcycleToRider(args.get(0),args.get(1));
    }
}
