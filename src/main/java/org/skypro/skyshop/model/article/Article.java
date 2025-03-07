package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final String title;
    private final String text;
    private final UUID id;

    public Article(String title, String text, UUID id) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return title + text;
    }

    @JsonIgnore
    @Override
    public String getType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + System.lineSeparator() + text;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
