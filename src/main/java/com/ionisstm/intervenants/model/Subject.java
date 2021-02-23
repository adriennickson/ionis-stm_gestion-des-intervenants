package com.ionisstm.intervenants.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "*Please provide a subject name")
    @NotEmpty(message = "*Please provide a subject name")
    private String name;

    @Column(name = "description")
    @NotNull(message = "*Please provide a subject description")
    @NotEmpty(message = "*Please provide a subject description")
    private String description;

    @Column(name = "is_active", columnDefinition="tinyint(1) default 1")
    //@NotNull(message = "*Please provide a ACTIVE STATUS")
    private Boolean isActive;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}