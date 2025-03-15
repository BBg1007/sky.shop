package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;


    //По скольку в данном задании не нужно изменять цену товара - присвоил модификатор final.

    public Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Не указано название продукта. Продукт не создан.");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public abstract boolean isSpecial();


    public abstract int getPrice();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return name;
    }

    @Override
    public String getType() {
        return "PRODUCT";
    }

    @Override
    public String toString() {
        return "Товар: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
