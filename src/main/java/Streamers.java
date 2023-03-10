public class Streamers  implements Entity{
    private final Integer streamerType;
    private Integer id;
    private String name;

    public Streamers(Integer streamerType, Integer id, String name) {
        this.streamerType = streamerType;
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStreamerType() {
        return streamerType;
    }
}
