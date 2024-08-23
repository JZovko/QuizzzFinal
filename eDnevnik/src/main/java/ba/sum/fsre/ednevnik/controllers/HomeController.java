package ba.sum.fsre.ednevnik.controllers;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.models.UserAnswers;
import ba.sum.fsre.ednevnik.models.UserDetails;
import ba.sum.fsre.ednevnik.services.UserAnswersServiceImp;
import ba.sum.fsre.ednevnik.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserAnswersServiceImp userAnswersServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;


    @GetMapping("/quiz/povijest/{userId}")
    public String Povijest(@PathVariable Long userId, @AuthenticationPrincipal UserDetails currentUser, Model model){
        //Dohvati authenticated user-ov ID
        String currentUsername = currentUser.getUsername();

        // Dohvati usera
        User authenticatedUser = userServiceImp.findByUsername(currentUsername);

        if (authenticatedUser.getId().equals(userId)) {
            List<UserAnswers> userAnswersList = userAnswersServiceImp.getUserAnswersByUser(authenticatedUser);
            model.addAttribute("userAnswersList", userAnswersList);
            return "/quiz/povijest";
        }else {
            return "redirect:/home";
        }

    }

    @GetMapping("/home")
    public String showHomePage(@AuthenticationPrincipal UserDetails currentUser,Model model) {
        //Dohvati authenticated user-ov ID
        String currentUsername = currentUser.getUsername();
        // Dohvati usera
        User authenticatedUser = userServiceImp.findByUsername(currentUsername);

        model.addAttribute("authenticatedUser",authenticatedUser);
        model.addAttribute("currentUsername",currentUsername);

        return "quiz/home";
    }
}