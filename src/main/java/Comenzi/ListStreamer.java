package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;

import java.util.ArrayList;
import java.util.List;

public class ListStreamer extends ListAlghorithm {
    @Override
    public void list(int id) {
        List<StreamInfo> currentStreams = new ArrayList<>();
        Streamer streamer = Database.getInstance().getStreamer(id);

        for (Stream stream : Database.getInstance().getStreamerStreams(id)) {
                currentStreams.add(new StreamInfo(streamer, stream));
        }

        System.out.println(Utils.listJson(currentStreams));
    }
}
