package cs2030.simulator;

public class Customer {
    private final int identifier;
    private final double arrivalTime;

    /**
     * Creates an cs2030.simulator.Customer class.
     * @param identifier id
     * @param arrivalTime initial time
     */
    public Customer(int identifier, double arrivalTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("%d arrives at %.1f", identifier, arrivalTime);
    }
}
