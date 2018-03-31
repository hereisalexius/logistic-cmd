package logistic.stage;

import java.io.IOException;
import logistic.MainApp;

public class SaveStage extends ConsoleStage {
    
    public SaveStage(MainApp app) {
        super(app);
    }
    
    @Override
    public void printStage() {
        try {
            app.save();
            System.out.println("Data saved!");
        } catch (IOException ex) {
            System.out.println("Data didn`t saved!");
        } finally {
            app.pressAnyKeyToContinue();
            app.setStage(new MainStage(app));
        }
    }
    
}
