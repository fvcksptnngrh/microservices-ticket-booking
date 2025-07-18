package com.keretaapi.user_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // --- CONSTRUCTORS ---
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // --- GETTERS AND SETTERS (DITAMBAHKAN MANUAL) ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}