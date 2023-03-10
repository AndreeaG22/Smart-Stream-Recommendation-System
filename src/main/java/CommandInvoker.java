import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, Command> commands;
    public CommandInvoker() {
        commands = new HashMap<>();
        commands.put("list", new ListStreams());
        commands.put("add", new AddStreams());
        commands.put("delete", new DeleteStream());
        commands.put("update", new ListenStream());
        commands.put("listen", new ListenStream());
        commands.put("recommend", new RecommendStreams());
        commands.put("surprise", new SurpriseStreams());
    }
    public void execute(String command, String line, StreamsFacade streamsFacade) {
        commands.get(command).execute(line, streamsFacade);
    }
}
