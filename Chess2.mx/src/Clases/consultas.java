package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class consultas {
    public boolean guardarUsuario(String usuario, String email, String password){
        ConexionDB db = new ConexionDB();
        Connection conexion = db.conectar();

        //Comprobar si el usuario ya existe
        String verificarUsuarioSQL = "SELECT username FROM users WHERE username = ?";
        try (PreparedStatement pstVerificar = conexion.prepareStatement(verificarUsuarioSQL)) {
            pstVerificar.setString(1, usuario);
            ResultSet rs = pstVerificar.executeQuery();

            if (rs.next()) {
                //Si ya existe retornar false
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Elige otro.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage());
            return false;
        }
        
        
        //Comprobar si el usuario ya existe
        String verificarEmailSQL = "SELECT email FROM users WHERE email = ?";
        try (PreparedStatement pstVerificar = conexion.prepareStatement(verificarEmailSQL)) {
            pstVerificar.setString(1, email);
            ResultSet rs = pstVerificar.executeQuery();

            if (rs.next()) {
                //Si ya existe retornar false
                JOptionPane.showMessageDialog(null, "El correo electrónico ya existe. Elige otro.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar correo: " + e.getMessage());
            return false;
        }

        //Insertar nuevo usuario en bd
        String sqlInsertar = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstInsertar = conexion.prepareStatement(sqlInsertar)) {
            pstInsertar.setString(1, usuario);
            pstInsertar.setString(2, email);
            pstInsertar.setString(3, password);
            pstInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean consultarUsuario(String email, String pass) {
        ConexionDB db = new ConexionDB();
        boolean loginExitoso = false;
        
        try {
            Connection cn = db.conectar();
            PreparedStatement pst = cn.prepareStatement("SELECT username, email, password FROM users WHERE email = ? AND password = ?");
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                loginExitoso = true;
                JOptionPane.showMessageDialog(null, "Login correcto. Bienvenido " + username);
                
                //Agregar la sesión en la clase Sesion
                Sesion.startSession(username);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return loginExitoso;
    }
    
    
    public boolean consultarContrincante(String username, String pass) {
        ConexionDB db = new ConexionDB();
        boolean userEncontrado = false;
        
        try {
            Connection cn = db.conectar();
            PreparedStatement pst = cn.prepareStatement("SELECT username, email, password FROM users WHERE username = ? AND password = ?");
            pst.setString(1, username);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userEncontrado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return userEncontrado;
    }
}
