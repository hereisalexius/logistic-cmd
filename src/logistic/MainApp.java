package logistic;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logistic.stage.ConsoleStage;
import logistic.stage.MainStage;

public class MainApp {

    //Имя корневого файла для хранения данных
    public static final String DATA_FILE = "data.ser";

    // Флаг на состояние работы приложения
    private boolean isExecuting;
    //Контейнер данных
    private DataContainer data;
    //Сканер ввода параметров
    private Scanner scanner;

    public MainApp() {
        isExecuting = true;
        data = new DataContainer();
        scanner = new Scanner(System.in);
    }

    /*
    Запуск приложение, открываем форму MainStage
     */
    public void start() {
        while (isExecuting) {
            setStage(new MainStage(this));
        }
    }

    /*
    Закрытие приложениея(прирывание цикла впрослушивания ввода)
     */
    public void close() {
        isExecuting = false;
    }

    /*
        очистка консоли
     */
    public void clear() {

        try {
            //https://stackoverflow.com/questions/10241217/how-to-clear-console-in-java
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Сохранение(Сериализация) данных
     */
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream(DATA_FILE, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
    }

    /*
    Выгрузка(Десериализация) данных
     */
    public void load() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(DATA_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (DataContainer) ois.readObject();
        ois.close();
    }

    //Запросс на нажатие Enter для подтверждения
    //Используется в нескольких классах от ConsoleStage
    public void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Metod перехода на другую "форму"
        
     */
    public void setStage(ConsoleStage stage) {
        clear();
        stage.printStage();
    }

//Геттер слушателя ввода
    public Scanner getScanner() {
        return scanner;
    }

//Геттер доступа к даным
    public DataContainer getData() {
        return data;
    }

    //Запуск Java приложения
    public static void main(String[] args) {
        new MainApp().start();
    }

}
