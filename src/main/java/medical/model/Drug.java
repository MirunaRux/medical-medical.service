package medical.model;

public class Drug {
    private String id;
    private String name;
    private int drugNumber;

    public Drug(String id, String name, int drugNumber) {
        this.id = id;
        this.name = name;
        this.drugNumber = drugNumber;
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

    public int getDrugNumber() {
        return drugNumber;
    }

    public void setDrugNumber(int drugNumber) {
        this.drugNumber = drugNumber;
    }
}
