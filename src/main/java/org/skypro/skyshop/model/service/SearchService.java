package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String search) {

        return this.storageService.toSearchable().stream()
                .filter(searchable -> searchable.searchTerm().toLowerCase().contains(search.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .toList();

    }
}
