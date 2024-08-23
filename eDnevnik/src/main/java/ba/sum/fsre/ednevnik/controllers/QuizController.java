package ba.sum.fsre.ednevnik.controllers;

import ba.sum.fsre.ednevnik.models.*;
import ba.sum.fsre.ednevnik.repositories.UserRepository;
import ba.sum.fsre.ednevnik.services.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    @Autowired
    OdgovoriServiceImp odgovoriServiceImp;
    @Autowired
    PitanjaService pitanjaService;
    @Autowired
    QuizServiceImp quizServiceImp;
    @Autowired
    UserAnswersServiceImp userAnswersServiceImp;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImp userServiceImp;


    @GetMapping("/quiz/add")
    public String addQuiz(Model model){

        Quiz quiz = new Quiz();

        // Inicijalizira 5 Pitanja, koji svaki ima 3 Odgovora
        for (int i = 0; i < 5; i++) {
            Pitanja pitanja = new Pitanja();
            for (int j = 0; j < 3; j++) {
                pitanja.getOdgovori().add(new Odgovori());
            }
            quiz.getPitanja().add(pitanja);
        }

        model.addAttribute("quiz", quiz);
        return "quiz/addQuiz";
    }

    //treba napraviti da quiz/list izlista sve quizove
    @GetMapping("/quiz/list")
    public String QuizList(Model model){
        List<Quiz> quizovi = quizServiceImp.findAll();
        model.addAttribute("quizovi", quizovi);
        return "/quiz/list";
    }

    @GetMapping("/quiz/play/{quizId}")
    public String playQuiz(@PathVariable Long quizId, Model model,Authentication authentication) {
        Quiz quiz = quizServiceImp.findById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("pitanja", quiz.getPitanja());
        return "quiz/playQuiz";
    }


    @GetMapping("/quiz/test")
    public String QuizTest(Model model){
        List<Quiz> quizovi1 = quizServiceImp.findAll();
        model.addAttribute("quizovi", quizovi1);
        return "quiz/list";
    }

    @PostMapping("/quiz/submitAnswers")
    public String UserPostQuiz(@RequestParam("selectedAnswers") List<Boolean> selectedAnswerIds,
                               @RequestParam("quizId") Long quizId,
                               Model model,
                               @AuthenticationPrincipal UserDetails userDetails){
        User user = null;
        if (userDetails != null) {
            user = userServiceImp.findByUsername(userDetails.getUsername());
        }

        // Get trenutnog user-a ID
        Long userId = user != null ? user.getId() : null;
        model.addAttribute("user_id", userId);
        Quiz quiz = quizServiceImp.getQuizById(quizId);

        //Broj tocnih odgovora
        int correctAnswersCount = 0;
        for (Boolean answer : selectedAnswerIds) {
            if (answer) {
                correctAnswersCount++;
            }
        }

        model.addAttribute("correctAnswersCount", correctAnswersCount);
        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(user);
        userAnswers.setQuiz(quiz);
        userAnswers.setTocnoOdgovoreno(correctAnswersCount);
        userAnswers.setOdgovorenoU(LocalDateTime.now());
        userAnswersServiceImp.saveUserAnswers(userAnswers);

        model.addAttribute("user", user);
        model.addAttribute("quiz_id", quizId);
        model.addAttribute("totalQuestions", quiz.getPitanja().size());

        return "redirect:/quiz/list";

    }

    @PostMapping("/quiz/delete/{quizId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteQuiz(@PathVariable Long quizId){
        quizServiceImp.deleteById(quizId);
        return "redirect:/quiz/list";
    }

    @PostMapping("/quiz/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addQuiz(@ModelAttribute Quiz quiz) {
        LocalDateTime  Datum1 = LocalDateTime.now();
        quiz.setDatum(Datum1);
        quizServiceImp.saveQuiz(quiz);
        return "redirect:/quiz/list";
    }

    @GetMapping("/quiz/edit/{quizId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateQuiz(@PathVariable Long quizId,Model model){
        Quiz quiz = quizServiceImp.findById(quizId);
        model.addAttribute("quiz", quiz);
        return "quiz/editQuiz";
    }

    @PostMapping("/quiz/editQuiz/{quizId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(@PathVariable Long quizId, @ModelAttribute Quiz quiz, Model model) {
        // Provjerite postoji li korisnik s tim ID-om
        Quiz existingQuiz = quizServiceImp.findById(quizId);
        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setDescription(quiz.getDescription());

        if (quizId == null) {
            // Handle the error appropriately, perhaps log it or return an error page.
            return "redirect:/quiz/test"; // Or any error page
        }

        for (Pitanja pitanja : quiz.getPitanja()) {
            Optional<Pitanja> existingPitanjaOpt = pitanjaService.findById(pitanja.getId());

            if (existingPitanjaOpt.isPresent()) {
                Pitanja existingPitanja = existingPitanjaOpt.get();
                existingPitanja.setText(pitanja.getText());

                // Update Odgovori
                for (Odgovori odgovor : pitanja.getOdgovori()) {
                    Optional<Odgovori> existingOdgovoriOpt = odgovoriServiceImp.findById(odgovor.getId());

                    if (existingOdgovoriOpt.isPresent()) {
                        Odgovori existingOdgovori = existingOdgovoriOpt.get();
                        existingOdgovori.setOdgovorText(odgovor.getOdgovorText());
                        existingOdgovori.setCorrect(odgovor.isCorrect());

                        odgovoriServiceImp.save(existingOdgovori);
                    }
                }

                pitanjaService.save(existingPitanja);

            }

        }
        // Postavite ostala polja po potrebi
        quizServiceImp.saveQuiz(existingQuiz);

        return "redirect:/quiz/list";
    }

}

