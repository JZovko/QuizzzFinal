package ba.sum.fsre.ednevnik.services;

import ba.sum.fsre.ednevnik.models.Odgovori;
import ba.sum.fsre.ednevnik.repositories.OdgovoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OdgovoriServiceImp {
    @Autowired
    OdgovoriRepository odgovoriRepository;

    public Odgovori saveOdgovori(Odgovori odgovori){
        return odgovoriRepository.save(odgovori);
    }

    public Optional<Odgovori> findById(Long id) {
        return odgovoriRepository.findById(id);
    }

    public void save(Odgovori existingOdgovori) {
        odgovoriRepository.save(existingOdgovori);
    }
}
