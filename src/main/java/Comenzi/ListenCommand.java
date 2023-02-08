package Comenzi;

import Informatii.Stream;
import Informatii.User;

public class ListenCommand implements Command{
    private final int userId;
    private final int streamId;

    public ListenCommand(String[] parts) {
        this.userId = Integer.parseInt(parts[0]);
        this.streamId = Integer.parseInt(parts[2]);
    }


    @Override
    public void execute() {
        Database database = Database.getInstance();

        User user = database.getUser(userId);
        Stream stream = database.getStream(streamId);

        user.getStreams().add(stream.getId());
        stream.setNoOfStreams(stream.getNoOfStreams() + 1);
    }
}
