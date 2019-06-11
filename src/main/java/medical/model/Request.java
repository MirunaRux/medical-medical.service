package medical.model;

public class    Request {
    private String id;
    private String drugName;
    private int cantity;

    public Request(String id, String drugName, int cantity) {
        this.id = id;
        this.drugName = drugName;
        this.cantity = cantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getCantity() {
        return cantity;
    }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }
}
