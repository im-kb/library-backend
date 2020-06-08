package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginData {
    private String login;
    private String password;
    private boolean isAdmin;

    public LoginData() { }

    public String getLogin() {return login;}
    public String getPassword() { return password;}
    @Override
    public String toString() {
        return "{\"LoginData\":{"
                + "\"Login\":\"" + login + ""
                + ", \"password\":\"" + password + "\""
                + ", \"isAdmin:\":\"" + isAdmin + "\""
                + "}}";
    }
}
