package com.ionisstm.intervenants.model;

import javax.persistence.*;

@Entity
@Table(name = "ato")
public class ApprovedTrainingOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ato_id")
    private int id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey, name = "speaker_id", nullable=false, referencedColumnName = "speaker_id")
    private Speaker speaker;

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
}
