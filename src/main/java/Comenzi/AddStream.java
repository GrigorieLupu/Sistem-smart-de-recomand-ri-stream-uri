package Comenzi;

import Informatii.Stream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddStream implements Command {
    private final int streamerId;
    private final int streamId;
    private final int streamType;
    private final int streamGenre;
    private final long length;
    private final String name;

    public AddStream(String[] parts) {
        this.streamerId = Integer.parseInt(parts[0]);
        this.streamType = Integer.parseInt(parts[2]);
        this.streamId = Integer.parseInt(parts[3]);
        this.streamGenre = Integer.parseInt(parts[4]);
        this.length = Long.parseLong(parts[5]);
        this.name = Utils.joinStrings(6, parts.length, parts);
    }

    @Override
    public void execute() {
        Database database = Database.getInstance();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        database.add(new Stream.Builder()
                .id(streamId)
                .streamType(streamType)
                .streamGenre(streamGenre)
                .noOfStreams(0)
                .streamerId(streamerId)
                .length(length)
                .dateAdded(2132142152151L)
                .name(name)
                .build());
    }

}
