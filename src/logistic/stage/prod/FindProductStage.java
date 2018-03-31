package logistic.stage.prod;

import java.util.ArrayList;
import java.util.List;
import logistic.MainApp;
import logistic.entities.Product;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;

public class FindProductStage extends ConsoleStage {
    
    public FindProductStage(MainApp app) {
        super(app);
    }
    
    @Override
    public void printStage() {
        printMenu();
        listenCommands();
    }
    
    private void printMenu() {
        System.out.println("[Products Find]");
        System.out.println("1)By Price Range");
        System.out.println("2)By Discountinued");
        System.out.println("3)By Containing Text");
        System.out.println("4)Back to Products Manager");
        System.out.println();
        System.out.println("Enter command code:");
    }
    
    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new FindByPriceRange(app));
                break;
            case 2:
                app.setStage(new FindByDiscountinued(app));
                break;
            case 3:
                app.setStage(new FindByText(app));
                break;
            case 4:
                app.setStage(new ProductManagementStage(app));
                break;
            default:
                app.setStage(this);
        }
    }
    
    private class FindByPriceRange extends ConsoleStage {
        
        private final List<Product> result;
        
        public FindByPriceRange(MainApp app) {
            super(app);
            result = new ArrayList<>();
        }
        
        @Override
        public void printStage() {
            
            PrintProductsStage pps = new PrintProductsStage(app, result, new FindProductStage(app));
            EnterTextStage priceTo = new EnterTextStage(new FindProductStage(app), pps, "Enter Product Price[To]", (String data) -> {
                if (data.matches("[0-9]+")) {
                    int dataInt = Integer.valueOf(data);
                    for (Product p : app.getData().getProducts()) {
                        int priceInt = Integer.valueOf(p.getProPrice());
                        if (priceInt > dataInt) {
                            result.remove(p);
                        }
                    }
                    return "ok";
                } else {
                    return "Price must be numeric!";
                }
            }, app);
            EnterTextStage priceFrom = new EnterTextStage(new FindProductStage(app), priceTo, "Enter Product Price[From]", (String data) -> {
                if (data.matches("[0-9]+")) {
                    int dataInt = Integer.valueOf(data);
                    for (Product p : app.getData().getProducts()) {
                        int priceInt = Integer.valueOf(p.getProPrice());
                        if (priceInt >= dataInt) {
                            result.add(p);
                        }
                    }
                    
                    return "ok";
                } else {
                    return "Price must be numeric!";
                }
            }, app);
            app.setStage(priceFrom);
        }
        
    }
    
    private class FindByDiscountinued extends ConsoleStage {
        
        private final List<Product> result;
        
        public FindByDiscountinued(MainApp app) {
            super(app);
            result = new ArrayList<>();
        }
        
        @Override
        public void printStage() {
            
            PrintProductsStage pps = new PrintProductsStage(app, result, new FindProductStage(app));
            EnterTextStage disc = new EnterTextStage(new FindProductStage(app), pps, "Enter Product discountinued[t/f]", (data) -> {
                if (data.contentEquals("t")) {
                    for (Product p : app.getData().getProducts()) {
                        if (p.isProDiscountinued()) {
                            result.add(p);
                        }
                    }
                    return "ok";
                } else if (data.contentEquals("f")) {
                    for (Product p : app.getData().getProducts()) {
                        if (!p.isProDiscountinued()) {
                            result.add(p);
                        }
                    }
                    return "ok";
                } else {
                    return "Input data must be t - true ore f - false.";
                }
            }, app);
            app.setStage(disc);
        }
    }
    
    private class FindByText extends ConsoleStage {
        
        private final List<Product> result;
        
        public FindByText(MainApp app) {
            super(app);
            result = new ArrayList<>();
        }
        
        @Override
        public void printStage() {
            PrintProductsStage pps = new PrintProductsStage(app, result, new FindProductStage(app));
            EnterTextStage txt = new EnterTextStage(new FindProductStage(app), pps, "Enter text", (String data) -> {
                for (Product p : app.getData().getProducts()) {
                    if (p.toString().contains(data)) {
                        result.add(p);
                    }
                }
                return "ok";
            }, app);
            app.setStage(txt);
        }
        
    }
}
