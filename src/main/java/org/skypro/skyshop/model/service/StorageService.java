package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.exeptions.NoSuchProductException;
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


    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(storageProduct.get(id));
    }

    public Product getAnyProduct() {

        return storageProduct.values().stream().findAny().get();
    }

    public Article getAnyArticle() {

        return storageArticle.values().stream().findAny().get();
    }

    public void checkProductExisting(UUID id) {
        if (storageProduct.containsKey(id)) {
            return;
        } else throw new NoSuchProductException();
    }


}
