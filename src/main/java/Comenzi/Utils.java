package Comenzi;

import Informatii.Stream;
import Informatii.Streamer;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utils {
    public static String listJson(List<StreamInfo> streamInfos) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf.format(new Date());
        for (int i = 0; i < streamInfos.size(); i++) {
            Stream stream = streamInfos.get(i).getStream();
            Streamer streamer = streamInfos.get(i).getStreamer();
            sb.append("{\"id\":\"").append(stream.getId()).append("\",");
            sb.append("\"name\":\"").append(stream.getName()).append("\",");
            sb.append("\"streamerName\":\"").append(streamer.getName()).append("\",");
            sb.append("\"noOfListenings\":\"").append(stream.getNoOfStreams()).append("\",");
            sb.append("\"length\":\"").append(getLengthString(stream.getLength())).append("\",");
            sb.append("\"dateAdded\":\"").append(getStringDate(stream.getDateAdded())).append("\"}");
            if (i < streamInfos.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static int parseStreamType(String streamType) {
        switch (streamType) {
            case "SONG":
                return 1;

            case "PODCAST":
                return 2;

            case "AUDIOBOOK":
                return 3;

            default:
                return -1;
        }
    }

    // pentru cazurile de exceptie
    public static String joinStrings(int start, int end, String[] parts) {
        return String.join(" ", Arrays.copyOfRange(parts, start, end));
    }

    public static String getStringDate(long milis) {
        Date date = new Date(milis * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));

        return sdf.format(date);
    }

    // prelucrez lungimea in secunde in formatul HH:MM:SS
    public static String getLengthString(long seconds) {
        int m, h, sec;
        h = (int) (seconds / 3600);

        if (h > 0) {
            m = (int) ((seconds % 3600) / 60);
            sec = (int) ((seconds % 3600) % 60);
            return String.format("%02d:%02d:%02d", h, m, sec);
        } else {
            m = (int) (seconds / 60);
            sec = (int) (seconds % 60);
            return String.format("%02d:%02d", m, sec);
        }
    }

    // citesc din fisierul csv
    public static List<String[]> getCsvLine(String filename) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build();
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
