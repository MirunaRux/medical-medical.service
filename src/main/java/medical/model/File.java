package medical.model;

public class File {
    private String id;
    private String content;
    private String pacient_id;

    public File(String id, String content, String pacient_id) {
        this.id = id;
        this.content = content;
        this.pacient_id = pacient_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPacient_id() {
        return pacient_id;
    }

    public void setPacient_id(String pacient_id) {
        this.pacient_id = pacient_id;
    }
}
