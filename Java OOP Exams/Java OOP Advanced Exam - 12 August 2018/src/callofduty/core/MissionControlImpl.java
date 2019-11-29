package callofduty.core;


import callofduty.interfaces.controllers.MissionControl;
import callofduty.interfaces.missions.Mission;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissionControlImpl implements MissionControl {
    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;

    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;

    private static final Double MISSION_RATING_MAXIMUM_VALUE = 100D;

    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;

    private static final Double MISSION_BOUNTY_MAXIMUM_VALUE = 100000D;

    private Map<String, Class> missionClasses;

    private Iterator<Map.Entry<String, Class>> missionIterator;

    public MissionControlImpl() {
        this.initMissionClasses();
        this.missionIterator = this.missionClasses
                .entrySet()
                .iterator();
    }

    private void initMissionClasses() {
        try {
            this.missionClasses = new LinkedHashMap<>() {{
                put("EscortMission", Class.forName("callofduty.models.missions.EscortMission"));
                put("HuntMission", Class.forName("callofduty.models.missions.HuntMission"));
                put("SurveillanceMission", Class.forName("callofduty.models.missions.SurveillanceMission"));
            }};
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkAndReformMissionId(String missionId) {
        return missionId.length() > MISSION_ID_MAXIMUM_LENGTH
                ? missionId.substring(0, MISSION_ID_MAXIMUM_LENGTH)
                : missionId;
    }

    private Double checkAndReformMissionRating(Double missionRating) {
        return missionRating < MISSION_RATING_MINIMUM_VALUE
                ? MISSION_RATING_MINIMUM_VALUE
                : (missionRating > MISSION_RATING_MAXIMUM_VALUE
                ? MISSION_RATING_MAXIMUM_VALUE
                : missionRating);
    }

    private Double checkAndreformMissionBounty(Double missionBounty) {
        return missionBounty < MISSION_BOUNTY_MINIMUM_VALUE
                ? MISSION_BOUNTY_MINIMUM_VALUE
                : (missionBounty > MISSION_BOUNTY_MAXIMUM_VALUE
                ? MISSION_BOUNTY_MAXIMUM_VALUE
                : missionBounty);
    }

    private void updateMissionType() {
        this.missionIterator = this
                .missionClasses
                .entrySet()
                .iterator();
    }

    private Class currentMission() {
        if (!this.missionIterator.hasNext()) {
            this.updateMissionType();
        }

        return this.missionIterator.next().getValue();
    }

    private Mission instantiateMissionObject(String missionId, Double missionRating, Double missionBounty) {
        Mission missionObject = null;

        try {
            missionObject = (Mission)
                    this.currentMission()
                            .getConstructor(String.class, Double.class, Double.class)
                            .newInstance(missionId, missionRating, missionBounty);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ignored) {
            ignored.printStackTrace();
        }

        return missionObject;
    }

    @Override
    public Mission generateMission(String missionId, Double missionRating, Double missionBounty) {
        missionId = this.checkAndReformMissionId(missionId);
        missionRating = this.checkAndReformMissionRating(missionRating);
        missionBounty = this.checkAndreformMissionBounty(missionBounty);

        return this.instantiateMissionObject(missionId, missionRating, missionBounty);
    }

}