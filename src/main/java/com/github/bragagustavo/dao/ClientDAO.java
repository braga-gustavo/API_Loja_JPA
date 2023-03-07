package com.github.bragagustavo.dao;

import com.github.bragagustavo.shop.model.Client;

import javax.persistence.EntityManager;

public class ClientDAO {

    private EntityManager entityManager;

    public ClientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void register(Client client){
        this.entityManager.persist(client);
    }

    public Client findById(Long id){
        return entityManager.find(Client.class, id);
    }

 }
