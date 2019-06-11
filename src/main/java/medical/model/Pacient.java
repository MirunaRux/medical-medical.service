package medical.model;

public class Pacient {
    private String id;
    private String name;
    private String surname;
    private String birthday;
    private String cnp;
    private String dateIn;
    private String dateEx;

    public Pacient(String id, String firstName, String lastName, String age, String cnp, String dateIn, String dateEx) {
        this.id = id;
        this.name = firstName;
        this.surname = lastName;
        this.birthday = birthday;
        this.cnp = cnp;
        this.dateIn = dateIn;
        this.dateEx = dateEx;
    }

    /*public Pacient(String referenceNumber, String firstName, String lastName) {
        this.id = referenceNumber;
        this.name = firstName;
        this.surname = lastName;
    }
*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateEx() {
        return dateEx;
    }

    public void setDateEx(String dateEx) {
        this.dateEx = dateEx;
    }

    public String getName() {
        return name;
    }
}
