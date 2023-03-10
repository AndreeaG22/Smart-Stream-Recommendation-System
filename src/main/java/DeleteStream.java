public class DeleteStream extends Command {
    public void execute(String line, StreamsFacade streamsFacade) {
        String[] arguments = line.split(" ");
        streamsFacade.deleteStream(arguments[2]);
    }

}
