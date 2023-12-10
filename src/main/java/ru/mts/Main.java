package ru.mts;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item(10, 1301, 0.75);
        Item item2 = new Item(41, 999, 42.575);
        Item item3 = new Item(32, 161, 59.1);

        calculate(item1);
        calculate(item2);
        calculate(item3);
    }

    private static void calculate(Item item) {
        double sumWithoutDiscount = item.getPrice() * item.getCount();
        System.out.println("Сумма без скидки: " + Math.round(sumWithoutDiscount * 100) / (double) 100);
        double sumWithDiscount = sumWithoutDiscount - (sumWithoutDiscount * (item.getDiscount() / 100));
        System.out.println("Сумма со скидкой: " + Math.round(sumWithDiscount * 100) / (double) 100);
    }
}