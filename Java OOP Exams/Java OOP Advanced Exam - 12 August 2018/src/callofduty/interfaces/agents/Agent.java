package callofduty.interfaces.agents;

import callofduty.interfaces.agents.properties.Identifiable;
import callofduty.interfaces.agents.properties.Nameable;
import callofduty.interfaces.agents.properties.Rateable;
import callofduty.interfaces.missions.Mission;

public interface Agent extends Identifiable, Nameable, Rateable {
    void acceptMission(Mission mission);

    void completeMissions();
}
