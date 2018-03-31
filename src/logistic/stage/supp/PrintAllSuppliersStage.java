package logistic.stage.supp;

import logistic.MainApp;
import logistic.entities.Supplier;
import logistic.stage.ConsoleStage;

public class PrintAllSuppliersStage extends ConsoleStage {
    
    public PrintAllSuppliersStage(MainApp app) {
        super(app);
    }
    
    @Override
    public void printStage() {
        System.out.println("[Print Suppliers]");
        System.out.println("Code\tName\tRegion");
        for (Supplier s : app.getData().getSuppliers()) {
            System.out.println(s);
        }
        app.pressAnyKeyToContinue();
        app.setStage(new SupplierManagmentStage(app));
    }
    
}
