package com.ionisstm.intervenants.repository;

import com.ionisstm.intervenants.model.Session;
import com.ionisstm.intervenants.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Set;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Set<Session> findBySpeakerAndYear(Speaker speaker, Date year);
}
