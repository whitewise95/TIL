package domain;

public class Passenger {
    private int destination;
    private String goal;
    private String name;

    public Passenger() {
    }

    public Passenger(int destination, String goal, String name) {
        this.destination = destination;
        this.goal = goal;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Passenger setName(String name) {
        this.name = name;
        return this;
    }

    public int getDestination() {
        return destination;
    }

    public Passenger setDestination(int destination) {
        this.destination = destination;
        return this;
    }

    public String getGoal() {
        return goal;
    }

    public Passenger setGoal(String goal) {
        this.goal = goal;
        return this;
    }
}
