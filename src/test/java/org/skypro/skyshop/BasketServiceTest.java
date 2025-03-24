package org.skypro.skyshop;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.util.StorageFixture;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void tryAddNoExistingProduct_thenThrowException() {
        UUID id = UUID.randomUUID();
        try {
            basketService.addToBasket(id);
        } catch (Exception e) {
        }

        assertThrows(NoSuchProductException.class, () -> basketService.addToBasket(id));
    }

    @Test
    public void addExistingProductInProductBasket() {

        Product product = StorageFixture.getProduct();
        UUID id = product.getId();
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addToBasket(id);
        Mockito.verify(productBasket, Mockito.times(1)).addProduct(id);
    }

    @Test
    public void tryGetUserBasket_whenProductBasketEmpty() {
        UserBasket userBasket = basketService.getUserBasket();
        Assertions.assertEquals(0, userBasket.getTotalCost());
        Assertions.assertEquals(Collections.<BasketItem>emptyList(), userBasket.getBasketItems());

    }

    @Test
    public void whenGetUserBasket_thenReturnRequiredUserBasket() {

        Product product = StorageFixture.getProduct();
        UUID id = product.getId();
        List<BasketItem> items = List.of(new BasketItem(product, 1));

        Mockito.when(productBasket.getProductBasket()).thenReturn(Map.of(product.getId(), 1));
        Mockito.when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertEquals(product.getPrice(), userBasket.getTotalCost());
        Assertions.assertEquals(new UserBasket(items), userBasket);
    }


}
