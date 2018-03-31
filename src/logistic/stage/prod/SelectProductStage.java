package logistic.stage.prod;

import logistic.MainApp;
import logistic.entities.Product;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.MessageStage;
import logistic.stage.SetField;

public class SelectProductStage extends ConsoleStage {

    private Product selectedProduct;
    private final boolean remove;

    public SelectProductStage(boolean remove, MainApp app) {
        super(app);
        this.remove = remove;
    }

    @Override
    public void printStage() {
        final ConsoleStage edit = new EditProductStage(app);
        ConsoleStage removeS = new MessageStage("Product was Removed!", new ProductManagementStage(app), app);
        ConsoleStage next = edit;
        if (remove) {
            next = removeS;
        }

        EnterTextStage code;
        code = new EnterTextStage(new ProductManagementStage(app), next, "Enter Editing Product Code ", new SetField() {
            @Override
            public String set(String data) {
                String resp = "ok";
                if (data.matches("[0-9]+")) {
                    int codeInt = Integer.valueOf(data);
                    final Product p = getProductToEdit(codeInt);
                    if (p == null) {
                        resp = "Product doesnt exist, please enter another.";
                    } else {
                        selectedProduct = p;
                        if (remove) {
                            app.getData().getProducts().remove(p);
                        } else {
                            ((EditProductStage) edit).setSelectedProduct(p);
                        }
                    }
                } else {
                    resp = "Code must be a number, please enter another.";
                }
                return resp;
            }

            private Product getProductToEdit(int code) {
                for (Product s : app.getData().getProducts()) {
                    if (s.getProCode() == code) {
                        return s;
                    }
                }
                return null;
            }
        }, app);

        app.setStage(code);
    }

}
