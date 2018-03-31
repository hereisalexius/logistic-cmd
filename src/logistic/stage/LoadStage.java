package logistic.stage;

import logistic.MainApp;

public class LoadStage extends ConsoleStage {

    public LoadStage(MainApp app) {
        super(app);
    }

    @Override
    public void printStage() {
        try {
            app.load();
            System.out.println("Data loaded!");
        } catch (Exception ex) {
            System.out.println("Data didn`t loaded!");
        } finally {
            app.pressAnyKeyToContinue();
            app.setStage(new MainStage(app));
        }
    }

}
