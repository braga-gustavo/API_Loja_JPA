/**
 * @author :Gustavo
 * Date :10/03/2023
 * Time :16:31
 * Project Name :JPA e Hibernate
 **/
package com.github.bragagustavo.shop.model;

import javax.persistence.Embeddable;

@Embeddable //Sinaliza que a classe pode ser embutida em outra
public class PersonalData {

    private String name;
    private String cpf;

    public PersonalData() {
    }

    public PersonalData(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
