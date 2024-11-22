package Clases;

import Formularios.TableroAjedrez;
import javax.swing.JOptionPane;

/**
 *
 * @author luigi
 */
public class Tablero {

    private Pieza[][] tablero; //8x8 matriz para almacenar las piezas
    private int[] peonDobleCasilla; // Guarda la posición del peón que se movió dos casillas
    private int[] posicionReyBlanco;
    private int[] posicionReyNegro;
    
    boolean reyBlancoMovido = false;
    boolean reyNegroMovido = false;
    boolean torreBlancaIzquierdaMovida = false;
    boolean torreBlancaDerechaMovida = false;
    boolean torreNegraIzquierdaMovida = false;
    boolean torreNegraDerechaMovida = false;

    private String angel = "G:\\CUCEI\\5 Semestre\\SSP de Ingeniería de Software I\\Prueba-Ing-Software\\Chess2.mx\\build\\classes\\Images\\";
    private String chava = "C:\\Users\\ChavaR\\Desktop\\QUINTO SEMESTRE\\SEMINARIO DE INGENIERIA DE SOFTWARE I\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\";
    private String ivan = "";
    
    
    // Constructor
    public Tablero() {
        tablero = new Pieza[8][8];
        peonDobleCasilla = null;
        inicializarTablero();
        posicionReyBlanco = new int[]{7, 4};
        posicionReyNegro = new int[]{0, 4};
    }

    //Inicializar el tablero con las piezas en las posiciones iniciales
    private void inicializarTablero() {
        //Path base para las imagenes

        /**
         * **************************************
         * CAMBIEN EL VALOR DE directorioBase PARA QUE SE CARGUEN BIEN LAS
         * IMÁGENES **************************************
         */
       
        
        String directorioBase = angel;

        //Piezas principales
        String[] piezasPrincipalesNegro = {"black_rook.png", "black_knight.png", "black_bishop.png", "black_queen.png", "black_king.png", "black_bishop.png", "black_knight.png", "black_rook.png"};
        String[] nombresPiezasNegro = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        String[] piezasPrincipalesBlanco = {"white_rook.png", "white_knight.png", "white_bishop.png", "white_queen.png", "white_king.png", "white_bishop.png", "white_knight.png", "white_rook.png"};
        String[] nombresPiezasBlanco = {"Torre", "Caballo", "Alfil", "Reina", "Rey", "Alfil", "Caballo", "Torre"};

        //Conf de piezas principales (fila 0 para negras, fila 7 para blancas)
        for (int col = 0; col < 8; col++) {
            //Piezas negras
            tablero[0][col] = new Pieza(nombresPiezasNegro[col], "Negro", directorioBase + piezasPrincipalesNegro[col]);
            tablero[0][col].setPosicion(new int[]{0, col});
            //Piezas blancas
            tablero[7][col] = new Pieza(nombresPiezasBlanco[col], "Blanco", directorioBase + piezasPrincipalesBlanco[col]);
            tablero[7][col].setPosicion(new int[]{7, col});
        }

        //Guardar las posiciones del rey (sirve para lógica del jaque)
        tablero[0][4].setPosicion(new int[]{0, 4});
        tablero[7][4].setPosicion(new int[]{7, 4});

        //Configuración de los peones
        for (int col = 0; col < 8; col++) {
            //Peones negros
            tablero[1][col] = new Pieza("Peon", "Negro", directorioBase + "black_pawn.png");
            tablero[1][col].setPosicion(new int[]{1, col});
            //Peones blancos
            tablero[6][col] = new Pieza("Peon", "Blanco", directorioBase + "white_pawn.png");
            tablero[6][col].setPosicion(new int[]{6, col});

        }
    }

    //Obtener pieza en una posición específica
    public Pieza getPieza(int fila, int col) {
        return tablero[fila][col];
    }

    //Establecer una pieza en una posición específica
    public void setPieza(int fila, int col, Pieza pieza) {
        tablero[fila][col] = pieza;
    }

    public int[] getPeonDobleCasilla() {
        return peonDobleCasilla;
    }

    public void setPeonDobleCasilla(int[] peonDobleCasilla) {
        this.peonDobleCasilla = peonDobleCasilla;
    }

