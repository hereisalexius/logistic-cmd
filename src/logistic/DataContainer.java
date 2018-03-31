package logistic;

import logistic.entities.*;
import java.util.*;
import java.io.*;

/*
    Подготовленнный класс контейнер для сериализации объектов
    Класс(как и классы сущностей: Address, Product, SupRegion, Supplier) 
    организованы в соответствии спецификации класов для сериализации
        1) Имплементируют интерфейс Serializable
        2) Содержат конструктор без параметров
        3) Содержат полный набор сеттеров и геттеров(ко всем полям) 
 */
public class DataContainer implements Serializable {

    //Хранилище поставщиков
    private List<Supplier> suppliers;
    //Хранилище товаров
    private List<Product> products;

    public DataContainer() {
        suppliers = new ArrayList<>();
        products = new ArrayList<>();
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
