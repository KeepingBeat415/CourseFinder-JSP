package com.example.soen387.model;
import com.example.soen387.dao.PersonDao;

import javax.persistence.*;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String phone_number;
    private String DOB;
    private String user_type;

    private static final ReentrantLock register_lock = new ReentrantLock();

    // Default Constructor
    public Person(){
        this(null,null,null,null,null,null,null,null,null);
    }

    // For register new user
    public Person(String username, String password, String first_name, String last_name, String address, String email, String phone_number, String DOB, String user_type){
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
    // For Login
    public Person(String username, String password){
        this.username = username;
        this.password = password;
    }
    // For search course
    public Person(int id, String first_name, String last_name, String phone_number, String email){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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

    public String createPerson(){

        // Create Data Access Object
        PersonDao personDao = new PersonDao();

        register_lock.lock();
        String userRegistered = "";

        try{
            // Insert User data into the database
            userRegistered = personDao.registerUser(this);
        }
        finally {
            register_lock.unlock();
        }
        return userRegistered;
    }

    public Person findByUsername(String username){
        // Create Data Access Object
        PersonDao personDao = new PersonDao();
        return personDao.findPersonInfo(username);
    }

    public String updatePerson(){
        // Create Data Access Object
        PersonDao personDao = new PersonDao();
        return personDao.updatePersonInfo(this);
    }

    public boolean deletePerson(String username){
        // Create Data Access Object
        PersonDao personDao = new PersonDao();
        return personDao.deletePerson(username);
    }
}
