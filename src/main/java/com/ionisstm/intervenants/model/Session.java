package com.ionisstm.intervenants.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_id")
    private int id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey, name = "speaker_id", nullable=false, referencedColumnName = "speaker_id")
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey, name = "subject_id", nullable=false, referencedColumnName = "subject_id")
    private Subject subject;

    // Prendre la première année de l'année académique (Par exemple pour l'année 2020-2021 prende 2020)
    @Column(name = "year", nullable = false)
    private Date year;

    @Column(name = "evaluation")
    private String evaluation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
