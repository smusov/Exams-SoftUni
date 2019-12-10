package motocrossWorldChampionship.commands;

import motocrossWorldChampionship.commands.interfaces.Command;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;

import java.util.List;

public class CreateRace implements Command {
    @Override
    public String execute(List<String> args, ChampionshipController controller) {
        return controller.createRace(args.get(0),Integer.parseInt(args.get(1)));
    }
}
