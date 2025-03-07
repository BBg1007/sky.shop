package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    public String searchTerm();

    public String getType();

    public String getName();

    public UUID getId();

    default String getStringRepresentation() {
        return this.getName() + " - " + this.getType();
    }


}
