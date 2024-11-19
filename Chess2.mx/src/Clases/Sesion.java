/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author luigi
 */
public class Sesion {
    private static String username;  
    private static boolean loggedIn = false;
    
    //Hacer que al iniciar el programa la sesión sea nula
    //private static String username = null;  // El nombre del usuario será null al iniciar la aplicación
    //private static boolean isLoggedIn = false; // Estado de sesión (inactivo por defecto)
    
    // Método para iniciar sesión
    public static void startSession(String user) {
        username = user;
        loggedIn = true;
    }

    // Método para cerrar sesión
    public static void endSession() {
        username = null;
        loggedIn = false;
    }

    // Método para obtener el nombre de usuario
    public static String getUsername() {
        return username;
    }

    // Método para verificar si el usuario está autenticado
    public static boolean isLoggedIn() {
        return loggedIn;
    }
}
