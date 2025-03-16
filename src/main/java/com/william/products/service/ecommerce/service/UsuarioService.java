package com.william.products.service.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.william.products.service.ecommerce.entity.UsuarioEntity;
import com.william.products.service.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {

      @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity registrarUsuario(UsuarioEntity usuario) {
        if (usuarioRepository.existsByIdentificacion(usuario.getIdentificacion())) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La identificación ya está registrada.");
         /*    throw new IllegalArgumentException("La identificación ya está registrada."); */
        }
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado.");
            /* throw new IllegalArgumentException("El correo ya está registrado."); */
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioEntity> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public boolean validarCredenciales(String nombre, String identificacion) {
        return usuarioRepository.existsByNombreAndIdentificacion(nombre, identificacion);
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
