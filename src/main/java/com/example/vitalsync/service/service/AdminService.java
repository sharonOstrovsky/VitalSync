package com.example.vitalsync.service.service;

import com.example.vitalsync.entity.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> listarAdmins() throws Exception;
    public Admin guardarAdmin(Admin admin) throws Exception;
    Admin obtenerAdminPorId(Long id) throws Exception;
    Admin actualizarAdmin(Admin admin)throws Exception;
    void eliminarAdmin(Long id) throws Exception;
}
