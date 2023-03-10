public class SurpriseStreams extends Command {
    public void execute(String line, StreamsFacade streamsFacade) {
        SingletonRecommendOrSurprise surprise = SingletonRecommendOrSurprise.getInstance();
        surprise.surpriseStreams(line, streamsFacade);
    }
}
