package callofduty.models.agents;

public class NoviceAgent extends AgentImpl  {

    private static final Double DEFAULT_RATING = 0.0;

    public NoviceAgent(String id, String name) {
        super(id, name, DEFAULT_RATING);
    }
}
