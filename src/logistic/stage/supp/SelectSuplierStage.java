package logistic.stage.supp;

import logistic.MainApp;
import logistic.entities.Supplier;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.SetField;

public class SelectSuplierStage extends ConsoleStage {

    public SelectSuplierStage(MainApp app) {
        super(app);
    }

    @Override
    public void printStage() {
        SupplierStage next = new SupplierStage(app);

        EnterTextStage code;
        code = new EnterTextStage(new SupplierManagmentStage(app), next, "Enter Specific Supplier Code ", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    final Supplier p = getSupplierToEdit(codeInt);
                    if (p == null) {
                        resp = "Product doesnt exist, please enter another.";
                    } else {
                        ((SupplierStage) next).setSelectedSup(p);
                    }
                } else {
                    resp = "Code must be a number, please enter another.";
                }
                return resp;
            }

            private Supplier getSupplierToEdit(int code) {
                for (Supplier s : app.getData().getSuppliers()) {
                    if (s.getSupCode() == code) {
                        return s;
                    }
                }
                return null;
            }
        }, app);

        app.setStage(code);
    }

}
