package com.ionisstm.intervenants.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "speaker_id")
    private int id;

    @OneToMany(mappedBy="speaker")
    private Set<Address> addresses;

    @Column(name = "professional_status")
    private String professionalStatus;

    //Approved training organization
    @OneToMany(mappedBy="speaker")
    private Set<ApprovedTrainingOrganization> approvedTrainingOrganizations;

    @Column(name = "job")
    private String job;

    @Column(name = "bachelor_rate")
    private int bachelorRate;

    @Column(name = "master_one_rate")
    private int masterOneRate;

    @Column(name = "master_two_rate")
    private int masterTwoRate;

    //Evaluation
    @OneToMany(mappedBy="speaker")
    private Set<Session> sessions;

    @Column(name = "is_blacklist")
    private Boolean isBlacklist;

    @ManyToMany
    @JoinTable(name = "speaker_subject", joinColumns = @JoinColumn(name = "speaker_id"), inverseJoinColumns = @JoinColumn(name = "supject_id"))
    private Set<Subject> subjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<ApprovedTrainingOrganization> getApprovedTrainingOrganizations() {
        return approvedTrainingOrganizations;
    }

    public void setApprovedTrainingOrganizations(Set<ApprovedTrainingOrganization> approvedTrainingOrganizations) {
        this.approvedTrainingOrganizations = approvedTrainingOrganizations;
    }

    public String getProfessionalStatus() {
        return professionalStatus;
    }

    public void setProfessionalStatus(String professionalStatus) {
        this.professionalStatus = professionalStatus;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getBachelorRate() {
        return bachelorRate;
    }

    public void setBachelorRate(int bachelorRate) {
        this.bachelorRate = bachelorRate;
    }

    public int getMasterOneRate() {
        return masterOneRate;
    }

    public void setMasterOneRate(int masterOneRate) {
        this.masterOneRate = masterOneRate;
    }

    public int getMasterTwoRate() {
        return masterTwoRate;
    }

    public void setMasterTwoRate(int masterTwoRate) {
        this.masterTwoRate = masterTwoRate;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Boolean getBlacklist() {
        return isBlacklist;
    }

    public void setBlacklist(Boolean blacklist) {
        isBlacklist = blacklist;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
