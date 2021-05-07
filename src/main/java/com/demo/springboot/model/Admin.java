package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "admin")
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false, updatable = false)
    private Long adminId;
    private String login;
    private String password;

    public Long getAdminId() {
        return adminId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
