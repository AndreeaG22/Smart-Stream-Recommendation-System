import java.util.ArrayList;
import java.util.Comparator;

public class SingletonRecommendOrSurprise {
    private static SingletonRecommendOrSurprise uniqueInstance;
    private SingletonRecommendOrSurprise() {}
    public static SingletonRecommendOrSurprise getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonRecommendOrSurprise();
        }
        return uniqueInstance;
    }

    public void recommendStreams(String line, StreamsFacade streamsFacade) {
        String[] info = line.split(" ");

        Integer code = streamsFacade.workWithStreams.getCode(info[2]);

        Users user = (Users) streamsFacade.users.get(Integer.parseInt(info[0]));

        ArrayList<Integer> streamsList = user.getStreams();
        ArrayList<Streams> goodStreams = streamsFacade.collectUnlistenedStreamsRecommend(streamsList, code);

        goodStreams.sort(Comparator.comparing(Streams::getNoOfStreams).reversed());

        StringBuilder json = new StringBuilder();
        json.append("[");

        int size = goodStreams.size();

        if(size > 5)
            size = 5;
        for(int i = 0; i < size; i++) {
            Streams stream = goodStreams.get(i);
            json.append(streamsFacade.addStreamToPrint(stream));
        }

        streamsFacade.finalizeJsonAndPrint(json);

    }

    public void surpriseStreams(String line, StreamsFacade streamsFacade) {
        String[] info = line.split(" ");

        Integer code = streamsFacade.workWithStreams.getCode(info[2]);

        Users user = (Users) streamsFacade.users.get(Integer.parseInt(info[0]));
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        ArrayList<Integer> streamsList = user.getStreams();
        ArrayList<Streams> goodStreams = streamsFacade.collectUnlistenedStreamsSurprise(streamsList, code);

        goodStreams.sort(Comparator.comparing(Streams::getDateAdded).thenComparing(Streams::getNoOfStreams).reversed());

        StringBuilder json = new StringBuilder();
        json.append("[");

        int size = goodStreams.size();
        if(size > 3)
            size = 3;
        for(int i = 0; i < size; i++) {
            Streams stream = goodStreams.get(i);
            json.append(streamsFacade.addStreamToPrint(stream));
        }

        streamsFacade.finalizeJsonAndPrint(json);
    }
}
