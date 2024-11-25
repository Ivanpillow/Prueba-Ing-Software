package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class consultas {
    public boolean guardarUsuario(String usuario, String email, String password) {
        ConexionDB db = new ConexionDB();
        Connection conexion = db.conectar();

        // Comprobar si el usuario ya existe
        String verificarUsuarioSQL = "SELECT username FROM users WHERE username = ?";
        try (PreparedStatement pstVerificar = conexion.prepareStatement(verificarUsuarioSQL)) {
            pstVerificar.setString(1, usuario);
            ResultSet rs = pstVerificar.executeQuery();

            if (rs.next()) {
                // Si ya existe retornar false
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe. Elige otro.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar usuario: " + e.getMessage());
            return false;
        }

        // Comprobar si el correo electrónico ya existe
        String verificarEmailSQL = "SELECT email FROM users WHERE email = ?";
        try (PreparedStatement pstVerificar = conexion.prepareStatement(verificarEmailSQL)) {
            pstVerificar.setString(1, email);
            ResultSet rs = pstVerificar.executeQuery();

            if (rs.next()) {
                // Si ya existe retornar false
                JOptionPane.showMessageDialog(null, "El correo electrónico ya existe. Elige otro.");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar correo: " + e.getMessage());
            return false;
        }

        // Hash de la contraseña usando SHA-256
        String passwordHasheada;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b)); // Convertir a hexadecimal
            }
            passwordHasheada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error al hashear la contraseña: " + e.getMessage());
            return false;
        }

        // Insertar nuevo usuario en la base de datos
        String sqlInsertar = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstInsertar = conexion.prepareStatement(sqlInsertar)) {
            pstInsertar.setString(1, usuario);
            pstInsertar.setString(2, email);
            pstInsertar.setString(3, passwordHasheada);
            pstInsertar.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // Función para hashear contraseñas con SHA-256
    private String hashPassword(String password) {
        try {
            // Crear instancia de MessageDigest con SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes("UTF-8"));

            // Convertir el array de bytes a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Error al hashear la contraseña: " + e.getMessage());
        }
    }
    
    public boolean consultarUsuario(String email, String pass) {
        ConexionDB db = new ConexionDB();
        boolean loginExitoso = false;

        try {
            // Conexión a la base de datos
            Connection cn = db.conectar();

            // Hashear la contraseña ingresada por el usuario
            String passwordHasheada;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(pass.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hash) {
                    sb.append(String.format("%02x", b)); // Convertir a hexadecimal
                }
                passwordHasheada = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                JOptionPane.showMessageDialog(null, "Error al hashear la contraseña: " + e.getMessage());
                return false;
            }

            // Consulta para verificar usuario y contraseña
            PreparedStatement pst = cn.prepareStatement(
                "SELECT username FROM users WHERE email = ? AND password = ?"
            );
            pst.setString(1, email);
            pst.setString(2, passwordHasheada);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Inicio de sesión exitoso
                String username = rs.getString("username");
                loginExitoso = true;
                JOptionPane.showMessageDialog(null, "Login correcto. Bienvenido " + username);

                // Agregar la sesión en la clase Sesion
                Sesion.startSession(username);
            } else {
                // Usuario o contraseña incorrectos
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        return loginExitoso;
    }
    
    
    public boolean consultarContrincante(String username, String pass) {
        ConexionDB db = new ConexionDB();
        boolean contrincanteEncontrado = false;

        try {
            Connection cn = db.conectar();
            PreparedStatement pst = cn.prepareStatement(
                "SELECT username, email, password FROM users WHERE username = ?"
            );
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Obtén la contraseña almacenada (hasheada) de la base de datos
                String passwordAlmacenada = rs.getString("password");

                // Hashea la contraseña ingresada por el usuario
                String passwordHasheada = hashPassword(pass);

                // Compara las contraseñas
                if (passwordAlmacenada.equals(passwordHasheada)) {
                    contrincanteEncontrado = true;
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Contraseña del contrincante incorrecta.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Usuario del contrincante no encontrado.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al buscar contrincante: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }

        return contrincanteEncontrado;
    }
    
    public boolean guardarPartidaEnBD(int player1Id, int player2Id, String colorPlayer2) {
        try {
            // Establecer conexión con la base de datos
            ConexionDB db = new ConexionDB();
            Connection cn = db.conectar();


            if (player1Id == -1 || player2Id == -1){
                JOptionPane.showMessageDialog(null, "No se pudieron obtener los IDs de los usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            
            String query = "INSERT INTO games (player_id_white, player_id_black, winner_id, game_date) VALUES (?, ?, NULL, ?)";
            PreparedStatement pst = cn.prepareStatement(query);
            
            if(colorPlayer2.equals("Blancas")){
                pst.setInt(1, player2Id);
                pst.setInt(2, player1Id);
            } else{
                pst.setInt(1, player1Id);
                pst.setInt(2, player2Id);
            }
                    
            
            pst.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            int rowsInserted = pst.executeUpdate();
            cn.close(); 

            return rowsInserted > 0;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al guardar la partida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public int obtenerUsuarioId(String username, Connection cn) {
        try {
            String query = "SELECT user_id FROM users WHERE username = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    public String obtenerUsuario(int id, Connection cn) {
        try {
            String query = "SELECT username FROM users WHERE user_id = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, id); 

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("username"); //Retorna el nombre del usuario
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre del usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null; 
    }
}
