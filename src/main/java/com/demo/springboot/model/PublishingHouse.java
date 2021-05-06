package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PublishingHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="publishing_house_id", nullable = false, updatable = false)
    private Long publishingHouseId;

    private String name;
    private String city;


    public PublishingHouse() { }

    public String getName() {return name;}
    public Long getPublishingHouseId() {return publishingHouseId;}
    public String getCity() { return city;}
    @Override
    public String toString() {
        return "{\"WydawnictwoData\":{"
                + "\"ID Wydawnictwa\":\"" + publishingHouseId + ""
                + "\"Nazwa\":\"" + name + ""
                + ", \"Miasto\":\"" + city + "\""
                + "}}";
    }
}
