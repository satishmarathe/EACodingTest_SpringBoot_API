package com.ea.festivalservice.model;

public class Festival {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;

    public Festival() {
    }

    public Festival(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Festival(Integer id, String name, Integer quantity, Integer version) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.version = version;
    }

    public Festival(Integer id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", version=" + version +
                '}';
    }
}