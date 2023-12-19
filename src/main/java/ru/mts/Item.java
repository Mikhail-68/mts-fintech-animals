package ru.mts;

/**
 * Класс товара, содержащий его свойства
 */
public class Item {

    // Количество товара
    private int count;

    // Цена за одну единицу товара
    private double price;

    // Скидка на товар (в процентах)
    private double discount;

    public Item(int count, double price, double discount) {
        this.count = count;
        this.price = price;
        this.discount = discount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
