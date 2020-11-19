package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getEventTime() == e2.getEventTime()) {
            if (e1 instanceof ArriveEvent && !(e2 instanceof ArriveEvent)) {
                return 1;
            } else if (!(e1 instanceof ArriveEvent) && e2 instanceof ArriveEvent) {
                return -1;
            }
            if (!(e1 instanceof DoneEvent) && e2 instanceof DoneEvent) {
                return 1;
            } else if (e1 instanceof DoneEvent && !(e2 instanceof DoneEvent)) {
                return -1;
            }
        }
        return Double.compare(e1.getEventTime(), e2.getEventTime());
    }
}