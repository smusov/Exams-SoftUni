package callofduty.reflections;

import callofduty.interfaces.agents.Agent;
import callofduty.interfaces.missions.Mission;

import java.lang.reflect.Field;

public class FieldReflector {

    private FieldReflector() {
    }

    public static Object getAgentFieldValue(Agent agent , String fieldName){
        try {

            Field field = agent.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(agent);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setAgentFieldValue (Agent agent , String fieldName , Object value){

        try {

            Field field = agent.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(agent,value);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setMissionFieldValue(Mission mission,String fieldName,Object value){
        try {

            Field status = mission.getClass().getSuperclass().getDeclaredField(fieldName);
            status.setAccessible(true);
            status.set(mission,value);

        } catch (NoSuchFieldException | IllegalAccessException e) {

            e.printStackTrace();

        }
    }
}
