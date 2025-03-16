package com.william.products.service.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.products.service.ecommerce.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    boolean existsByIdentificacion(String identificacion);
    boolean existsByCorreo(String correo);
    
    boolean existsByNombreAndIdentificacion(String nombre, String identificacion);
}