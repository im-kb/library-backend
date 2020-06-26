package com.demo.springboot.domain.dto;


public class LoginData {
    private String login;
    private String password;

    public LoginData() { }
    public LoginData(String login, String password) {
        this.login=login;
        this.password=password;
    }

    public String getLogin() {return login;}
    public String getPassword() { return password;}
    @Override
    public String toString() {
        return "{\"LoginData\":{"
                + "\"Login\":\"" + login + ""
                + ", \"password\":\"" + password + "\""
                + "}}";
    }
}
