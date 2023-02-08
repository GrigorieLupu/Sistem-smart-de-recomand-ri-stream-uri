package Informatii;

public class Stream {
    private int streamType;
    private int id;
     private int streamGenre;
    private long noOfStreams;
    private int streamerId;
    private long length;
    private long dateAdded;
    private String name;

    private Stream(int streamType, int id, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamType() {
        return streamType;
    }

    public int getId() {
        return id;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public long getLength() {
        return length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private int streamType;
        private int id;
        private int streamGenre;
        private long noOfStreams;
        private int streamerId;
        private long length;
        private long dateAdded;
        private String name;

        public Builder streamType(int streamType) {
            this.streamType = streamType;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder streamGenre(int streamGenre) {
            this.streamGenre = streamGenre;
            return this;
        }

        public Builder noOfStreams(long noOfStreams) {
            this.noOfStreams = noOfStreams;
            return this;
        }

        public Builder streamerId(int streamerId) {
            this.streamerId = streamerId;
            return this;
        }

        public Builder length(long length) {
            this.length = length;
            return this;
        }

        public Builder dateAdded(long dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Stream build() {
            return new Stream(streamType, id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
        }
    }
}
