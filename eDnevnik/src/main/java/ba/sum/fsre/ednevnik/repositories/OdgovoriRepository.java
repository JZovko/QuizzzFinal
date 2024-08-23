package ba.sum.fsre.ednevnik.repositories;

import ba.sum.fsre.ednevnik.models.Odgovori;
import ba.sum.fsre.ednevnik.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OdgovoriRepository extends JpaRepository<Odgovori,Long> {

}
