package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clasa BasicRecommendStreams implementeaza interfata Command si implementeaza algorithmul de recomandare
 * pentru cele 5 streamuri dupa preferinte din streamerii ascultati de user
 */
public class BasicRecommendStreams implements Command {
    private final int userId;
    private final int streamType;

    public BasicRecommendStreams(String[] parts) {
        this.userId = Integer.parseInt(parts[0]);
        this.streamType = Utils.parseStreamType(parts[2]);
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        List<Integer> listenedStreamers = db.selectListenedStreamers(userId);
        List<Integer> userStreams = db.getUser(userId).getStreams();
        List<StreamInfo> selectedStreams = db.selectUserStreams(listenedStreamers, userId, streamType, false);

        // sortarea informatiilor despre un stream
        selectedStreams.sort(Comparator.comparingLong(s -> -s.getStream().getNoOfStreams()));

        // afisarea in format json a streamurilor
        int nrOfSelectedStreams = Math.min(5, selectedStreams.size());
        System.out.println(Utils.listJson(selectedStreams.subList(0, nrOfSelectedStreams)));
    }
}
