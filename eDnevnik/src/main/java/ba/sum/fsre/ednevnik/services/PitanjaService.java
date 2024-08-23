package ba.sum.fsre.ednevnik.services;

import ba.sum.fsre.ednevnik.models.Pitanja;
import ba.sum.fsre.ednevnik.repositories.PitanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PitanjaService {
    @Autowired
    PitanjaRepository pitanjaRepository;

    public Pitanja savePitanja(Pitanja pitanja){
        return pitanjaRepository.save(pitanja);
    }

    public Optional<Pitanja> findById(Long id) {
        return pitanjaRepository.findById(id);
    }

    public void save(Pitanja existingPitanja) {
        pitanjaRepository.save(existingPitanja);
    }
}
