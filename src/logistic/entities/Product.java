package logistic.entities;

public class Product implements java.io.Serializable {
    private int proCode;
    private String proMake;
    private String proModel;
    private String proPrice;
    private String proQtyAvailable;
    private boolean proDiscountinued;

    public Product() {
    }

    public Product(int proCode, String proMake, String proModel, String proPrice, String proQtyAvailable, boolean proDiscountinued) {
        this.proCode = proCode;
        this.proMake = proMake;
        this.proModel = proModel;
        this.proPrice = proPrice;
        this.proQtyAvailable = proQtyAvailable;
        this.proDiscountinued = proDiscountinued;
    }

    public int getProCode() {
        return proCode;
    }

    public void setProCode(int proCode) {
        this.proCode = proCode;
    }

    public String getProMake() {
        return proMake;
    }

    public void setProMake(String proMake) {
        this.proMake = proMake;
    }

    public String getProModel() {
        return proModel;
    }

    public void setProModel(String proModel) {
        this.proModel = proModel;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public String getProQtyAvailable() {
        return proQtyAvailable;
    }

    public void setProQtyAvailable(String proQtyAvailable) {
        this.proQtyAvailable = proQtyAvailable;
    }

    public boolean isProDiscountinued() {
        return proDiscountinued;
    }

    public void setProDiscountinued(boolean proDiscountinued) {
        this.proDiscountinued = proDiscountinued;
    }

    @Override
    public String toString() {
        return proCode + "\t" + proMake + "\t" + proModel + "\t" + proPrice + "\t" + proQtyAvailable + "\t" + proDiscountinued;
    }
}
