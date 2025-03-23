package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.util.StorageFixture;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyStorageService_whenTrySearchAnyString_thenReturnEmptyList() {
        List<SearchResult> result = searchService.search("Что угодно");

        Assertions.assertEquals(Collections.<SearchResult>emptyList(), result);
    }

    @Test
    public void givenNoEmptyStorageService_whenTrySearchRelevantString_THenReturnListWithResult() {

        storageService = new StorageService();
        searchService = new SearchService(storageService);
        storageService.addProduct(StorageFixture.getProduct());
        Product searchingProduct = storageService.getAnyProduct();
        List<SearchResult> result = searchService.search(searchingProduct.getName());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(searchingProduct.getId(), result.get(0).getId());

        storageService.addArticle(StorageFixture.getArticle());
        Article searchingArticle = storageService.getAnyArticle();
        result = searchService.search(searchingArticle.getName());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(searchingArticle.getId(), result.get(0).getId());
    }

    @Test
    public void givenNoEmptyStorageService_whenTrySearchNoRelevantString() {
        storageService = Mockito.mock(StorageService.class);
        searchService = Mockito.mock(SearchService.class);
        storageService.addProduct(StorageFixture.getProduct());
        List<SearchResult> result = searchService.search("Что угодно.");
        Assertions.assertTrue(result.isEmpty());


    }

}
