package com.github.bragagustavo.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Embedded // Sinaliza que a entidade foi embutida
    private PersonalData personalData;

    public Client() {
    }

    public Client(String name, String cpf) {
        this.personalData = new PersonalData(name, cpf);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return personalData.getName();
    }

    public String getCpf() {
        return this.personalData.getCpf();
    }
}
