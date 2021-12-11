package com.example.hotelmanagementsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "service_reservation")
public class ServiceReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private  long serviceId;
    private long userId;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
