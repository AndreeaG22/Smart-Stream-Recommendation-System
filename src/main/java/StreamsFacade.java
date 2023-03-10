import java.util.*;

public class StreamsFacade {


    HashMap<Integer, Entity> users;
    HashMap<Integer, Entity> streamers;
    LinkedHashMap<Integer, Streams> streams;
    DateAndTime dateAndTime = new DateAndTime();
    WorkWithStreams workWithStreams = new WorkWithStreams();
    public LinkedHashMap<Integer, Streams> constructStreams(String file) {
        return workWithStreams.constructStreams(file);
    }
    public StringBuilder addStreamToPrint(Streams stream) {
        return workWithStreams.addStreamToPrint(stream, dateAndTime, this);
    }
    public void listStreamerStreams(String streamerId) {
        workWithStreams.listStreamerStreams(streamerId, this);
    }
    public void listUserStreams(String userId) {
        workWithStreams.listUserStreams(userId, this);
    }

    public void addStreams(String line) {
        workWithStreams.addStreams(line, this);
    }

    public void deleteStream(String streamId) {
        workWithStreams.deleteStream(streamId, this);
    }

    public void listenStream(String userId, String streamId) {
        workWithStreams.listenStream(userId, streamId, this);
    }

    public ArrayList<Streams> collectUnlistenedStreamsSurprise(ArrayList<Integer> streamsList, Integer code) {
        ArrayList<Integer> listendedStreamers = workWithStreams.collectStreamersListenedByUser(streamsList, this);
        ArrayList<Integer> streamersToListen = workWithStreams.collectUnlistenedStreamers(listendedStreamers, this);
        ArrayList<Integer> goodStreamers = workWithStreams.collectStreamersWithSpecificCode(code, streamersToListen, this);
        return workWithStreams.collectUnlistenedStreams(goodStreamers, streamsList, this);
    }
    public ArrayList<Streams> collectUnlistenedStreamsRecommend(ArrayList<Integer> streamsList, Integer code) {
        ArrayList<Integer> listendedStreamers = workWithStreams.collectStreamersListenedByUser(streamsList, this);
        ArrayList<Integer> goodStreamers = workWithStreams.collectStreamersWithSpecificCode(code, listendedStreamers, this);
        return workWithStreams.collectUnlistenedStreams(goodStreamers, streamsList, this);
    }
    public void finalizeJsonAndPrint(StringBuilder json) {
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        System.out.println(json);
    }
}
