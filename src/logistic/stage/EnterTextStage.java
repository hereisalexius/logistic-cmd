package logistic.stage;

import logistic.MainApp;
/*
    Форма ввода данных (по умолчанию текста)
    
    Дополнительную валидацию, и присвоение данных можно настроить через интерфейс SetField
*/
public class EnterTextStage extends ConsoleStage {

    //Форма - куда если отмена
    private final ConsoleStage exitStage;
    //Форма - куда дальше
    private final ConsoleStage nextStage;
    //Сообщение 
    private final String title;
    //Операция
    private final SetField setAction;

    public EnterTextStage(ConsoleStage exitStage, ConsoleStage nextStage, String title, SetField setAction, MainApp app) {
        super(app);
        this.exitStage = exitStage;
        this.nextStage = nextStage;
        this.title = title;
        this.setAction = setAction;
    }



    @Override
    public void printStage() {
        System.out.println("Type \"exit\" to cancel adding.");
        System.out.print(title + " :");
        String data = app.getScanner().next();
        if (data.contentEquals("exit")) {
            app.setStage(exitStage);
        } else if (data.length() > 0) {
            String response = setAction.set(data);
            if (response.contentEquals("ok")) {
                app.setStage(nextStage);
            } else {
                System.out.println(response);
                app.pressAnyKeyToContinue();
                app.setStage(this);
            }
        } else {
            System.out.println("Make cannot be empty, please enter another.\n");
            app.pressAnyKeyToContinue();
            app.setStage(this);
        }
    }

}
