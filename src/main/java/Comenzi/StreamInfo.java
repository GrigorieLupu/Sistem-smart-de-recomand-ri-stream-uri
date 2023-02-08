package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;

public class StreamInfo {
    private final Streamer streamer;
    private final Stream stream;


    public StreamInfo(Streamer streamer, Stream stream) {
        this.streamer = streamer;
        this.stream = stream;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public Stream getStream() {
        return stream;
    }
}
