package callofduty.core;

import callofduty.interfaces.agents.Agent;
import callofduty.interfaces.controllers.MissionControl;
import callofduty.interfaces.managers.MissionManager;
import callofduty.interfaces.missions.Mission;
import callofduty.models.agents.MasterAgent;
import callofduty.models.agents.NoviceAgent;
import callofduty.reflections.FieldReflector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MissionManagerImpl implements MissionManager {

    private Map<String, Agent> agents;
    private MissionControl missionControl;
    private int assignedMissions;

    public MissionManagerImpl() {
        this.agents = new LinkedHashMap<>();
        this.missionControl = new MissionControlImpl();
    }

    @Override
    public String agent(List<String> arguments) {

        String id = arguments.get(0);
        String name = arguments.get(1);

        this.agents.put(id, new NoviceAgent(id, name));

        return String.format("Registered Agent - %s:%s", name, id);
    }

    @Override
    public String request(List<String> arguments) {

        String agentId = arguments.get(0);
        String missionId = arguments.get(1);
        Double missionRating = Double.parseDouble(arguments.get(2));
        Double missionBounty = Double.parseDouble(arguments.get(3));

        Mission mission = this.missionControl.generateMission(missionId, missionRating, missionBounty);

        this.agents.get(agentId).acceptMission(mission);

        this.assignedMissions++;

        String missionName = mission.getClass().getSimpleName().replace("M", " M");

        return String.format("Assigned %s - %s to Agent - %s", missionName, missionId, this.agents.get(agentId).getName());
    }

    @Override
    public String complete(List<String> arguments) {

        Agent agent = this.agents.get(arguments.get(0));
        agent.completeMissions();

        Map<String, Mission> missions = (Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "completedMissions");


        assert missions != null;
        if (missions.size() >= 3 && agent.getClass().getSimpleName().equals("NoviceAgent")) {
            growAgent(agent);
        }

        return String.format("Agent - %s:%s has completed all assigned missions.", agent.getName(), agent.getId());
    }

    @Override
    public String status(List<String> arguments) {

        String output = "";

        if (this.agents.containsKey(arguments.get(0))) {

            output = this.agents.get(arguments.get(0)).toString();

        } else {

            List<Agent> agents = new ArrayList<>(this.agents.values());

            for (Agent agent : agents) {

                Map<String, Mission> missionMap = (Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "missions");
                Map<String, Mission> completed = (Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "completedMissions");

                assert missionMap != null;
                if (missionMap.containsKey(arguments.get(0))) {
                    output = missionMap.get(arguments.get(0)).toString();
                    break;
                } else {
                    assert completed != null;
                    if (completed.containsKey(arguments.get(0))) {
                        output = completed.get(arguments.get(0)).toString();
                        break;
                    }
                }
            }
        }

        return output;
    }

    @Override
    public String over(List<String> arguments) {

        long noviceAgentsCount = this.agents.values().stream().filter(e -> e.getClass().getSimpleName().contains("Novice")).count();
        long masterAgentsCount = this.agents.values().stream().filter(e -> e.getClass().getSimpleName().contains("Master")).count();

        int completedMissionCount = getCompletedMissionCount();
        Double totalRatings = getTotalRatings();
        Double totalBounty = getTotalBounty();

        return String.format("" +
                        "Novice Agents: %d%n" +
                        "Master Agents: %d%n" +
                        "Assigned Missions: %d%n" +
                        "Completed Missions: %d%n" +
                        "Total Rating Given: %.2f%n" +
                        "Total Bounty Given: $%.2f",
                noviceAgentsCount,
                masterAgentsCount,
                this.assignedMissions,
                completedMissionCount,
                totalRatings,
                totalBounty);
    }

    private Double getTotalBounty() {
        Double totalBounty = 0.0;

        for (Agent agent : this.agents.values()) {

            if (agent instanceof MasterAgent) {
                totalBounty += ((MasterAgent) agent).getBounty();
            }

        }

        return totalBounty;
    }

    private Double getTotalRatings() {
        Double rating = 0.0;

        for (Agent agent : this.agents.values()) {
            rating+=agent.getRating();
        }

        return rating;
    }

    private int getCompletedMissionCount(){

        int missionCount = 0;

        for (Agent agent : this.agents.values()) {

            Map<String, Mission> missions = (Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "completedMissions");

            assert missions != null;
            missionCount += missions.size();
        }
        return missionCount;
    }

    private void growAgent(Agent agent) {

        String id = (String) FieldReflector.getAgentFieldValue(agent,"id");
        String name = (String) FieldReflector.getAgentFieldValue(agent,"name");
        Double rating = (Double) FieldReflector.getAgentFieldValue(agent,"rating");
        Map<String, Mission> missions =(Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "missions");
        Map<String, Mission> completedMissions = (Map<String, Mission>) FieldReflector.getAgentFieldValue(agent, "completedMissions");

        Agent masterAgent = new MasterAgent(id, name, rating);

        FieldReflector.setAgentFieldValue(masterAgent,"missions",missions);
        FieldReflector.setAgentFieldValue(masterAgent,"completedMissions",completedMissions);

        this.agents.put(id, masterAgent);
    }
}
