/**
 * @author :Gustavo
 * Date :11/03/2023
 * Time :10:10
 * Project Name :JPA e Hibernate
 **/
package com.github.bragagustavo.shop.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoryID implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String type;

    public CategoryID(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public CategoryID() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
