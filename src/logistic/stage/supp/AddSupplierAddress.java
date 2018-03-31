package logistic.stage.supp;

import logistic.MainApp;
import logistic.entities.Address;
import logistic.entities.Supplier;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.MessageStage;
import logistic.stage.SetField;

public class AddSupplierAddress extends ConsoleStage {

    private final Supplier supplierToAdd;
    private Address addressToAdd;

    public AddSupplierAddress(Supplier supplierToAdd, MainApp app) {
        super(app);
        this.supplierToAdd = supplierToAdd;
        addressToAdd = new Address();
    }

    @Override
    public void printStage() {

        MessageStage msg = new MessageStage("Supplier was add!", new SupplierManagmentStage(app), app);

        EnterTextStage country = new EnterTextStage(new SupplierManagmentStage(app), msg, "Enter Address Country", new SetField() {
            @Override
            public String set(String data) {
                addressToAdd.setBldCountry(data);
                supplierToAdd.setSupAddress(addressToAdd);
                app.getData().getSuppliers().add(supplierToAdd);
                return "ok";
            }
        }, app);

        EnterTextStage pcode = new EnterTextStage(new SupplierManagmentStage(app), country, "Enter Address Pcode", new SetField() {
            @Override
            public String set(String data) {
                addressToAdd.setBldPcode(data);
                return "ok";
            }
        }, app);

        EnterTextStage town = new EnterTextStage(new SupplierManagmentStage(app), pcode, "Enter Address Town", new SetField() {
            @Override
            public String set(String data) {
                addressToAdd.setBldTown(data);
                return "ok";
            }
        }, app);

        EnterTextStage strt = new EnterTextStage(new SupplierManagmentStage(app), town, "Enter Address Street", new SetField() {
            @Override
            public String set(String data) {
                addressToAdd.setBldStreet(data);
                return "ok";
            }
        }, app);

        EnterTextStage code = new EnterTextStage(new SupplierManagmentStage(app), strt, "Enter Supplier Address BuildNum", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    
                        addressToAdd.setBldNum(codeInt);
         
                } else {
                    resp = "Code must be a number, please enter another.";
                }
                return resp;
            }
        }, app);
        app.setStage(code);
    }
}
