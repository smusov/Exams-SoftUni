package callofduty.models.agents;

import callofduty.interfaces.agents.Agent;
import callofduty.interfaces.agents.bountyAgent.Bountyable;
import callofduty.interfaces.agents.properties.Rateable;
import callofduty.interfaces.missions.Mission;
import callofduty.reflections.FieldReflector;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AgentImpl implements Agent {

    private String id;
    private String name;
    private Double rating;
    private Map<String,Mission> missions;
    private Map<String,Mission> completedMissions;

    protected AgentImpl(String id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.missions = new LinkedHashMap<>();
        this.completedMissions = new LinkedHashMap<>();
    }

    @Override
    public void acceptMission(Mission mission) {
        this.missions.put(mission.getId(),mission);

    }

    @Override
    public void completeMissions() {

        this.rating += this.missions.values().stream().mapToDouble(Rateable::getRating).sum();

        setCompletedMissionsStatus();

        this.missions.clear();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {

        String agentType = this.getClass().getSimpleName().replace("A", " A");

        return String.format("" +
                        "%s - %s%n" +
                        "Personal Code: %s%n" +
                        "Assigned Missions: %d%n" +
                        "Completed Missions: %d%n" +
                        "Rating: %.2f"
                , agentType
                , this.name
                , this.id
                , this.missions.size()
                , this.completedMissions.size()
                , this.rating);
    }

    protected Double getMissionBounty() {
        return this.missions.values().stream().mapToDouble(Bountyable::getBounty).sum();
    }

    private void setCompletedMissionsStatus() {

        for (Mission mission : this.missions.values()) {

            FieldReflector.setMissionFieldValue(mission,"status",false);
            this.completedMissions.put(mission.getId(),mission);

        }
    }
}
