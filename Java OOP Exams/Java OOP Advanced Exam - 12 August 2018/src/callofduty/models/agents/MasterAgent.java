package callofduty.models.agents;

import callofduty.interfaces.agents.bountyAgent.BountyAgent;

public class MasterAgent extends AgentImpl implements BountyAgent {

    private static final Double DEFAULT_BOUNTY = 0.0;

    private Double bounty;

    public MasterAgent(String id, String name, Double rating) {
        super(id, name, rating);
        this.bounty = DEFAULT_BOUNTY;
    }

    @Override
    public void completeMissions() {
        this.bounty += super.getMissionBounty();
        super.completeMissions();
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + String.format("Bounty Earned: $%.2f", getBounty());
    }
}
