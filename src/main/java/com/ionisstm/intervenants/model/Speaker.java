package com.ionisstm.intervenants.model;

import javax.persistence.*;

@Entity
@Table(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "speaker_id")
    private int id;

}
