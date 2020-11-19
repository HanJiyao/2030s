package cs2030.simulator;

import java.util.function.Function;

public class DoneEvent extends Event{
    public DoneEvent(Customer customer, Server server, double eventTime) {
        super(customer, server, eventTime,
            shop -> {
                Server updateServer = new Server(
                    server.getIdentifier(),
                    true,
                    false,
                    server.getNextAvailableTime()
                );
                return Pair.of(
                    shop.replace(updateServer),
                    new DoneEvent(customer, updateServer, eventTime)
                );
            }
        );
    }

    @Override
    public String toString() {
        return String.format("%.3f %d served by %d",
                super.getEventTime(),
                super.getCustomer().getIdentifier(),
                super.getServer().getIdentifier());
    }
}
