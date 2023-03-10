public class ListenStream extends Command{
    public void execute(String line, StreamsFacade streamsFacade) {
        String[] arguments = line.split(" ");
        streamsFacade.listenStream(arguments[0], arguments[2]);
    }
}
