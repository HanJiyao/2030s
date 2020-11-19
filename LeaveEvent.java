package cs2030.simulator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer, Server server, double eventTime) {
        super(customer, server, eventTime,
            shop -> Pair.of(shop, new LeaveEvent(customer, server, eventTime)));
    }

    @Override
    public String toString() {
        return String.format("%.3f %d leaves",
            this.getEventTime(), this.getCustomer().getIdentifier());
    }
}
