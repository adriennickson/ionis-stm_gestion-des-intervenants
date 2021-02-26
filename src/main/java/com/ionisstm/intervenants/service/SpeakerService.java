package com.ionisstm.intervenants.service;

import com.ionisstm.intervenants.model.Speaker;
import com.ionisstm.intervenants.repository.SpeakerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class SpeakerService {

    private SpeakerRepository speakerRepository;
    public Optional<Speaker> findSpeakerById(Integer id) {
        return speakerRepository.findById(id);
    }

}
