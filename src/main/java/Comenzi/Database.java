package Comenzi;

import Informatii.*;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static Database instance = null;
    private List<Streamer> streamers;
    private List<Stream> streams;
    private List<User> users;

    private Database() {
        streamers = new ArrayList<>();
        streams = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void add(Streamer streamer) {
        streamers.add(streamer);
    }

    public void add(Stream stream) {
        streams.add(stream);
    }

    public void add(User user) {
        users.add(user);
    }

    public void delete(int streamerId, int streamId) {
        for (int i = 0; i < streams.size(); i++) {
            Stream currentStream = streams.get(i);
            if (currentStream.getId() == streamId && currentStream.getStreamerId() == streamerId) {
                streams.remove(i);
                return;
            }
        }
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Stream getStream(int id) {
        for (Stream stream : streams) {
            if (stream.getId() == id) {
                return stream;
            }
        }
        return null;
    }

    public Streamer getStreamer(int id) {
        for (Streamer streamer : streamers) {
            if (streamer.getId() == id) {
                return streamer;
            }
        }
        return null;
    }

    public List<Stream> getStreamerStreams(int id) {
        List<Stream> streamerStreams = new ArrayList<>();
        for (Stream stream : streams) {
            if (stream.getStreamerId() == id) {
                streamerStreams.add(stream);
            }
        }
        return streamerStreams;
    }

    public List<Stream> getUserStreams(int id) {
        List<Stream> userStreams = new ArrayList<>();
        for (Integer streamId : getUser(id).getStreams()) {
            userStreams.add(getStream(streamId));
        }
        return userStreams;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public List<Integer> selectStreamerIds(List<Integer> streamIds) {
        List<Integer> restult = new ArrayList<>();

        for (Integer streamId: streamIds) {
            for (Stream stream: streams) {
                if (stream.getId() == streamId) {
                    restult.add(stream.getStreamerId());
                }
            }
        }

        return restult;
    }

    public List<StreamInfo> selectUserStreams(List<Integer> streamerIds, int userId, int streamType, boolean listened) {
        List<StreamInfo> selectedStreams = new ArrayList<>();
        List<Integer> userStreams = getUser(userId).getStreams();
        // selectare de stream-uri nevizualizate
        for (Stream stream : streams) {
            boolean condition = listened == userStreams.contains(stream.getId());

            if (streamType == stream.getStreamType() && condition && streamerIds.contains(stream.getStreamerId())) {
                Streamer streamer = getStreamer(stream.getStreamerId());
                selectedStreams.add(new StreamInfo(streamer, stream));
            }
        }
        return selectedStreams;
    }

    public List<Streamer> selectListenedStreamers(int userId) {
        List<Integer> userStreams = getUser(userId).getStreams();
        List<Streamer> selectedStreamers = new ArrayList<>();

        for (Integer streamId : userStreams) {
            Stream stream = getStream(streamId);
            Streamer streamer = getStreamer(stream.getStreamerId());

            if (selectedStreamers.contains(streamer)) continue;

            selectedStreamers.add(streamer);
        }

        return selectedStreamers;
    }
    private boolean isStreamer(int id) {
        for (Streamer streamer : streamers) {
            if (streamer.getId() == id) return true;
        }
        return false;
    }

    private boolean isUser(int id) {
        for (User user : users) {
            if (user.getId() == id) return true;
        }
        return false;
    }

    public void clear() {
        streamers.clear();
        streams.clear();
        users.clear();
        instance = null;
    }
}