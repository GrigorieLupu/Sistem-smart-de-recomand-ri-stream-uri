import Comenzi.*;
import Informatii.Stream;
import Informatii.Streamer;
import Informatii.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProiectPOO {

    public static final String resourcesRoot = "src/main/resources/";

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        ProiectPOO proiectPOO = new ProiectPOO();

        proiectPOO.parseStreamers(args[0]);
        proiectPOO.parseStreams(args[1]);
        proiectPOO.parseUsers(args[2]);
        CommandSystem commandSystem = CommandSystem.getInstance();

        try (BufferedReader br = new BufferedReader(new FileReader(resourcesRoot + args[3]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                //adaugare in lista de comenzi a obiectului commandSystem

                switch (parts[1]) {
                    case "ADD":
                        commandSystem.addCommand(new AddStream(parts));
                        break;

                    case "DELETE":
                        commandSystem.addCommand(new DeleteStream(parts));
                        break;

                    case "LIST":
                        commandSystem.addCommand(new ListStreams(parts));
                        break;

                    case "LISTEN":
                        commandSystem.addCommand(new ListenCommand(parts));
                        break;

                    case "RECOMMEND":
                        commandSystem.addCommand(new BasicRecommendStreams(parts));
                        break;

                    case "SURPRISE":
                        commandSystem.addCommand(new SurpriseRecommendStreams(parts));
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid command type");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        commandSystem.execute();

        Database.getInstance().clear();
        CommandSystem.getInstance().clear();
    }

    //iau informatiile din fiserul Streamers.csv

    private void parseStreamers(String directory) {
        String filePath = resourcesRoot + directory;
        for (String[] values : Utils.getCsvLine(filePath)) {
            int id = Integer.parseInt(values[0]);
            int streamerType = Integer.parseInt(values[1]);
            String name = values[2];

            Streamer streamer = new Streamer(id, streamerType, name);
            Database.getInstance().add(streamer);
        }
    }

    //iau informatiile din fiserul Streams.csv

    private void parseStreams(String directory) {

        String filePath = resourcesRoot + directory;
        for (String[] values : Utils.getCsvLine(filePath)) {
            int id = Integer.parseInt(values[1]);
            int streamType = Integer.parseInt(values[0]);
            int streamGenre = Integer.parseInt(values[2]);
            long noOfStreams = Long.parseLong(values[3]);
            int streamerId = Integer.parseInt(values[4]);
            long length = Long.parseLong(values[5]);
            long dateAdded = Long.parseLong(values[6]);
            String name = values[7];

            Stream stream = new Stream.Builder()
                    .id(id)
                    .streamType(streamType)
                    .streamGenre(streamGenre)
                    .noOfStreams(noOfStreams)
                    .streamerId(streamerId)
                    .length(length)
                    .dateAdded(dateAdded)
                    .name(name)
                    .build();

            Database.getInstance().add(stream);
        }
    }

    //iau informatiile din fiserul Users.csv

    private void parseUsers(String directory) {
        String filePath = resourcesRoot + directory;
        for (String[] values : Utils.getCsvLine(filePath)) {

            int id = Integer.parseInt(values[0]);
            String name = values[1];
            List<Integer> streams = new ArrayList<>();
            for (int i = 2; i < values.length; i++) {
                String[] streamIds = values[i].split(" ");
                for (String streamId : streamIds) {
                    streams.add(Integer.parseInt(streamId));
                }
            }
            User user = new User(id, name, streams);
            Database.getInstance().add(user);
        }
    }
}