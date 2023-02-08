package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;

import java.util.ArrayList;
import java.util.List;

public class ListUser extends ListAlghorithm {
    @Override
    public void list(int id) {
        List<StreamInfo> currentStreams = new ArrayList<>();

        List<Stream> userStreams = Database.getInstance().getUserStreams(id);
        for (Stream stream : userStreams) {
            Streamer streamer = Database.getInstance().getStreamer(stream.getStreamerId());
            currentStreams.add(new StreamInfo(streamer, stream));
        }

        System.out.println(Utils.listJson(currentStreams));
    }
}
