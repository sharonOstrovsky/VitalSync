package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.usuario.UsuarioInfoRequestDTO;
import com.example.vitalsync.dto.request.usuario.UsuarioLoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/vitalsync/auth")
@CrossOrigin
public class AuntenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;


    public AuntenticacionController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginRequestDTO authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getClave()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user=(User)authentication.getPrincipal();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user) {
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UsuarioInfoRequestDTO userInfo = new UsuarioInfoRequestDTO();
        userInfo.setFirstName(userObj.getUsername());
        userInfo.setLastName(userObj.getPassword());
        userInfo.setRoles(userObj.getAuthorities().toArray());

        return ResponseEntity.ok(userInfo);
    }

}
