package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class consultas {
    public boolean guardarUsuario(String usuario, String password){
        ConexionDB db = new ConexionDB();
        Connection conexion = db.conectar();

        //Comprobar si el usuario ya existe
        String verificarUsuarioSQL = "SELECT nombre FROM usuarios WHERE nombre = ?";
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

        //Insertar nuevo usuario en bd
        String sqlInsertar = "INSERT INTO usuarios(nombre, clave) VALUES (?, ?)";
        try (PreparedStatement pstInsertar = conexion.prepareStatement(sqlInsertar)) {
            pstInsertar.setString(1, usuario);
            pstInsertar.setString(2, password);
            pstInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public boolean consultarUsuario(String user, String pass) {
        ConexionDB db = new ConexionDB();
        boolean loginExitoso = false;
        
        try {
            Connection cn = db.conectar();
            PreparedStatement pst = cn.prepareStatement("SELECT nombre, clave FROM usuarios WHERE nombre = ? AND clave = ?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                loginExitoso = true;
                JOptionPane.showMessageDialog(null, "Login correcto. Bienvenido " + user);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return loginExitoso;
    }
}
