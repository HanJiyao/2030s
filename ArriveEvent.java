package cs2030.simulator;

import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class ArriveEvent extends Event{
    ArriveEvent(Customer customer) {
        super(customer, null, customer.getArrivalTime()
        , shop -> {
            Queue<Server> serverQueue =
                new PriorityQueue<>(shop.getServerList().size(), new ServerComparator());
            serverQueue.addAll(shop.getServerList());
            Shop updateShop = new Shop(new ArrayList<>(serverQueue));
            if (updateShop.find(Server::isAvailable).isPresent()) {
                Server s = updateShop.find(Server::isAvailable).get();
                Server updateServer = new Server(
                        s.getIdentifier(),
                        false,
                        s.hasWaitingCustomer(),
                        customer.getArrivalTime() + 1
                );
                return Pair.of(
                    shop,
                    new ServeEvent(customer, updateServer, customer.getArrivalTime()));
            } else if (updateShop.find(x -> !x.hasWaitingCustomer()).isPresent()) {
                Server s = updateShop.find(x -> !x.hasWaitingCustomer()).orElse(null);
                if (s != null) {
                    Server updateServer = new Server(
                            s.getIdentifier(),
                            s.isAvailable(),
                            true,
                            s.getNextAvailableTime()
                    );
                    return Pair.of(
                        shop,
                        new WaitEvent(customer, updateServer, customer.getArrivalTime()));
                }
            };
            return Pair.of(shop, new LeaveEvent(customer, null, customer.getArrivalTime()));
        });
    }

    @Override
    public String toString() {
        return String.format("%.3f %d arrives",
                super.getCustomer().getArrivalTime(), super.getCustomer().getIdentifier());
    }
}
