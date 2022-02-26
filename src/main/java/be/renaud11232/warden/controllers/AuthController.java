package be.renaud11232.warden.controllers;

import be.renaud11232.warden.models.User;
import be.renaud11232.warden.responses.Token;
import be.renaud11232.warden.repositories.UserRepository;
import be.renaud11232.warden.requests.LoginRequest;
import be.renaud11232.warden.security.jwt.JwtUtils;
import be.renaud11232.warden.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new Token(jwt));
    }

    @GetMapping("/whoami")
    public ResponseEntity<?> whoAmI() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUuid(userDetails.getUuid()).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/renew")
    public ResponseEntity<?> renew() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new Token(jwt));
    }

}
