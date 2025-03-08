package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService() {
        this.storageProduct = new TreeMap<UUID, Product>();
        this.storageArticle = new TreeMap<UUID, Article>();

        createTestData();

    }

    public void addProduct(Product product) {
        storageProduct.put(product.getId(), product);
    }

    public void addArticle(Article article) {
        storageArticle.put(article.getId(), article);
    }


    public Collection<Product> getAllProducts() {
        return new ArrayList<Product>(storageProduct.values());
    }

    public Collection<Article> getAllArticles() {
        return new ArrayList<Article>(storageArticle.values());
    }

    public Collection<Searchable> toSearchable() {

        List<Searchable> searchables = new ArrayList<>();

        searchables.addAll(this.storageArticle.values());
        searchables.addAll(this.storageProduct.values());

        return searchables;

    }


    private void createTestData() {


        Product product = new SimpleProduct("Вилка", 6000, UUID.randomUUID());
        Product product1 = new DiscountedProduct("Вилка", 6800, 6, UUID.randomUUID());
        Product product2 = new FixPriceProduct("Цепь", UUID.randomUUID());
        Product product3 = new DiscountedProduct("Звездочка", 2300, 9, UUID.randomUUID());
        Product product4 = new SimpleProduct("Рама", 15000, UUID.randomUUID());

        addProduct(product);
        addProduct(product1);
        addProduct(product2);
        addProduct(product3);
        addProduct(product4);

        Article article = new Article("Модели велосипедов для не высоких людей", "Горный велосипед PLANDY год 2024, рама 14, на рост 140-175см или Lorak Glory 100, фиолетовый, 26 дюймов, 17 размер рамы под рост 160-175 см", UUID.randomUUID());
        Article article1 = new Article("Виды вилок для велосипеда", "Жесткие и Амортизационные (Пружинные, Воздушные, Воздушно-масляные и Пружинно-масляные", UUID.randomUUID());
        Article article2 = new Article("Типы рам велосипедов", "Жёсткий (ригидный), Хардтейл, Двухподвес.", UUID.randomUUID());

        addArticle(article);
        addArticle(article1);
        addArticle(article2);





    }


    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageProduct.get(id));
    }


}
