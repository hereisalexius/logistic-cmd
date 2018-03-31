package logistic.stage;

import logistic.MainApp;

/*
    Форма для вывода сообщений
*/

public class MessageStage extends ConsoleStage {

    private final String msg;
    private final ConsoleStage exitStage;

    public MessageStage(String msg, ConsoleStage exitStage, MainApp app) {
        super(app);
        this.msg = msg;
        this.exitStage = exitStage;
    }

    @Override
    public void printStage() {
        System.out.println(msg);
        app.pressAnyKeyToContinue();
        app.setStage(exitStage);
    }

}
