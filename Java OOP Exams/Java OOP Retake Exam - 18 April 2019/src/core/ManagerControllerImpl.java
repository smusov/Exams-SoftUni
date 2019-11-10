package core;

import common.ConstantMessages;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class ManagerControllerImpl implements ManagerController {

    private static final String PLAYER_PATH = "models.players.";
    private static final String CARD_PATH = "models.cards.";

    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        this.cardRepository = new CardRepositoryImpl();
        this.playerRepository = new PlayerRepositoryImpl();
        this.battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {

        try {
            Class<?> clazz = Class.forName(PLAYER_PATH + type);
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(CardRepository.class, String.class);

            Player player = (Player) declaredConstructor.newInstance(new CardRepositoryImpl(), username);

            this.playerRepository.add(player);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER,type,username);
    }

    @Override
    public String addCard(String type, String name) {

        try {
            Class<?> clazz = Class.forName(CARD_PATH + type+"Card");
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class);
            Card card = (Card) declaredConstructor.newInstance(name);

            this.cardRepository.add(card);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CARD,type,name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {

        this.playerRepository.find(username).getCardRepository().add(this.cardRepository.find(cardName));

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS,cardName,username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {

        Player attacker = this.playerRepository.find(attackUser);
        Player enemy = this.playerRepository.find(enemyUser);

        this.battlefield.fight(attacker,enemy);

        return String.format(ConstantMessages.FIGHT_INFO,attacker.getHealth(),enemy.getHealth());
    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        List<Player> players = this.playerRepository.getPlayers();

        for (Player player : players) {

            CardRepository cardRepository = player.getCardRepository();

            sb.append(String.format(ConstantMessages.PLAYER_REPORT_INFO,player.getUsername(),player.getHealth(),cardRepository.getCount()));
            sb.append(System.lineSeparator());

            cardRepository.getCards().forEach(e->sb.append(String.format(ConstantMessages.CARD_REPORT_INFO,e.getName(),e.getDamagePoints())).append(System.lineSeparator()));

            sb.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
