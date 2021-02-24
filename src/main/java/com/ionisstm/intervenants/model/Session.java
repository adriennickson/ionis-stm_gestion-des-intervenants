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
    private Date dateAddressTo;

    @Column(name = "evaluation")
    private int evaluation;


}
