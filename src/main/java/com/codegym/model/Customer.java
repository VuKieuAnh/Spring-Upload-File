package com.codegym.model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Customer(String name, String email, String address, String image) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public Customer(int id, String name, String email, String address, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public Customer() {
    }

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
