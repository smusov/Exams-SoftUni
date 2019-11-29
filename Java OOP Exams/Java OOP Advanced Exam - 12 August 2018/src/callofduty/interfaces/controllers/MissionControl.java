package callofduty.interfaces.controllers;

import callofduty.interfaces.missions.Mission;

public interface MissionControl {
    Mission generateMission(String missionId, Double missionRating, Double missionBounty);
}
