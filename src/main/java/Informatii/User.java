package Informatii;

import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Integer> streams;

    public User(int id, String name, List<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getStreams() {
        return streams;
    }
}
