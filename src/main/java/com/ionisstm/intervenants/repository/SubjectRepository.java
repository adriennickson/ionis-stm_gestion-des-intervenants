package com.ionisstm.intervenants.repository;

import com.ionisstm.intervenants.model.Role;
import com.ionisstm.intervenants.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Integer> {
    Subject findByName(String name);
}
