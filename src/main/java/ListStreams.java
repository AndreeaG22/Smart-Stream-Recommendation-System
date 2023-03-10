import java.util.Map;

public class ListStreams extends Command {
    public void execute(String line, StreamsFacade streamsFacade) {
        String[] arguments = line.split(" ");
        if(streamerExist(streamsFacade, arguments)) {
            streamsFacade.listStreamerStreams(arguments[0]);
        }else {
            streamsFacade.listUserStreams(arguments[0]);
        }
    }
    public boolean streamerExist(StreamsFacade streamsFacade, String[] arguments) {
        for(Map.Entry<Integer, Entity> entry : streamsFacade.streamers.entrySet()) {
            if(entry.getValue().getId() == Integer.parseInt(arguments[0])) {
                return true;
            }
        }
        return false;
    }
}
