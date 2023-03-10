import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class EntityFactory {
    public HashMap<Integer, Entity> createEntity(String entityType, String file) {
        if(entityType == null || file == null) {
            return null;
        }
        List<String[]> lines = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(file))) {
            lines = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        switch (entityType) {
            case "STREAMERS":
                HashMap<Integer, Entity> streamers = new HashMap<>();
                String[] info;
                for (int i = 1; i < lines.size(); i++) {
                    info = lines.get(i);
                    Streamers streamer = new Streamers(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2]);
                    streamers.put(streamer.getId(), streamer);
                }
                return streamers;
            case "USERS":
                HashMap<Integer, Entity> users = new HashMap<>();
                String[] line;
                for (int i = 1; i < lines.size(); i++) {
                    line = lines.get(i);
                    Users user = new Users(Integer.parseInt(line[0]), line[1], listOfStreams(line[2]));
                    users.put(user.getId(), user);
                }
                return users;
            default:
                throw new IllegalArgumentException("Unknown entity type: " + entityType);
        }

    }
    public ArrayList<Integer> listOfStreams(String streams) {
        ArrayList<Integer> streamsList = new ArrayList<>();
        String[] streamsArray = streams.split(" ");
        for (String s : streamsArray) {
            streamsList.add(Integer.parseInt(s));
        }
        return streamsList;
    }
}
