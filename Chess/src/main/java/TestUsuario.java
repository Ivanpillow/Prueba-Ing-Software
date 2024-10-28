
import com.mycompany.chess.Usuario;
import com.mycompany.chess.UsuarioDAO;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luigi
 */
public class TestUsuario {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Registro de usuario
        System.out.println("Registro de nuevo usuario:");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Correo: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        // Aquí deberías hacer un hash de la contraseña
        String passwordHash = password; // Para efectos de esta prueba, no uses este método en producción.

        Usuario nuevoUsuario = new Usuario(username, email, passwordHash);
        boolean registrado = usuarioDAO.registrarUsuario(nuevoUsuario);
        if (registrado) {
            System.out.println("Registro exitoso.");
        } else {
            System.out.println("Error al registrar el usuario.");
        }

        // Iniciar sesión
        System.out.println("\nIniciar sesión:");
        System.out.print("Correo: ");
        String emailLogin = scanner.nextLine();
        System.out.print("Password: ");
        String passwordLogin = scanner.nextLine();
        
        boolean inicioExitoso = usuarioDAO.iniciarSesion(emailLogin, passwordLogin);
        if (inicioExitoso) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Error en el inicio de sesión. Verifica tus credenciales.");
        }

        scanner.close();
    }
}
