package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

public class UserBasket {
    public final List<BasketItem> basketItems;

    private final int totalCost;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.totalCost = basketItems.stream().mapToInt(x -> x.getProduct().getPrice() * x.getQuantity()).sum();
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return totalCost == that.totalCost && Objects.equals(basketItems, that.basketItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItems, totalCost);
    }
}

