package logistic.stage.prod;

import logistic.MainApp;
import logistic.entities.Product;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.MessageStage;
import logistic.stage.SetField;

public class AddProductStage extends ConsoleStage {

    private final Product productToAdd;

    public AddProductStage(MainApp app) {
        super(app);
        productToAdd = new Product();
    }

    @Override
    public void printStage() {
        MessageStage msg = new MessageStage("Product was added successfully!", new ProductManagementStage(app), app);
        EnterTextStage discountinued = new EnterTextStage(new ProductManagementStage(app), msg, "Enter Product discountinued[t/f]", (data) -> {
            if (data.contentEquals("t")) {
                productToAdd.setProDiscountinued(true);
                app.getData().getProducts().add(productToAdd);
                return "ok";
            } else if (data.contentEquals("f")) {
                productToAdd.setProDiscountinued(false);
                app.getData().getProducts().add(productToAdd);
                return "ok";
            } else {
                return "Input data must be t - true ore f - false.";
            }
        }, app);

        EnterTextStage qtyAvailable = new EnterTextStage(new ProductManagementStage(app), discountinued, "Enter Product QtyAvailable", (String data) -> {
            productToAdd.setProQtyAvailable(data);
            return "ok";
        }, app);

        EnterTextStage price = new EnterTextStage(new ProductManagementStage(app), qtyAvailable, "Enter Product Price", (String data) -> {
            if (data.matches("[0-9]+")) {
                productToAdd.setProPrice(data);
                return "ok";
            } else {
                return "Price must be numeric!";
            }
        }, app);

        EnterTextStage model = new EnterTextStage(new ProductManagementStage(app), price, "Enter Product Model", (String data) -> {
            productToAdd.setProModel(data);
            return "ok";
        }, app);

        EnterTextStage make = new EnterTextStage(new ProductManagementStage(app), model, "Enter Product Make", (String data) -> {
            productToAdd.setProMake(data);
            return "ok";
        }, app);

        EnterTextStage code = new EnterTextStage(new ProductManagementStage(app), make, "Enter Product Code", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    if (!isCodeExists(codeInt)) {
                        productToAdd.setProCode(codeInt);
                    } else {
                        resp = "Code already exists, please enter another.";
                    }
                } else {
                    resp = "Code must be a number, please enter another.";
                }
                return resp;
            }

            private boolean isCodeExists(int code) {
                for (Product s : app.getData().getProducts()) {
                    if (s.getProCode() == code) {
                        return true;
                    }
                }
                return false;
            }
        }, app);

        app.setStage(code);
    }
}
