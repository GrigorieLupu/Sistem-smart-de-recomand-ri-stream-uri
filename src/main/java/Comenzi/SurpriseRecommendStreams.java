package Comenzi;

import Informatii.Stream;

import java.util.Comparator;
import java.util.List;

/**
 * Comanda care recomanda 3 streamuri surpriza (cele mai recent adaugate) unui user
 * care au streamerii neascultati de user
 */
public class SurpriseRecommendStreams implements Command {

    private final int userId;
    private final int streamType;

    public SurpriseRecommendStreams(String[] parts) {
        this.userId = Integer.parseInt(parts[0]);
        this.streamType = Utils.parseStreamType(parts[2]);
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        List<Stream> dbStreams = db.getStreams();
        List<Integer> listenedStreamers = db.selectListenedStreamers(userId);
        List<StreamInfo> selectedStreams = db.selectUserStreams(listenedStreamers, userId, streamType, false);
        List<Integer> userStreams = db.getUser(userId).getStreams();

        selectedStreams.sort(new Comparator<StreamInfo>() {
            // compari prima data dupa si apoi dupa numarul de ascultari
            @Override
            public int compare(StreamInfo s1, StreamInfo s2) {
                if(s1.getStream().getDateAdded() == s2.getStream().getDateAdded()) {
                    return Long.compare(s2.getStream().getNoOfStreams(), s1.getStream().getNoOfStreams());
                } else {
                    return Long.compare(s2.getStream().getNoOfStreams(), s1.getStream().getNoOfStreams());
                }
            }
        });

        // afisarea in format json a streamurilor
        int nrOfSelectedStreams = Math.min(3, selectedStreams.size());
        System.out.println(Utils.listJson(selectedStreams.subList(0, nrOfSelectedStreams)));
    }
}
