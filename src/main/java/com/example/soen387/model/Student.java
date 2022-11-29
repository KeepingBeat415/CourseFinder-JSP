package com.example.soen387.model;

import com.example.soen387.dao.PersonDao;

import javax.persistence.*;

@Entity
@DiscriminatorValue("student")
public class Student extends Person{

    private static final PersonDao personDao = new PersonDao();

    public Student(){
        super();
    }

    public Student(String username, String password, String first_name, String last_name, String address, String email, String phone_number, String DOB){
        super(username, password,first_name,last_name,address,email,phone_number,DOB, "student");
    }

    public String searchStudent(String student_id){
        return personDao.searchStudent(student_id);
    }

}
