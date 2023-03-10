public class Streams{
    private final Integer streamType;
    private Integer id;
    private final Integer streamGenre;
    private Long noOfStreams;
    private final Integer streamerId;
    private Long length;
    private Long dateAdded;
    private String name;
    private Streams(StreamsBuilder builder) {
        this.streamType = builder.streamType;
        this.id = builder.id;
        this.streamGenre = builder.streamGenre;
        this.noOfStreams = builder.noOfStreams;
        this.streamerId = builder.streamerId;
        this.length = builder.length;
        this.dateAdded = builder.dateAdded;
        this.name = builder.name;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public Integer getStreamerId() {
        return streamerId;
    }


    public void setLength(Long length) {
        this.length = length;
    }

    public Long getLength() {
        return length;
    }


    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getDateAdded() {
        return dateAdded;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public StringBuilder constructJsonFormat(Streams stream, String date, String finalFormat, StreamsFacade streamsFacade) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"id\":\"").append(stream.getId()).append("\",");
        json.append("\"name\":\"").append(stream.getName()).append("\",");
        json.append("\"streamerName\":\"").append(getStreamerName(stream.getStreamerId(), streamsFacade)).append("\",");
        json.append("\"noOfListenings\":\"").append(stream.getNoOfStreams()).append("\",");
        json.append("\"length\":\"").append(finalFormat).append("\",");
        json.append("\"dateAdded\":\"").append(date).append("\"},");
        return json;
    }
    public String getStreamerName(int streamerId, StreamsFacade streamsFacade) {
        return streamsFacade.streamers.get(streamerId).getName();
    }
    public static class StreamsBuilder {
        private Integer streamType;
        private Integer id;
        private Integer streamGenre;
        private Long noOfStreams;
        private Integer streamerId;
        private Long length;
        private Long dateAdded;
        private String name;

        public StreamsBuilder streamType(Integer streamType) {
            this.streamType = streamType;
            return this;
        }
        public StreamsBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public StreamsBuilder streamGenre(Integer streamGenre) {
            this.streamGenre = streamGenre;
            return this;
        }
        public StreamsBuilder noOfStreams(Long noOfStreams) {
            this.noOfStreams = noOfStreams;
            return this;
        }
        public StreamsBuilder streamerId(Integer streamerId) {
            this.streamerId = streamerId;
            return this;
        }
        public StreamsBuilder length(Long length) {
            this.length = length;
            return this;
        }
        public StreamsBuilder dateAdded(Long dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }
        public StreamsBuilder name(String name) {
            this.name = name;
            return this;
        }
        public Streams build() {
            return new Streams(this);
        }
    }

}


