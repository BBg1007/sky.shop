package org.skypro.skyshop.util;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.StorageService;

import java.util.UUID;

public class StorageFixture {
    public static Product getProduct() {

        return new SimpleProduct("Вилка", 6000, UUID.randomUUID());
    }


    public static Article getArticle() {
        return new Article("Название статьи", "Текст статьи", UUID.randomUUID());
    }
}
