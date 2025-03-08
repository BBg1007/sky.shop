package org.skypro.skyshop.model.basket;

import java.util.List;

public  class UserBasket {
    public final List<BasketItem> basketItems;

    private final int totalCost;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.totalCost = basketItems.stream().mapToInt(x->x.getProduct().getPrice()*x.getQuantity()).sum();
    }

    public int getTotalCost() {
        return totalCost;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }
}
