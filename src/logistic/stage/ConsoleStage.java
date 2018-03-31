package logistic.stage;

import logistic.MainApp;

/*
    Базовый класс для "Форм" консоли
 */
public abstract class ConsoleStage {

    protected final MainApp app;

    public ConsoleStage(MainApp app) {
        this.app = app;
    }

    //Метод в котором происходит вывод данных в консоль для текущей формы
    //ДЛя отрисовки используется метод в MainApp, setStage( )
    public abstract void printStage();
}
