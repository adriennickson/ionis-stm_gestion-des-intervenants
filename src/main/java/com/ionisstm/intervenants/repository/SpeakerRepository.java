package com.ionisstm.intervenants.repository;

import com.ionisstm.intervenants.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
    Set<Speaker> findAllByNameContainingOrLastNameContaining(String a, String b);

    @Query(value = "SELECT DISTINCT * FROM speakers JOIN speaker_subject ON speaker_subject.speaker_id=speakers.speaker_id "
            +"JOIN subjects ON speaker_subject.supject_id=subjects.subject_id WHERE subjects.name LIKE :query", nativeQuery = true)
    public List<Speaker> FindAllWithJoinSubjectByName(String query);
}
