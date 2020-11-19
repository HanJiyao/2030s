package cs2030.simulator;

public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /**
     * Creates an cs2030.simulator.Server class.
     * @param identifier id
     * @param isAvailable availability
     * @param hasWaitingCustomer anybody before
     * @param nextAvailableTime next serve time
     */
    public Server(int identifier, boolean isAvailable,
           boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean hasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    @Override
    public String toString() {
        String output;
        if (this.isAvailable) {
            output = String.format("%d is available", identifier);
        } else {
            if (this.hasWaitingCustomer) {
                output = String.format(
                    "%d is busy; waiting customer to be served at %.3f",
                    identifier, nextAvailableTime
                );
            } else {
                output = String.format("%d is busy; available at %.3f",
                    identifier, nextAvailableTime);
            }
        }
        return output;
    }
}