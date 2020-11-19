package cs2030.simulator;

import java.util.Comparator;

public class ServerComparator implements Comparator<Server> {
    @Override
    public int compare(Server s1, Server s2) {
        if (!s1.isAvailable() && s2.isAvailable()) {
            return 1;
        } else if (s1.isAvailable() && !s2.isAvailable()) {
            return -1;
        }
        if (s1.hasWaitingCustomer() && !s2.hasWaitingCustomer()) {
            return 1;
        } else if (!s1.hasWaitingCustomer() && s2.hasWaitingCustomer()) {
            return -1;
        }
        return Integer.compare(s1.getIdentifier(), s2.getIdentifier());
    }
}