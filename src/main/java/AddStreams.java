public class AddStreams extends Command{
    public void execute(String line, StreamsFacade streamsFacade) {
        streamsFacade.addStreams(line);
    }
}
