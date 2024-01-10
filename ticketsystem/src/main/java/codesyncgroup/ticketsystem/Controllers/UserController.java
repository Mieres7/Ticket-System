package codesyncgroup.ticketsystem.Controllers;

import codesyncgroup.ticketsystem.Entities.UserEntity;
import codesyncgroup.ticketsystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity newUser) {
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Optional<UserEntity>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findByUsername(username));
    }
}
