package Comenzi;

import Informatii.Stream;

import java.util.Comparator;
import java.util.List;

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
            @Override
            public int compare(StreamInfo s1, StreamInfo s2) {
                return Long.compare(s2.getStream().getNoOfStreams(), s1.getStream().getNoOfStreams());
            }
        });

        // afisarea in format json a streamurilor
        int nrOfSelectedStreams = Math.min(3, selectedStreams.size());
        System.out.println(Utils.listJson(selectedStreams.subList(0, nrOfSelectedStreams)));
    }
}
