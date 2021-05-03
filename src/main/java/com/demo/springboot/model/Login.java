package com.demo.springboot.model;


public class Login {


    private String login;
    private String password;

    public Login() {
    }

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
