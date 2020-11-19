package cs2030.simulator;

public class WaitEvent extends Event {
    public WaitEvent(Customer customer, Server server, double eventTime) {
        super(customer, server, eventTime,
            shop -> {
                Server updateServer = new Server(
                    server.getIdentifier(),
                    false,
                    true,
                    server.getNextAvailableTime() + 1
                );
                return Pair.of(
                    shop.replace(updateServer),
                    new ServeEvent(customer, updateServer, eventTime)
                );
            }
        );
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits to be served by %d",
            super.getEventTime(), super.getCustomer().getIdentifier(),
            super.getServer().getIdentifier());
    }
}
