package logistic.stage.supp;

import logistic.MainApp;
import logistic.entities.Product;
import logistic.entities.SupRegion;
import logistic.entities.Supplier;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.MessageStage;
import logistic.stage.SetField;


public class SupplierStage extends ConsoleStage {
    
    private Supplier selectedSup;
    
    public SupplierStage(MainApp app) {
        super(app);
    }

    public void setSelectedSup(Supplier selectedSup) {
        this.selectedSup = selectedSup;
    }
    
    
    
    @Override
    public void printStage() {
        printSupplierInfo();
        printMenu();
        listenCommands();
    }
    
    private void printSupplierInfo() {
        System.out.println("[Supplier]");
        System.out.println("Code\tName\tRegion");
        System.out.println(selectedSup);
        System.out.println("");
        System.out.println(selectedSup.getSupAddress());
        System.out.println("");
        System.out.println("[Supplier Products]");
        System.out.println("Code\tMake\tModel\tPrice\tQtyAvailable\tDiscountinued");
        System.out.println("");
        for (Product p : selectedSup.getSupProducts()) {
            System.out.println(p);
        }
        System.out.println("");
    }
    
    private void printMenu() {
        System.out.println("1)Add Product(By Code)");
        System.out.println("2)Remove Product(By Code)");
        System.out.println("");
        System.out.println("3)Remove Current Supplier");
        System.out.println("");
        System.out.println("4)Set Supplier Code[" + selectedSup.getSupCode() + "]");
        System.out.println("5)Set Supplier Name[" + selectedSup.getSupName() + "]");
        System.out.println("6)Set Supplier Region[" + selectedSup.getSupRegion() + "]");
        System.out.println("");
        System.out.println("7)Set Address Build Num[" + selectedSup.getSupAddress().getBldNum() + "]");
        System.out.println("8)Set Address Street[" + selectedSup.getSupAddress().getBldStreet() + "]");
        System.out.println("9)Set Address Town[" + selectedSup.getSupAddress().getBldTown() + "]");
        System.out.println("10)Set Address Pcode[" + selectedSup.getSupAddress().getBldPcode() + "]");
        System.out.println("11)Set Address Country[" + selectedSup.getSupAddress().getBldCountry() + "]");
        System.out.println("");
        System.out.println("12)Back To Suppliers Manager");
        System.out.println();
        System.out.println("Enter command code:");
    }
    
    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new EnterTextStage(this, this, "Enter Product Code To Add", (String data) -> {
                    int codeInt = Integer.valueOf(data);
                    for (Product p : app.getData().getProducts()) {
                        if (p.getProCode() == codeInt) {
                            selectedSup.getSupProducts().add(p);
                            return "ok";
                        }
                    }
                    return "ok";
                }, app));
                break;
            case 2:
                app.setStage(new EnterTextStage(this, this, "Enter Product Code To Remove", (String data) -> {
                    int codeInt = Integer.valueOf(data);
                    for (Product p : app.getData().getProducts()) {
                        if (p.getProCode() == codeInt) {
                            selectedSup.getSupProducts().remove(p);
                            return "ok";
                        }
                    }
                    return "ok";
                }, app));
                break;
            case 3:
                app.getData().getSuppliers().remove(selectedSup);
                app.setStage(new MessageStage("Supplier was removed.", new SupplierManagmentStage(app), app));
                break;
            case 4:
                app.setStage(new EnterTextStage(this, this, "Enter Supplier Code", new SetField() {
                    @Override
                    public String set(String data) {
                        String resp = "ok";
                        if (data.matches("[0-9]+")) {
                            int codeInt = Integer.valueOf(data);
                            if (!isCodeExists(codeInt)) {
                                selectedSup.setSupCode(codeInt);
                            } else {
                                resp = "Code already exists, please enter another.";
                            }
                        } else {
                            resp = "Code must be a number, please enter another.";
                        }
                        return resp;
                    }
                    
                    private boolean isCodeExists(int code) {
                        for (Supplier s : app.getData().getSuppliers()) {
                            if (s.getSupCode() == code) {
                                return true;
                            }
                        }
                        return false;
                    }
                    
                }, app));
                break;
            case 5:
                app.setStage(new EnterTextStage(this, this, "Enter Supplier Name", new SetField() {
                    @Override
                    public String set(String data) {
                        selectedSup.setSupName(data);
                        return "ok";
                    }
                }, app));
                break;
            case 6:
                app.setStage(new EnterTextStage(this, this, "Enter Supplier Region[ 1 - UK, 2 - EU, 3 - OTHER]", new SetField() {
                    @Override
                    public String set(String data) {
                        String resp = "ok";
                        if (data.matches("[0-9]+")) {
                            int codeInt = Integer.valueOf(data);
                            switch (codeInt) {
                                case 1:
                                    selectedSup.setSupRegion(SupRegion.UNITED_KINGDOM);
                                    break;
                                case 2:
                                    selectedSup.setSupRegion(SupRegion.EUROPE);
                                    break;
                                case 3:
                                    selectedSup.setSupRegion(SupRegion.OUTSIDE_EU);
                                    break;
                                default:
                                    resp = "Input data must be between 1 - 3, please enter another.";
                                    ;
                            }
                        } else {
                            resp = "Input data must be a number, please enter another.";
                        }
                        return resp;
                    }
                    
                }, app));
                break;
            case 7:
                app.setStage(new EnterTextStage(this, this, "Enter Supplier Address BuildNum", new SetField() {
                    @Override
                    public String set(String data) {
                        String resp = "ok";
                        if (data.matches("[0-9]+")) {
                            int codeInt = Integer.valueOf(data);
                            selectedSup.getSupAddress().setBldNum(codeInt);
                        } else {
                            resp = "Code must be a number, please enter another.";
                        }
                        return resp;
                    }
                    
                }, app));
                break;
            case 8:
                app.setStage(new EnterTextStage(this, this, "Enter Address Street", new SetField() {
                    @Override
                    public String set(String data) {
                        selectedSup.getSupAddress().setBldStreet(data);
                        return "ok";
                    }
                }, app));
                break;
            case 9:
                app.setStage(new EnterTextStage(this, this, "Enter Address Town", new SetField() {
                    @Override
                    public String set(String data) {
                        selectedSup.getSupAddress().setBldTown(data);
                        return "ok";
                    }
                }, app));
                break;
            case 10:
                app.setStage(new EnterTextStage(this, this, "Enter Address Pcode", new SetField() {
                    @Override
                    public String set(String data) {
                        selectedSup.getSupAddress().setBldPcode(data);
                        return "ok";
                    }
                }, app));
                break;
            case 11:
                app.setStage(new EnterTextStage(this, this, "Enter Address Country", new SetField() {
                    @Override
                    public String set(String data) {
                        selectedSup.getSupAddress().setBldCountry(data);
                        return "ok";
                    }
                }, app));
                break;
            case 12:
                app.setStage(new SupplierManagmentStage(app));
                break;
            default:
                app.setStage(this);
        }
    }
    
}
