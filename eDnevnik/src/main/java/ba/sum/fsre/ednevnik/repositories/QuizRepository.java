package ba.sum.fsre.ednevnik.repositories;

import ba.sum.fsre.ednevnik.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    // basic CRUD operations

}
