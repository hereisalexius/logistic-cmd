package logistic.entities;

public class Address implements java.io.Serializable{

    private int bldNum;
    private String bldStreet;
    private String bldTown;
    private String bldPcode;
    private String bldCountry;

    public Address() {
    }
    
    public Address(int bldNum, String bldStreet, String bldTown, String bldPcode, String bldCountry) {
        this.bldNum = bldNum;
        this.bldStreet = bldStreet;
        this.bldTown = bldTown;
        this.bldPcode = bldPcode;
        this.bldCountry = bldCountry;
    }

    public int getBldNum() {
        return bldNum;
    }

    public void setBldNum(int bldNum) {
        this.bldNum = bldNum;
    }

    public String getBldStreet() {
        return bldStreet;
    }

    public void setBldStreet(String bldStreet) {
        this.bldStreet = bldStreet;
    }

    public String getBldTown() {
        return bldTown;
    }

    public void setBldTown(String bldTown) {
        this.bldTown = bldTown;
    }

    public String getBldPcode() {
        return bldPcode;
    }

    public void setBldPcode(String bldPcode) {
        this.bldPcode = bldPcode;
    }

    public String getBldCountry() {
        return bldCountry;
    }

    public void setBldCountry(String bldCountry) {
        this.bldCountry = bldCountry;
    }

    @Override
    public String toString() {
        return "Address{" + "bldNum=" + bldNum + ", bldStreet=" + bldStreet + ", bldTown=" + bldTown + ", bldPcode=" + bldPcode + ", bldCountry=" + bldCountry + '}';
    }
}
