package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {

    public int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        try {
            inputCheck(price);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return;
        }
        this.price = price;

    }

    private static void inputCheck(int price) {
        if (price < 1)
            throw new IllegalArgumentException(" Цена должна быть 1 или выше. Введенное значение - " + price + ". Продукт не создан.");
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getName() + " : " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct that = (SimpleProduct) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }
}
