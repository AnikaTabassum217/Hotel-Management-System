package com.example.hotelmanagementsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "dining_reservatio")
public class DiningReservaton {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private  long userId;
    private String userName;
    private String mobileNumber;
    private long diningId;
    private int payment;
    private String startTime;
    private String endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getDiningId() {
        return diningId;
    }

    public void setDiningId(long diningId) {
        this.diningId = diningId;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
