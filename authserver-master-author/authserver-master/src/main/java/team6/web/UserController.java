package team6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import team6.model.Authority;
import team6.model.AuthorityRepository;
import team6.model.User;
import team6.model.UserRepository;

import java.security.Principal;


@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }



    @GetMapping("/hello")
    public ResponseEntity helloWorld(){
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping("/hello")
    public ResponseEntity posthelloWorld(@RequestParam String name){
        return ResponseEntity.ok("Hello World, " + name + "!");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam @NonNull String username,
                                              @RequestParam @NonNull String password) {
        if (userRepository.findUserByUsername(username) != null) {
            return ResponseEntity.ok("username already in use");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Authority auth = new Authority();
        auth.setAuthority("ROLE_USER");
        auth.setUsername(username);
        userRepository.save(user);
        authorityRepository.save(auth);
        return ResponseEntity.ok("success");
    }

}
