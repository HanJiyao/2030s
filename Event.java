package cs2030.simulator;

import java.util.function.Function;

public class Event {
    private final Customer customer;
    private final Server server;
    private final double eventTime;
    private final Function<Shop, Pair<Shop, Event>> func;

    public Event(Customer customer, Server server, double eventTime, Function<Shop, Pair<Shop, Event>> func) {
        this.customer = customer;
        this.server = server;
        this.eventTime = eventTime;
        this.func = func;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Server getServer() {
        return server;
    }

    public double getEventTime() {
        return eventTime;
    }

    final Pair<Shop, Event> execute(Shop shop) {
        return this.func.apply(shop);
    }
}
