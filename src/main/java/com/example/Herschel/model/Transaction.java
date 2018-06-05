package com.example.Herschel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue
    private Long id;

    private Long moment;
    private Long userId;
    private Boolean success;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoment() {
        return moment;
    }

    public void setMoment(Long moment) {
        this.moment = moment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Transaction(Long moment, Long userId, Boolean success) {
        this.moment = moment;
        this.userId = userId;
        this.success = success;
    }

    public Transaction(Long userId, Boolean success) {
        this.moment = (new Date()).getTime();
        this.userId = userId;
        this.success = success;
    }

    public Transaction()

    {

    }
}
