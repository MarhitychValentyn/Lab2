package com.dev.bank.models.request;

public class AuthRegisterRequest {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String birthday;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getBirthday() { return birthday; }
}
