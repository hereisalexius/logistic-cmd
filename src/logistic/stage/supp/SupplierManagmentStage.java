package logistic.stage.supp;

import logistic.MainApp;
import logistic.stage.ConsoleStage;
import logistic.stage.MainStage;
import logistic.stage.prod.AddProductStage;
import logistic.stage.prod.FindProductStage;
import logistic.stage.prod.PrintProductsStage;
import logistic.stage.prod.SelectProductStage;

public class SupplierManagmentStage extends ConsoleStage {
    
    public SupplierManagmentStage(MainApp app) {
        super(app);
    }
    
    @Override
    public void printStage() {
        printMenu();
        listenCommands();
    }
    
    private void printMenu() {
        System.out.println("[Suppliers Manager]");
        System.out.println("1)Add Supplier");
        System.out.println("2)Print All Suppliers");
        System.out.println("3)Select Specific Supplier(selection by code)");
        System.out.println("4)Back to Main Menu");
        System.out.println();
        System.out.println("Enter command code:");
    }
    
    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new AddSupplierStage(app));
                break;
            case 2:
                app.setStage(new PrintAllSuppliersStage(app));
                break;
            case 3:
                app.setStage(new SelectSuplierStage(app));
                break;
            case 4:
                app.setStage(new MainStage(app));
                break;
            default:
                app.setStage(this);
        }
    }
    
}
