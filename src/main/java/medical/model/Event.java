package medical.model;

public class Event {
    private String id;
    private String name;
    private String location;
    private String startDate;
    private String startTime;
    private String pacientName;
    private String doctorUsername;


    public Event(String id, String name, String location, String startDate, String startTime, String pacientName, String doctorUsername) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.startTime = startTime;
        this.pacientName = pacientName;
        this.doctorUsername = doctorUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getPacientName() {
        return pacientName;
    }

    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }
}
