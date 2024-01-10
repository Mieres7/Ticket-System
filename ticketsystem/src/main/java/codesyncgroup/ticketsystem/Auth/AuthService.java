package codesyncgroup.ticketsystem.Auth;

import codesyncgroup.ticketsystem.Entities.ProfileEntity;
import codesyncgroup.ticketsystem.Entities.UserEntity;
import codesyncgroup.ticketsystem.Jwt.JwtService;
import codesyncgroup.ticketsystem.Repositories.ProfileRepository;
import codesyncgroup.ticketsystem.Repositories.RoleRepository;
import codesyncgroup.ticketsystem.Repositories.UnitRepository;
import codesyncgroup.ticketsystem.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;
    private final UnitRepository unitRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Login method that receives a login request form and return a token in case that the credentials are ok
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
    public AuthResponse register(RegisterRequest request) {
        // Creates the profile object
        ProfileEntity profile = ProfileEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .build();

        // Saves it in the database
        profileRepository.save(profile);

        // Creates the user objet
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByNameRole(request.getRole()))
                .unit(unitRepository.findByNameUnit(request.getUnit()))
                .profile(profile)
                .build();

        // Saves it in the database
        userRepository.save(user);

        // Returns the token showing that the register was correct and the session will be authenticated for the moment
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
