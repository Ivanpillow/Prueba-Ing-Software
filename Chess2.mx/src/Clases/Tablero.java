package Clases;

import Formularios.TableroAjedrez;
import javax.swing.JOptionPane;

/**
 *
 * @author luigi
 */
public class Tablero {

    private Pieza[][] tablero; //8x8 matriz para almacenar las piezas

    // Constructor
    public Tablero() {
        tablero = new Pieza[8][8];
        inicializarTablero();
    }

    //Inicializar el tablero con las piezas en las posiciones iniciales
    private void inicializarTablero() {
        //Path base para las imagenes
                        
        /****************************************
        CAMBIEN EL VALOR DE directorioBase
        PARA QUE SE CARGUEN BIEN LAS IMÁGENES
        ****************************************/
        String directorioBase = "C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\";

        //piezas principales
        String[] piezasPrincipalesNegro = {"black_rook.png", "black_knight.png", "black_bishop.png", "black_queen.png", "black_king.png", "black_bishop.png", "black_knight.png", "black_rook.png"};
        String[] nombresPiezasNegro = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        String[] piezasPrincipalesBlanco = {"white_rook.png", "white_knight.png", "white_bishop.png", "white_queen.png", "white_king.png", "white_bishop.png", "white_knight.png", "white_rook.png"};
        String[] nombresPiezasBlanco = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        //Conf de piezas principales (fila 0 para negras, fila 7 para blancas)
        for (int col = 0; col < 8; col++) {
            // Piezas negras
            //tablero[0][col] = new Pieza(nombresPiezasNegro[col], "Negro", directorioBase + piezasPrincipalesNegro[col]);
            // Piezas blancas
            tablero[7][col] = new Pieza(nombresPiezasBlanco[col], "Blanco", directorioBase + piezasPrincipalesBlanco[col]);
        }

        // Configuración de los peones
        for (int col = 0; col < 8; col++) {
            // Peones negros
            //tablero[1][col] = new Pieza("Peon", "Negro", directorioBase + "black_pawn.png");
            // Peones blancos
            tablero[6][col] = new Pieza("Peon", "Blanco", directorioBase + "white_pawn.png");
        }
    }

    // Obtener pieza en una posición específica
    public Pieza getPieza(int fila, int col) {
        return tablero[fila][col];
    }

    // Establecer una pieza en una posición específica
    public void setPieza(int fila, int col, Pieza pieza) {
        tablero[fila][col] = pieza;
    }

    public void coronacionPeon(Pieza pieza, int fila, boolean turno) {
        // Verificar si es un peón y llegó al final del tablero
        if (pieza.getTipo().equals("Peon")) {
            if ((pieza.getColor().equals("Blanco") && fila == 0) || (pieza.getColor().equals("Negro") && fila == 7)) {
                // Coronación
                String[] opciones = {"Reina", "Torre", "Alfil", "Caballo"};
                String seleccion = (String) JOptionPane.showInputDialog(
                        null,
                        "Elige la pieza a la que quieres coronar el peón:",
                        "Coronación",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);
                String color = turno ? "white_" : "black_";
                
                /****************************************
                CAMBIEN LAS PATHS DE ACUERDO A SU ENTORNO
                DEL SWITCH DE AQUI ADELANTE PARA QUE SE
                CARGUEN BIEN LAS IMÁGENES
                ****************************************/

                if (seleccion != null) {
                    switch (seleccion) {
                        case "Reina":
                            pieza.setTipo("Reina");
                            pieza.setImagenPath("C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\" + color + "queen.png");
                            break;
                        case "Torre":
                            pieza.setTipo("Torre");
                            pieza.setImagenPath("C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\" + color + "rook.png");
                            break;
                        case "Alfil":
                            pieza.setTipo("Alfil");
                            pieza.setImagenPath("C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\" + color + "bishop.png");
                            break;
                        case "Caballo":
                            pieza.setTipo("Caballo");
                            pieza.setImagenPath("C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\" + color + "knight.png");
                            break;
                    }
                }
            }
        }
    }
}
