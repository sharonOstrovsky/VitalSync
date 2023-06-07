package com.example.vitalsync.controller;

import com.example.vitalsync.dto.request.admin.AdminRequestDTO;
import com.example.vitalsync.dto.response.admin.AdminResponseDTO;
import com.example.vitalsync.service.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vitalsync/admin")
@CrossOrigin
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearAdmin (@RequestBody AdminRequestDTO admin){
        try {
            System.out.println(admin);
            AdminResponseDTO ad = adminService.guardarAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin "+ admin.getUsuario().getEmail() + " registrado exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}