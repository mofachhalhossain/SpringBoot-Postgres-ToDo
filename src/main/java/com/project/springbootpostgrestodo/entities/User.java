package com.project.springbootpostgrestodo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity                                   //0.0.001->defines entity
@Table(name = "User")                      //0.0.002->for Defining tables in database

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        //0.0.003->for generating auto value
    private int id;

    @Column(length = 20)                                //0.0.004->for changing field

    @NotBlank(message = "Please insert your name")       /*validator*/
    @Size(min = 3, max = 16, message = "3-16 characters are allowed")      /*validating charecter size*/
    private String name;
    @Column(unique = true)                     //0.0.005->changing field
    private String email;
    private String password;
    private String role;
    private String imageUrl;


    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", todo=" + todo +
                '}';
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    // for specifing database relation // CascadeType.ALL will help like when user will be deleted do will delete automatic
    //visit: https://howtodoinjava.com/hibernate/how-to-define-association-mappings-between-hibernate-entities/


    public List<ToDo> todo;
    {
        todo = new ArrayList<>();
    }

    public List<ToDo> getTodo() {
        return todo;
    }

    public void setTodo(List<ToDo> todo) {
        this.todo = todo;
    }

    /*Check HomeController*/
    public void setEnabled(boolean enabled) {
    }

}

