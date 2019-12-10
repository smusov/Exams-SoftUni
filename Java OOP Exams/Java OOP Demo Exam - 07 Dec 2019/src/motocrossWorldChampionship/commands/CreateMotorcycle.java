package motocrossWorldChampionship.commands;

import motocrossWorldChampionship.commands.interfaces.Command;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;

import java.util.List;

public class CreateMotorcycle implements Command {
    @Override
    public String execute(List<String> args, ChampionshipController controller) {
        return controller.createMotorcycle(args.get(0),args.get(1),Integer.parseInt(args.get(2)));
    }
}
