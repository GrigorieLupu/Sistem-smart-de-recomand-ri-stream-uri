package Comenzi;

import Informatii.Streamer;
import Informatii.User;

/**
 * Aici folosesc in principiu Strategy pattern
 */
public class ListStreams implements Command {
    private final int id;

    public ListStreams(String[] parts) {
        this.id = Integer.parseInt(parts[0]);
    }

    @Override
    public void execute() {
        ListAlghorithm listAlghorithm;
        User user = Database.getInstance().getUser(id);
        Streamer streamer = Database.getInstance().getStreamer(id);

        // aici verific propriu-zis pentru cine trebuie sa afisez streamurile
        if (user != null) {
            listAlghorithm = new ListUser();
        } else if (streamer != null) {
            listAlghorithm = new ListStreamer();
        } else {
            System.out.println("User or streamer not found");
            return;
        }

        listAlghorithm.list(id);
    }
}
