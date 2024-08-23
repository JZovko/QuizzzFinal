package ba.sum.fsre.ednevnik.repositories;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.models.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswersRepository extends JpaRepository<UserAnswers,Long> {
    //basic CRUD
    List<UserAnswers> findByUser(User user);


}

