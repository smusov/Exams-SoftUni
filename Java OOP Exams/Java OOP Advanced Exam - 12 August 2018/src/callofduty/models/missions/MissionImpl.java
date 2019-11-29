package callofduty.models.missions;

import callofduty.interfaces.missions.Mission;

public abstract class MissionImpl implements Mission {
    private String id;
    private Double rating;
    private Double bounty;
    private boolean status;

    protected MissionImpl(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
        this.status = true;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return String.format(
                "%s Mission - %s%n" +
                "Status: %s%n" +
                "Rating: %.2f%n" +
                "Bounty: %.2f",
                this.getClass().getSimpleName().replace("Mission", ""),
                this.id,
                this.status ? "Open" : "Completed",
                this.rating,
                this.bounty);
    }
}
