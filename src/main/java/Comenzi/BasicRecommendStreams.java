package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        List<Streamer> listenedStreamers = db.selectListenedStreamers(userId);
        List<Integer> userStreams = db.getUser(userId).getStreams();
//      List<Stream> selectedStreams = db.selectUserStreams(listenedStreamers, userId, streamType, false);
        List<StreamInfo> selectedStreams = new ArrayList<>();

        for (Streamer streamer: listenedStreamers) {
            List<Stream> streamerStreams = db.getStreamerStreams(streamer.getId());
            for (Stream stream: streamerStreams) {
                if (stream.getStreamType() == streamType && !userStreams.contains(stream.getId())) {
                    selectedStreams.add(new StreamInfo(streamer, stream));
                }
            }
        }

        // sortarea informatiilor despre un stream
        selectedStreams.sort(Comparator.comparingLong(s -> -s.getStream().getNoOfStreams()));

        // afisarea in format json a streamurilor
        int nrOfSelectedStreams = Math.min(5, selectedStreams.size());
        Utils.listJson(selectedStreams.subList(0, nrOfSelectedStreams));
    }
}
