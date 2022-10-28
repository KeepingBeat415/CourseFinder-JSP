package com.example.soen387.model;

public class User {

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String phone_number;
    private String DOB;
    private String user_type;


    public User(String username, String password, String first_name, String last_name, String address, String email, String phone_number, String DOB, String user_type){
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.DOB = DOB;
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getDOB() {
        return DOB;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

