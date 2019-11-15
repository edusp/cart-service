package com.bt.au.shoppingcart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class PersistenceDomainObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    @PrePersist
    private void prePersist(){
        Date date = new Date();
        if (this.creationDate == null) {
            this.creationDate = date;
        }
    }
}
