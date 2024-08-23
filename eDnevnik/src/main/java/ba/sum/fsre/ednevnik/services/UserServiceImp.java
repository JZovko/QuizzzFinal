package ba.sum.fsre.ednevnik.services;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {

    @Autowired
    UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

}
