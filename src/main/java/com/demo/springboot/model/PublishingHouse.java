package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PublishingHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "A")
    @Column(nullable = false, updatable = false)
    private Long publishingHouseId;

    private String name;
    private String city;


    public PublishingHouse() { }
    public PublishingHouse(String name, String city) {
        this.name = name;
        this.city = city;
    }

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
