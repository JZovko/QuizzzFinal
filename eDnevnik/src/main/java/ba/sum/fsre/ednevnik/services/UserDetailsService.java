package ba.sum.fsre.ednevnik.services;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public ba.sum.fsre.ednevnik.models.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repository.findByEmail(username);
        return new ba.sum.fsre.ednevnik.models.UserDetails(u);
    }
    public User findByUsername(String username) {
        return repository.findByIme(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}