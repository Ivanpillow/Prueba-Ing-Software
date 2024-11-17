/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        tablero[0][0] = new Pieza("Torre", "Negro", "/Images/black_rook.png");
        tablero[0][1] = new Pieza("Caballo", "Negro", "/Images/black_knight.png");
        tablero[0][2] = new Pieza("Alfil", "Negro", "/Images/black_bishop.png");
        tablero[0][3] = new Pieza("Reina", "Negro", "/Images/black_queen.png");
        tablero[0][4] = new Pieza("Rey", "Negro", "/Images/black_king.png");
        tablero[0][5] = new Pieza("Alfil", "Negro", "/Images/black_bishop.png");
        tablero[0][6] = new Pieza("Caballo", "Negro", "/Images/black_knight.png");
        tablero[0][7] = new Pieza("Torre", "Negro", "/Images/black_rook.png");

        tablero[1][0] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][1] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][2] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][3] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][4] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][5] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][6] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");
        tablero[1][7] = new Pieza("Peon", "Negro", "/Images/black_pawn.png");

        tablero[6][0] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][1] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][2] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][3] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][4] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][5] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][6] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");
        tablero[6][7] = new Pieza("Peon", "Blanco", "/Images/white_pawn.png");

        tablero[7][0] = new Pieza("Torre", "Blanco", "/Images/white_rook.png");
        tablero[7][1] = new Pieza("Caballo", "Blanco", "/Images/white_knight.png");
        tablero[7][2] = new Pieza("Alfil", "Blanco", "/Images/white_bishop.png");
        tablero[7][3] = new Pieza("Reina", "Blanco", "/Images/white_queen.png");
        tablero[7][4] = new Pieza("Rey", "Blanco", "/Images/white_king.png");
        tablero[7][5] = new Pieza("Alfil", "Blanco", "/Images/white_bishop.png");
        tablero[7][6] = new Pieza("Caballo", "Blanco", "/Images/white_knight.png");
        tablero[7][7] = new Pieza("Torre", "Blanco", "/Images/white_rook.png");

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
