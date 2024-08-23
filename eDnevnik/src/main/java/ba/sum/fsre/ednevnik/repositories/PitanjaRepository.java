package ba.sum.fsre.ednevnik.repositories;

import ba.sum.fsre.ednevnik.models.Pitanja;
import ba.sum.fsre.ednevnik.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitanjaRepository extends JpaRepository<Pitanja,Long> {

}
