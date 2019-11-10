package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {

    private static final int BEGINNER_INCREASE_HEALTH = 40;
    private static final int BEGINNER_INCREASE_POINTS = 30;

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {

        playersIsNotDead(attackPlayer);
        playersIsNotDead(enemyPlayer);

        increaseHealth(attackPlayer);
        increaseHealth(enemyPlayer);

        increasePoints(attackPlayer);
        increasePoints(enemyPlayer);

        increaseBonus(attackPlayer);
        increaseBonus(enemyPlayer);

        startBattle(attackPlayer, enemyPlayer);

    }

    private void startBattle(Player attackPlayer, Player enemyPlayer) {

        int attackPlayerDamage = getDamagePoints(attackPlayer);
        int enemyPlayerDamage = getDamagePoints(enemyPlayer);

        while (true) {

            enemyPlayer.takeDamage(attackPlayerDamage);

            if (enemyPlayer.isDead()) {
                return;
            }

            attackPlayer.takeDamage(enemyPlayerDamage);

            if (attackPlayer.isDead()){
                return;
            }
        }
    }

    private int getDamagePoints(Player player) {
        return player.getCardRepository().getCards().stream().mapToInt(Card::getDamagePoints).sum();
    }

    private void increaseBonus(Player player) {
        int sum = player.getCardRepository().getCards().stream().mapToInt(Card::getHealthPoints).sum();
        player.setHealth(player.getHealth() + sum);

    }

    private void increasePoints(Player player) {
        if (isBeginnerPlayer(player)) {
            player.getCardRepository().getCards().forEach(e -> e.setDamagePoints(e.getDamagePoints() + BEGINNER_INCREASE_POINTS));
        }
    }

    private void increaseHealth(Player player) {
        if (isBeginnerPlayer(player)) {
            player.setHealth(player.getHealth() + BEGINNER_INCREASE_HEALTH);
        }
    }

    private void playersIsNotDead(Player player) {
        if (player.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }
    }

    private boolean isBeginnerPlayer(Player player) {
        return player.getClass().getSimpleName().equals("Beginner");
    }
}
