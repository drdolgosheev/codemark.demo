package com.codemark.demoapp.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Базовая сущность с датой создания / изменения объекта
 * и статусом объекта ACTIVE, NOT_ACTIVE, DELETED
 */
@MappedSuperclass
@Data
public class BaseEntity {

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date date) {
        this.created = date;
    }

    public void setUpdated(Date date) {
        this.updated = date;
    }

    public Date getUpdated() {
        return updated;
    }
}
