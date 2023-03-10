import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithStreams {
    public LinkedHashMap<Integer, Streams> constructStreams(String file) {
        LinkedHashMap<Integer, Streams> streams = new LinkedHashMap<>();
        List<String[]> lines = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(file))) {
            lines = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        String[] info;
        for (int i = lines.size() - 1; i >= 1; i--) {
            info = lines.get(i);
            Integer key = Integer.parseInt(info[1]);
            Streams newStream = new Streams.StreamsBuilder()
                    .streamType(Integer.parseInt(info[0]))
                    .id(Integer.parseInt(info[1]))
                    .streamGenre(Integer.parseInt(info[2]))
                    .noOfStreams(Long.parseLong(info[3]))
                    .streamerId(Integer.parseInt(info[4]))
                    .length(Long.parseLong(info[5]))
                    .dateAdded(Long.parseLong(info[6]))
                    .name(info[7])
                    .build();
            streams.put(key, newStream);
        }
        return streams;
    }
    public StringBuilder addStreamToPrint(Streams stream, DateAndTime dateAndTime, StreamsFacade streamsFacade) {
        StringBuilder json = new StringBuilder();
        long time = stream.getLength();
        String finalFormat = dateAndTime.timeFormat(time);

        Long dateAdded = stream.getDateAdded();
        String date = dateAndTime.getDate(dateAdded);

        json.append(stream.constructJsonFormat(stream, date, finalFormat, streamsFacade));
        return json;
    }
    public void listStreamerStreams(String streamerId, StreamsFacade streamsFacade) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(Map.Entry<Integer, Streams> entry : streamsFacade.streams.entrySet()) {
            Streams stream = entry.getValue();
            if(stream.getStreamerId() == Integer.parseInt(streamerId)) {
                json.append(addStreamToPrint(stream, streamsFacade.dateAndTime, streamsFacade));
            }
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        System.out.println(json);
    }
    public void listUserStreams(String userId, StreamsFacade streamsFacade) {
        Users currentUser = (Users) streamsFacade.users.get(Integer.parseInt(userId));
        ArrayList<Integer> streamsList = currentUser.getStreams();
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (Integer streamId : streamsList) {
            Streams stream = streamsFacade.streams.get(streamId);
            json.append(addStreamToPrint(stream, streamsFacade.dateAndTime, streamsFacade));
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        System.out.println(json);
    }
    public void addStreams(String line, StreamsFacade streamsFacade) {
        String[] info = line.split(" ");
        Long date = Instant.now().getEpochSecond();
        StringBuilder nameOfStream = new StringBuilder();
        for(int i = 6; i < info.length; i++)
            nameOfStream.append(info[i]).append(" ");
        nameOfStream.deleteCharAt(nameOfStream.length() - 1);
        Streams newStream = new Streams.StreamsBuilder()
                .streamType(Integer.parseInt(info[2]))
                .id(Integer.parseInt(info[3]))
                .streamGenre(Integer.parseInt(info[4]))
                .noOfStreams(Long.parseLong("0"))
                .streamerId(Integer.parseInt(info[0]))
                .length(Long.parseLong(info[5]))
                .dateAdded(date)
                .name(nameOfStream.toString())
                .build();
        streamsFacade.streams.put(newStream.getId(), newStream);
    }
    public void deleteStream(String streamId, StreamsFacade streamsFacade) {
        streamsFacade.streams.remove(Integer.parseInt(streamId));
        for(Map.Entry<Integer, Entity> entry : streamsFacade.users.entrySet()) {
            Users user = (Users) entry.getValue();
            ArrayList<Integer> streamsList = user.getStreams();
            Integer index = getIndex(streamsList, Integer.parseInt(streamId));
            if(index != -1) {
                streamsList.remove(index);
                user.setStreams(streamsList);
            }
        }
    }
    public Integer getIndex(ArrayList<Integer> streamList, Integer streamId) {
        for(int i = 0; i < streamList.size(); i++)
            if(streamList.get(i).equals(streamId))
                return i;
        return -1;
    }
    public void listenStream(String userId, String streamId, StreamsFacade streamsFacade) {
        Users user = (Users) streamsFacade.users.get(Integer.parseInt(userId));
        ArrayList<Integer> streamsList = user.getStreams();
        Integer index = getIndex(streamsList, Integer.parseInt(streamId));
        if(index == -1) {
            streamsList.add(Integer.parseInt(streamId));
            user.setStreams(streamsList);
        }
        Streams stream = streamsFacade.streams.get(Integer.parseInt(streamId));
        stream.setNoOfStreams(stream.getNoOfStreams() + 1);
    }
    public Integer getCode(String type) {
        int code = 0;
        switch (type) {
            case "SONG":
                code = 1;
                break;
            case "PODCAST":
                code = 2;
                break;
            case "AUDIOBOOK":
                code = 3;
                break;
        }
        return code;
    }
    public ArrayList<Integer> collectStreamersListenedByUser(ArrayList<Integer> streamsList, StreamsFacade streamsFacade) {
        ArrayList<Integer> listendedStreamers = new ArrayList<>();
        for(Integer streamId : streamsList) {
            Streams stream = streamsFacade.streams.get(streamId);
            Integer streamerId = stream.getStreamerId();
            if(!listendedStreamers.contains(streamerId)) {
                listendedStreamers.add(streamerId);
            }
        }
        return listendedStreamers;
    }
    public ArrayList<Integer> collectStreamersWithSpecificCode(Integer code, ArrayList<Integer> listenedStreamers, StreamsFacade streamsFacade) {
        ArrayList<Integer> goodStreamers = new ArrayList<>();
        for(Integer streamerId : listenedStreamers) {
            Streamers streamer = (Streamers) streamsFacade.streamers.get(streamerId);
            if(streamer.getStreamerType().equals(code)) {
                goodStreamers.add(streamerId);
            }
        }
        return goodStreamers;
    }
    public ArrayList<Streams> collectUnlistenedStreams(ArrayList<Integer> goodStreamers, ArrayList<Integer> streamsList, StreamsFacade streamsFacade) {
        ArrayList<Streams> goodStreams = new ArrayList<>();
        for(Integer streamerId : goodStreamers) {
            for(Map.Entry<Integer, Streams> entry : streamsFacade.streams.entrySet()) {
                Streams stream = entry.getValue();
                if(stream.getStreamerId().equals(streamerId)) {
                    for(Integer streamId : streamsList)
                        if(!(streamId.equals(stream.getId()))) {
                            goodStreams.add(stream);
                            break;
                        }
                }
            }
        }
        return goodStreams;
    }
    public ArrayList<Integer> collectUnlistenedStreamers(ArrayList<Integer> listenedStreamers, StreamsFacade streamsFacade) {
        ArrayList<Integer> streamersToListen = new ArrayList<>();
        for (Map.Entry<Integer, Entity> entry : streamsFacade.streamers.entrySet()) {
            Streamers streamer = (Streamers) entry.getValue();
            if (!streamersToListen.contains(streamer.getId()) && !listenedStreamers.contains(streamer.getId())) {
                streamersToListen.add(streamer.getId());
            }
        }
        return streamersToListen;
    }
}