    public void coronacionPeon(Pieza pieza, int fila, boolean turno) {
        //Verificar si es un peón y llegó al final del tablero
        if (pieza.getTipo().equals("Peon")) {
            if ((pieza.getColor().equals("Blanco") && fila == 0) || (pieza.getColor().equals("Negro") && fila == 7)) {
                //Coronación
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

                /**
                 * **************************************
                 * CAMBIEN LAS PATHS DE ACUERDO A SU ENTORNO DEL SWITCH DE AQUI
                 * ADELANTE PARA QUE SE CARGUEN BIEN LAS IMÁGENES
                 * **************************************
                 */
                
                String pathCoronacionPeon = angel;
                
                
                if (seleccion != null) {
                    switch (seleccion) {
                        case "Reina":
                            pieza.setTipo("Reina");
                            pieza.setImagenPath(pathCoronacionPeon + color + "queen.png");
                            break;
                        case "Torre":
                            pieza.setTipo("Torre");
                            pieza.setImagenPath(pathCoronacionPeon + color + "rook.png");
                            break;
                        case "Alfil":
                            pieza.setTipo("Alfil");
                            pieza.setImagenPath(pathCoronacionPeon + color + "bishop.png");
                            break;
                        case "Caballo":
                            pieza.setTipo("Caballo");
                            pieza.setImagenPath(pathCoronacionPeon + color + "knight.png");
                            break;
                    }
                }
            }
        }
    }

    public void capturaAlPaso(Pieza pieza, int fila, int col, int casillaSeleccionadaY) {
        // Captura al paso: eliminar el peón capturado si aplica
        if (pieza.getTipo().equals("Peon") && this.getPeonDobleCasilla() != null) {
            int[] peonDoble = this.getPeonDobleCasilla();
            if (peonDoble[0] == casillaSeleccionadaY && peonDoble[1] == col) {
                this.setPieza(peonDoble[0], peonDoble[1], null); // Elimina el peón capturado
            }
        }

        // Actualizar posición del peón si hizo un movimiento doble
        if (pieza.getTipo().equals("Peon") && Math.abs(fila - casillaSeleccionadaY) == 2) {
            this.setPeonDobleCasilla(new int[]{fila, col});
        } else {
            this.setPeonDobleCasilla(null); // Resetear si no es un movimiento doble
        }
    }

    public int getFilaReyBlanco() {
        return posicionReyBlanco[0];
    }

    public int getFilaReyNegro() {
        return posicionReyNegro[0];

    }

    public int getColReyBlanco() {
        return posicionReyBlanco[1];

    }

    public int getColReyNegro() {
        return posicionReyNegro[1];
    }

    public void setPosicionReyBlanco(int[] nuevaPosicion) {
        posicionReyBlanco = nuevaPosicion;
    }

    public void setPosicionReyNegro(int[] nuevaPosicion) {
        posicionReyNegro = nuevaPosicion;
    }

    public boolean estaEnJaque(int filaRey, int colRey, String colorRey, Tablero tablero) {
        // Recorremos todo el tablero para buscar piezas enemigas
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = tablero.getPieza(fila, col);
                if (pieza != null && !pieza.getColor().equals(colorRey)) {
                    // Si es una pieza enemiga, verificamos si puede atacar al rey
                    if (pieza.validarMovimiento(fila, col, filaRey, colRey, tablero, pieza.getTipo(), pieza.getColor())) {
                        // Si la pieza puede atacar al rey, el rey está en jaque
                        return true;
                    }
                }
            }
        }
        return false; // El rey no está en jaque
    }
    
    
    public boolean isCasillaBajoAtaque(int fila, int col, String color) {
        // Iterar sobre las piezas del tablero y verificar si alguna puede atacar la casilla.
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza pieza = getPieza(f, c);
                if (pieza != null && !pieza.getColor().equals(color)) {
                    if (pieza.validarMovimiento(f, c, fila, col, this, pieza.getTipo(), pieza.getColor())) {
                        return true; // La casilla está bajo ataque
                    }
                }
            }
        }
        return false;
    }

    // Métodos para actualizar el estado del rey y las torres
    public void setReyBlancoMovido(boolean movido) {
        this.reyBlancoMovido = movido;
    }

    public void setReyNegroMovido(boolean movido) {
        this.reyNegroMovido = movido;
    }

    // Similar para las torres
    public void setTorreBlancaIzquierdaMovida(boolean movida) {
        this.torreBlancaIzquierdaMovida = movida;
    }

    public void setTorreBlancaDerechaMovida(boolean movida) {
        this.torreBlancaDerechaMovida = movida;
    }

    public void setTorreNegraIzquierdaMovida(boolean movida) {
        this.torreNegraIzquierdaMovida = movida;
    }

    public void setTorreNegraDerechaMovida(boolean movida) {
        this.torreNegraDerechaMovida = movida;
    }
    

}
