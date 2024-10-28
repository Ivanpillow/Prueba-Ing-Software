/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author luigi
 */
public class UsuarioDAO {
    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        String passwordHash = BCrypt.hashpw(usuario.getPasswordHash(), BCrypt.gensalt()); // Hash de la contraseña
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, passwordHash); // Usar el hash en lugar de la contraseña en texto plano
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para iniciar sesión
    public boolean iniciarSesion(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String passwordHash = rs.getString("password"); // Obtener el hash almacenado
                return BCrypt.checkpw(password, passwordHash); // Verificar la contraseña ingresada con el hash
            }
            return false; // Si no se encontró el usuario
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar el puntaje (sin cambios)
    public void actualizarPuntaje(int userId, int nuevoPuntaje) {
        String sql = "UPDATE users SET score = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, nuevoPuntaje);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
