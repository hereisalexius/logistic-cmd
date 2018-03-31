package logistic.stage.prod;

import logistic.MainApp;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.MainStage;

public class ProductManagementStage extends ConsoleStage {

    public ProductManagementStage(MainApp app) {
        super(app);
    }

    @Override
    public void printStage() {
        printMenu();
        listenCommands();
    }

    private void printMenu() {
        System.out.println("[Products Manager]");
        System.out.println("1)Add Product");
        System.out.println("2)Edit Product(selection by code)");
        System.out.println("3)Remove Product(selection by code)");
        System.out.println("4)Find Products");
        System.out.println("5)Print All Products");
        System.out.println("6)Back to Main Menu");
        System.out.println();
        System.out.println("Enter command code:");
    }

    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new AddProductStage(app));
                break;
            case 2:
                app.setStage(new SelectProductStage(false, app));
                break;
            case 3:
                app.setStage(new SelectProductStage(true, app));
                break;
            case 4:
                app.setStage(new FindProductStage(app));
                break;
            case 5:
                app.setStage(new PrintProductsStage(app, app.getData().getProducts(), this));
                break;
            case 6:
                app.setStage(new MainStage(app));
                break;
            default:
                app.setStage(this);
        }
    }
    


}
