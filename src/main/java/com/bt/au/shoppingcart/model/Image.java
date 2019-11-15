package com.bt.au.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Image extends PersistenceDomainObject {

    private String name;
    @Lob
    @Column(name = "image")
    private byte[] image;

    public Image(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setFile(byte[] filepart) {
        this.image = filepart;
    }
}
