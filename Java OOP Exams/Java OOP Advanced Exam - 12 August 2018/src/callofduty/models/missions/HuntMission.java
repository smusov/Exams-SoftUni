package callofduty.models.missions;

public class HuntMission extends MissionImpl {

    public HuntMission(String id, Double rating, Double bounty) {
        super(id, increaseRating(rating), increaseBounty(bounty));
    }

    private static Double increaseBounty(Double bounty) {
        return bounty * 2;
    }

    private static Double increaseRating(Double rating) {
        return rating * 1.50;
    }
}
