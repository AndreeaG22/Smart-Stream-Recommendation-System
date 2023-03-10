public class RecommendStreams extends Command {
    public void execute(String line, StreamsFacade streamsFacade) {
        SingletonRecommendOrSurprise recommend = SingletonRecommendOrSurprise.getInstance();
        recommend.recommendStreams(line, streamsFacade);
    }
}
