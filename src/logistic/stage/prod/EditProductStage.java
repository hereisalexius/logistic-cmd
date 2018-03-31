package logistic.stage.prod;

import logistic.MainApp;
import logistic.entities.Product;
import logistic.stage.ConsoleStage;
import logistic.stage.EnterTextStage;
import logistic.stage.SetField;

public class EditProductStage extends ConsoleStage {

    private Product selectedProduct;

    public EditProductStage(MainApp app) {
        super(app);
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        System.err.println(this.selectedProduct);
    }

    @Override
    public void printStage() {
        printMenu();
        listenCommands();
    }

    private void printMenu() {
        System.out.println("[Product Edit]");
        System.out.println("1)Set Code[ " + selectedProduct.getProCode() + " ]");
        System.out.println("2)Set Make[ " + selectedProduct.getProMake() + " ]");
        System.out.println("3)Set Model[ " + selectedProduct.getProModel() + " ]");
        System.out.println("4)Set Price[ " + selectedProduct.getProPrice() + " ]");
        System.out.println("5)Set QtyAvailable[ " + selectedProduct.getProQtyAvailable() + " ]");
        System.out.println("6)Set Discountinued[ " + selectedProduct.isProDiscountinued() + " ]");
        System.out.println("7)Back To Products Manager");
        System.out.println();
        System.out.println("Enter command code:");
    }

    private void listenCommands() {
        int val = app.getScanner().nextInt();
        switch (val) {
            case 1:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product Code", new SetField() {
                    @Override
                    public String set(String data) {
                        String resp = "ok";
                        if (data.matches("[0-9]+")) {
                            int codeInt = Integer.valueOf(data);
                            if (!isCodeExists(codeInt)) {
                                selectedProduct.setProCode(codeInt);
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
                }, app));
                break;
            case 2:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product Make", (String data) -> {
                    selectedProduct.setProMake(data);
                    return "ok";
                }, app));
                break;
            case 3:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product Model", (String data) -> {
                    selectedProduct.setProModel(data);
                    return "ok";
                }, app));
                break;
            case 4:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product Price", (String data) -> {
                    if (data.matches("[0-9]+")) {
                        selectedProduct.setProPrice(data);
                        return "ok";
                    } else {
                        return "Price must be numeric!";
                    }
                }, app));
                break;
            case 5:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product QtyAvailable", (String data) -> {
                    selectedProduct.setProQtyAvailable(data);
                    return "ok";
                }, app));
                break;
            case 6:
                app.setStage(new EnterTextStage(new ProductManagementStage(app), this, "Enter Product discountinued[t/f]", (data) -> {
                    if (data.contentEquals("t")) {
                        selectedProduct.setProDiscountinued(true);
                        return "ok";
                    } else if (data.contentEquals("f")) {
                        selectedProduct.setProDiscountinued(false);
                        return "ok";
                    } else {
                        return "Input data must be t - true ore f - false.";
                    }
                }, app));
                break;
            case 7:
                app.setStage(new ProductManagementStage(app));
                break;
            default:
                app.setStage(this);
        }
    }

}
