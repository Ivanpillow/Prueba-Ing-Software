
package Clases;

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
        String directorioBase = "C:\\Users\\ab_st\\OneDrive\\Documentos\\NetBeansProjects\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\";

        //piezas principales
        String[] piezasPrincipalesNegro = {"black_rook.png", "black_knight.png", "black_bishop.png", "black_queen.png", "black_king.png", "black_bishop.png", "black_knight.png", "black_rook.png"};
        String[] nombresPiezasNegro = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        String[] piezasPrincipalesBlanco = {"white_rook.png", "white_knight.png", "white_bishop.png", "white_queen.png", "white_king.png", "white_bishop.png", "white_knight.png", "white_rook.png"};
        String[] nombresPiezasBlanco = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        //Conf de piezas principales (fila 0 para negras, fila 7 para blancas)
        for (int col = 0; col < 8; col++) {
            // Piezas negras
            tablero[0][col] = new Pieza(nombresPiezasNegro[col], "Negro", directorioBase + piezasPrincipalesNegro[col]);
            // Piezas blancas
            tablero[7][col] = new Pieza(nombresPiezasBlanco[col], "Blanco", directorioBase + piezasPrincipalesBlanco[col]);
        }

        // Configuración de los peones
        for (int col = 0; col < 8; col++) {
            // Peones negros
            tablero[1][col] = new Pieza("Peon", "Negro", directorioBase + "black_pawn.png");
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
}
