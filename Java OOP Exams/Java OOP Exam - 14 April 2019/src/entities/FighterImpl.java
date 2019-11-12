package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private static final double DEFAULT_HEALTH_POINTS = 200.0;
    private static final double ATTACK_POINTS_MODIFIER = 50.0;
    private static final double DEFENSE_POINTS_MODIFIER = 25.0;
    private boolean aggressiveMode;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, DEFAULT_HEALTH_POINTS);
        this.setAggressiveModeTrue();
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        if (this.getAggressiveMode()) {
            this.setAggressiveModeFalse();
        } else {
            this.setAggressiveModeTrue();
        }
    }

    private void setAggressiveModeTrue() {
        this.aggressiveMode = true;
        super.setAttackPoints(super.getAttackPoints() + ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() - DEFENSE_POINTS_MODIFIER);
    }

    private void setAggressiveModeFalse() {
        this.aggressiveMode = false;
        super.setAttackPoints(super.getAttackPoints() - ATTACK_POINTS_MODIFIER);
        super.setDefensePoints(super.getDefensePoints() + DEFENSE_POINTS_MODIFIER);
    }

    @Override
    public String toString() {
        String mode = this.getAggressiveMode() ? "ON" : "OFF";
        return super.toString() + String.format(" *Aggressive Mode(%s)", mode);
    }
}
