package com.ionisstm.intervenants.model;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    //@NotNull(message = "*Please provide a user name")
    //@NotEmpty(message = "*Please provide a user name")
    private String userName;

    @Column(name = "email")
    //@Email(message = "*Please provide a valid Email")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @NotNull(message = "*Please provide an email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @NotNull(message = "*Please provide your password")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "name")
    @NotNull(message = "*Please provide your name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotNull(message = "*Please provide your last name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}