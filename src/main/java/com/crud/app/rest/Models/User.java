package com.crud.app.rest.Models;



import javax.persistence.*;

@Entity
public class User {
    @Id //primary key of the DB
    @GeneratedValue(strategy = GenerationType.AUTO) //ID auto increment
    private long id;

    @Column(name = "first_Name") //DB column name
    private String First_Name;
    @Column(name = "last_Name")
    private String Last_Name;

    @Column(name = "age")
    private int Age;
    @Column(name = "occupation")
    private String occupation;

    @Column
    public String email;

    public User(int id, String email, String fName, String lName, String occupation, int age) {

        this.id = id;
        this.email = email;
        this.First_Name = fName;
        this.Last_Name = lName;
        this.occupation = occupation;
        this.Age=age;

    }

    public User() {

    }

    public User(int i, String park, String bo_gum, String developer, int i1) {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
