package callofduty.models.missions;

public class EscortMission extends MissionImpl {

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, decreaseRating(rating), increaseBounty(bounty));
    }

    private static Double increaseBounty(Double bounty) {
        return bounty * 1.25;
    }

    private static Double decreaseRating(Double rating) {
        return rating * 0.75;
    }
}
