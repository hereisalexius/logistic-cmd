package logistic.stage.supp;

import logistic.MainApp;
import logistic.entities.Product;
import logistic.entities.SupRegion;
import logistic.entities.Supplier;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.SetField;

public class AddSupplierStage extends ConsoleStage {

    private Supplier supplierToAdd;

    public AddSupplierStage(MainApp app) {
        super(app);
        supplierToAdd = new Supplier();
    }

    @Override
    public void printStage() {

        AddSupplierAddress addrs = new AddSupplierAddress(supplierToAdd, app);

        EnterTextStage region = new EnterTextStage(new SupplierManagmentStage(app), addrs, "Enter Supplier Region[ 1 - UK, 2 - EU, 3 - OTHER]", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    switch (codeInt) {
                        case 1:
                            supplierToAdd.setSupRegion(SupRegion.UNITED_KINGDOM);
                            break;
                        case 2:
                            supplierToAdd.setSupRegion(SupRegion.EUROPE);
                            break;
                        case 3:
                            supplierToAdd.setSupRegion(SupRegion.OUTSIDE_EU);
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

        }, app);

        EnterTextStage name = new EnterTextStage(new SupplierManagmentStage(app), region, "Enter Supplier Name", new SetField() {
            @Override
            public String set(String data) {
                supplierToAdd.setSupName(data);
                return "ok";
            }
        }, app);

        EnterTextStage code = new EnterTextStage(new SupplierManagmentStage(app), name, "Enter Supplier Code", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    if (!isCodeExists(codeInt)) {
                        supplierToAdd.setSupCode(codeInt);
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

        }, app);
        app.setStage(code);
    }

}
