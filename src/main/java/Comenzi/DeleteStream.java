package Comenzi;

public class DeleteStream implements Command {
    private final int streamerId;
    private final int streamId;

    public DeleteStream(String[] parts) {
        this.streamerId = Integer.parseInt(parts[0]);
        this.streamId = Integer.parseInt(parts[2]);
    }

    @Override
    public void execute() {
        Database.getInstance().delete(streamerId, streamId);
    }

}
