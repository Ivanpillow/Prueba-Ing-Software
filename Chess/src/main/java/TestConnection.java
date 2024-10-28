
import com.mycompany.chess.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luigi
 */
public class TestConnection {
    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("Conexión exitosa a la base de datos");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
