package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    int basePrice;
    int discountValue;

    public DiscountedProduct(String name, int basePrice, int discountValue, UUID id) {
        super(name, id);
        if (basePrice < 1) {
            throw new IllegalArgumentException(" Цена должна быть 1 или выше. Введенное значение - " + basePrice + ". Продукт не создан.");
        }
        if (discountValue < 0 || discountValue > 100) {
            throw new IllegalArgumentException(" Процент должен быть числом от 0 до 100. Введенное значение - " + discountValue + ". Продукт не создан.");
        }
        this.basePrice = basePrice;
        this.discountValue = discountValue;
    }


    @Override
    public boolean isSpecial() {
        return true;
    }


    @Override
    public int getPrice() {
        return basePrice * discountValue / 100;
    }

    @Override
    public String toString() {
        return getName() + " : " + getPrice() + "(" + discountValue + "%)";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return basePrice == that.basePrice && discountValue == that.discountValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), basePrice, discountValue);
    }
}
