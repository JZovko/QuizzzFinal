package ba.sum.fsre.ednevnik.services;

import ba.sum.fsre.ednevnik.models.Quiz;
import ba.sum.fsre.ednevnik.pomocno.QuizService;
import ba.sum.fsre.ednevnik.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImp implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    public Quiz saveQuiz(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public List<Quiz> findAll() {return quizRepository.findAll();};

    public Quiz findById(Long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            return quiz.get();
        } else {
            throw new RuntimeException("Quiz not found with id: " + quizId);
        }
    }

    public void deleteById(Long Id) {
        quizRepository.deleteById(Id);
    }


    @Override
    public Quiz getQuizById(Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        return quizOptional.orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));
    }
}
