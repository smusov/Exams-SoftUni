package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.GunFactory;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private Map<String, Player> players;
    private ArrayDeque<Gun> guns;
    private Player mainPlayer;
    private GangNeighbourhood gangNeighbourhood;

    public ControllerImpl() {

        this.players = new LinkedHashMap<>();
        this.mainPlayer = new MainPlayer();
        this.guns = new ArrayDeque<>();
        this.gangNeighbourhood = new GangNeighbourhood();

    }

    @Override
    public String addPlayer(String name) {
        this.players.put(name, new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {

        if (type.equals("Pistol") || type.equals("Rifle")) {

            this.guns.offer(GunFactory.makeGun(name,type));

            return String.format(GUN_ADDED, name, type);
        }
        return GUN_TYPE_INVALID;
    }

    @Override
    public String addGunToPlayer(String name) {

        if (!this.guns.isEmpty()) {

            Gun gun = this.guns.poll();

            if (name.equals("Vercetti")) {

                this.mainPlayer.getGunRepository().add(gun);
                return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");

            } else if (this.players.containsKey(name)) {

                this.players.get(name).getGunRepository().add(gun);
                return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);

            } else {

                return CIVIL_PLAYER_DOES_NOT_EXIST;
            }
        }

        return GUN_QUEUE_IS_EMPTY;
    }

    @Override
    public String fight() {
        this.gangNeighbourhood.action(this.mainPlayer, this.players.values());

        if (mainPlayer.getLifePoints() == 100 && getCountPlayersBelowDefaultLifePoints() == 0) {
            return FIGHT_HOT_HAPPENED;
        }

        String message =

                FIGHT_HAPPENED + System.lineSeparator() +
                String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()) + System.lineSeparator() +
                String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, getKilledPlayerCount()) + System.lineSeparator() +
                String.format(CIVIL_PLAYERS_LEFT_MESSAGE, getLivePlayerCount()) + System.lineSeparator();

        return message.trim();
    }

    private long getLivePlayerCount() {
        return this.players.values().stream().filter(e -> e.getLifePoints() > 0).count();
    }

    private long getKilledPlayerCount() {
        return this.players.values().stream().filter(e -> e.getLifePoints() == 0).count();
    }

    private int getCountPlayersBelowDefaultLifePoints() {
        return (int) this.players.values().stream().filter(e -> e.getLifePoints() < 50).count();
    }
}
