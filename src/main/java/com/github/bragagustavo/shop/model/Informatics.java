/**
 * @author :Gustavo
 * Date :10/03/2023
 * Time :17:01
 * Project Name :JPA e Hibernate
 **/
package com.github.bragagustavo.shop.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "informatics")
public class Informatics extends Product {

    private String brand;
    private Integer model;

    public Informatics(String brand, Integer model) {
        this.brand = brand;
        this.model = model;
    }

    public Informatics() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String author) {
        this.brand = author;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer numberOfPages) {
        this.model = numberOfPages;
    }
}
