import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

import cs2030.simulator.Pair;
import cs2030.simulator.Shop;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.Customer;
import cs2030.simulator.EventComparator;

class Main {
    /**
     * Creates an cs2030.simulator.ArriveEvent to simulate the execution of events.
     * Scanner object to take in inputs.
     * @param args String[]
     */
    public static void main(String[] args) {
        int seed = Integer.parseInt(args[0]);
        int numOfServers = Integer.parseInt(args[1]);
        Shop shop = new Shop(numOfServers);
        int numOfCustomer = Integer.parseInt(args[2]);

        List<Event> eventList = new ArrayList<>();
        double arriveTime = 0;
        //Customer c = new Customer(args);
        //eventList.add(new ArriveEvent(c));
        for (int i = 2; i < numOfCustomer + 1; i++) {
            //arriveTime += c.genInterArrivalTime();
            eventList.add(new ArriveEvent(new Customer(i, arriveTime)));
        }

        Queue<Event> eventQueue = new PriorityQueue<>(
            eventList.size(),
            new EventComparator()
        );
        eventQueue.addAll(eventList);

        int numLeave = 0;
        double waitingTime = 0.0;
        while (eventQueue.peek() != null) {
            Event e = eventQueue.poll();
            System.out.println(e);
            if (!(e instanceof LeaveEvent)) {
                if (e instanceof WaitEvent) {
                    waitingTime += (e.getServer().getNextAvailableTime()
                            - e.getCustomer().getArrivalTime());
                }
                Pair<Shop, Event> newPair = e.execute(shop);
                shop = shop.replace(newPair.second().getServer());
                // if status is not done or leave, add executed event
                if (!(e instanceof DoneEvent)) {
                    eventQueue.add(newPair.second());
                }
            } else {
                numLeave++;
            }
        }
        System.out.printf("[%.3f %d %d]%n",
                waitingTime / (numOfCustomer - numLeave), numOfCustomer - numLeave, numLeave);
    }
}