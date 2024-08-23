package ba.sum.fsre.ednevnik.pomocno;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.models.UserAnswers;

import java.util.List;

public interface UserAnswersService {
    public User getUserById(Long userId);
    public boolean isCorrectAnswer(Long answerId);
    public List<UserAnswers> getUserAnswersByUser(User user);
}
