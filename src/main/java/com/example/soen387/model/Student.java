package com.example.soen387.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("student")
public class Student extends Person{

    public Student(){
        super();
    }

    public Student(String username, String password, String first_name, String last_name, String address, String email, String phone_number, String DOB){
        super(username, password,first_name,last_name,address,email,phone_number,DOB, "student");
    }

}
