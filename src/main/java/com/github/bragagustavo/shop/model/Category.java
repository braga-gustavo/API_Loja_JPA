package com.github.bragagustavo.shop.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    @EmbeddedId
    private CategoryID id;

    public Category(String name) {
        this.id = new CategoryID(name, "xpto");

    }

    public Category() {
    }

    public String getName() {
        return this.id.getName();
    }

}
