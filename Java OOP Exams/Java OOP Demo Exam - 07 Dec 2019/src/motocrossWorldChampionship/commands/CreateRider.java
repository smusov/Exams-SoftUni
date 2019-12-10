package motocrossWorldChampionship.commands;

import motocrossWorldChampionship.commands.interfaces.Command;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;

import java.util.List;

public class CreateRider implements Command {

    @Override
    public String execute(List<String> args , ChampionshipController controller) {
        return controller.createRider(args.get(0));
    }
}
