package Comenzi;

import Informatii.Stream;

import java.util.Comparator;
import java.util.List;

public class SurpriseRecommendStreams{

    //private final int userId;
    //private final int streamType;

    /*
    public SurpriseRecommendStreams(String[] parts) {
        this.userId = Integer.parseInt(parts[0]);
        this.streamType = Utils.parseStreamType(parts[2]);
    }

    @Override
    public void execute() {
        Database db = Database.getInstance();
        List<Stream> dbStreams = db.getStreams();
        List<StreamInfo> selectedStreams = db.selectUserStreams(userId, streamType, false);
        List<Integer> userStreams = db.getUser(userId).getStreams();

        selectedStreams.sort(new Comparator<StreamInfo>() {
            @Override
            public int compare(StreamInfo s1, StreamInfo s2) {
                if (s1.getStream().getDateAdded() == s2.getStream().getDateAdded()) {
                    return Long.compare(s2.getStream().getNoOfStreams(), s2.getStream().getNoOfStreams());
                } else {
                    return Long.compare(s2.getStream().getDateAdded(), s1.getStream().getDateAdded());
                }
            }
        });

        // afisarea in format json a streamurilor
        int nrOfSelectedStreams = Math.min(3, selectedStreams.size());
        Utils.listJson(selectedStreams.subList(0, nrOfSelectedStreams));
    }

     */
}
