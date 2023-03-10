import java.util.ArrayList;

public class Users implements Entity{
    private Integer id;
    private String name;
    private ArrayList<Integer> streams;

    public Users(Integer id, String name, ArrayList<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public ArrayList<Integer> getStreams() {
        return streams;
    }
    public void setStreams(ArrayList<Integer> streams) {
        this.streams = streams;
    }
}
