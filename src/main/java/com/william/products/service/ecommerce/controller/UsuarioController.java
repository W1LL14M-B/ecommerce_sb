package com.william.products.service.ecommerce.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.products.service.ecommerce.entity.UsuarioEntity;
import com.william.products.service.ecommerce.service.UsuarioService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

@Autowired
private UsuarioService usuarioService;

@PostMapping
public ResponseEntity<UsuarioEntity> registrarUsuario(@Valid @RequestBody UsuarioEntity usuario) {
    UsuarioEntity nuevoUsuario = usuarioService.registrarUsuario(usuario);
    return ResponseEntity.ok(nuevoUsuario);
}

@GetMapping("/{id}")
public ResponseEntity<UsuarioEntity> obtenerUsuarioPorId(@PathVariable Long id) {
    return usuarioService.obtenerUsuarioPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
 }

@GetMapping
public ResponseEntity<?> obtenerTodosLosUsuarios() {
    return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
}


@PostMapping("/login")
public ResponseEntity<?> validarCredenciales(@RequestBody Map<String, String> credenciales) {
    String nombre = credenciales.get("nombre");
    String identificacion = credenciales.get("identificacion");
    boolean esValido = usuarioService.validarCredenciales(nombre, identificacion);
    if (esValido) {
        return ResponseEntity.ok("Credenciales válidas.");
    } else {
        return ResponseEntity.status(401).body("Credenciales inválidas.");
    }
}

@PutMapping("/{id}")
public ResponseEntity<UsuarioEntity> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioEntity usuarioDetalles) {
    return usuarioService.obtenerUsuarioPorId(id)
            .map(usuario -> {
                usuario.setNombre(usuarioDetalles.getNombre());
                usuario.setIdentificacion(usuarioDetalles.getIdentificacion());
                usuario.setCorreo(usuarioDetalles.getCorreo());
                UsuarioEntity usuarioActualizado = usuarioService.registrarUsuario(usuario);
                return ResponseEntity.ok(usuarioActualizado);
            })
            .orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
    return usuarioService.obtenerUsuarioPorId(id)
            .map(usuario -> {
                usuarioService.eliminarUsuario(id);
                return ResponseEntity.ok("Usuario eliminado exitosamente.");
            })
            .orElse(ResponseEntity.notFound().build());
} 



}
