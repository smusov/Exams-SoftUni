package motocrossWorldChampionship.commands.interfaces;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;

import java.util.List;

public interface Command {
    String execute(List<String> args , ChampionshipController controller);
}
