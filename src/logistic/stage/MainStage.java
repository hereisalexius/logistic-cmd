package logistic.stage;

import logistic.MainApp;
import logistic.stage.prod.ProductManagementStage;
import logistic.stage.supp.SupplierManagmentStage;

/*
    Главная форма
*/
public class MainStage extends ConsoleStage {
    
    public MainStage(MainApp app) {
        super(app);
    }
    
    @Override
    public void printStage() {
        printMenu();
        listenCommands();
    }
    
    private void printMenu() {
        System.out.println("[Main Menu]");
        System.out.println("1)Load Data");
        System.out.println("2)Save Data");
        System.out.println("3)Products Manager");
        System.out.println("4)Suppliers Manager");
        System.out.println("5)Exit Application");
        System.out.println();
        System.out.print("Enter command code:");
    }
    
    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new LoadStage(app));
                break;
            case 2:
                app.setStage(new SaveStage(app));
                break;
            case 3:
                app.setStage(new ProductManagementStage(app));
                break;
            case 4:
                app.setStage(new SupplierManagmentStage(app));
                break;
            case 5:
                app.close();
                break;
            default:
                app.setStage(this);
        }
    }
    
}
