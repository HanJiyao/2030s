package cs2030.simulator;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {

    private final List<Server> serverList;

    public Shop(int num) {
        this.serverList = IntStream
                .range(1, num + 1)
                .mapToObj(x -> new Server(x, true, false, 0.0))
                .collect(Collectors.toList());
    }

    public Shop(List<Server> serverList) {
        this.serverList = serverList;
    }

    public Optional<Server> find(Predicate<Server> predicate) {
        return this.serverList.stream().filter(predicate).findFirst();
    }

    public Shop replace(Server s) {
        if (s!= null) {
            return new Shop(
                    this.serverList.stream()
                            .map(x -> x.getIdentifier() == s.getIdentifier() ? s : x)
                            .collect(Collectors.toList())
            );
        }
        return this;
    }

    public List<Server> getServerList() {
        return serverList;
    }

    @Override
    public String toString() {
        return "[" + this.serverList.stream().map(Server::toString).collect(Collectors.joining(", ")) + "]";
    }

}
