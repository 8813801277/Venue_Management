package com.genie.Venue_Mangamnet.dto;

public class LoginDto {
    private String username;
    private String password;

    LoginDto(){

    }

    public LoginDto(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}