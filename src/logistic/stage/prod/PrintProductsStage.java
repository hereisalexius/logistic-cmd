package logistic.stage.prod;

import java.util.List;
import logistic.MainApp;
import logistic.entities.Product;
import logistic.stage.ConsoleStage;

public class PrintProductsStage extends ConsoleStage {

    private final List<Product> productsToPrint;
    private final ConsoleStage requestOwner;

    public PrintProductsStage(MainApp app, List<Product> productsToPrint, ConsoleStage requestOwner) {
        super(app);
        this.productsToPrint = productsToPrint;
        this.requestOwner = requestOwner;
    }

    @Override
    public void printStage() {
        System.out.println("[Print Products]");
        System.out.println("Code\tMake\tModel\tPrice\tQtyAvailable\tDiscountinued");
        System.out.println("");
        for (Product p : productsToPrint) {
            System.out.println(p);
        }
        System.out.println("");
        app.pressAnyKeyToContinue();
        app.setStage(requestOwner);
    }

}
