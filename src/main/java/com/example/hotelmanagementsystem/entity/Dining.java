package com.example.hotelmanagementsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "dining")
public class Dining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String size;
    private  String status;
    private String available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
