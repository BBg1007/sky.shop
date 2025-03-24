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
        Product searchingProduct = StorageFixture.getProduct();
        Article searchingArticle = StorageFixture.getArticle();
        Mockito.when(storageService.toSearchable()).thenReturn(List.of(searchingProduct, searchingArticle));

        List<SearchResult> result = searchService.search(searchingProduct.getName());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(searchingProduct.getId(), result.get(0).getId());

        result = searchService.search(searchingArticle.getName());
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(searchingArticle.getId(), result.get(0).getId());
    }

    @Test
    public void givenNoEmptyStorageService_whenTrySearchNoRelevantString() {
        Mockito.when(storageService.toSearchable()).thenReturn(List.of(StorageFixture.getProduct()));
        List<SearchResult> result = searchService.search("Что угодно.");
        Assertions.assertTrue(result.isEmpty());
    }

}
