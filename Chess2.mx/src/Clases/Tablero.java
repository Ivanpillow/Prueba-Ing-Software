package Clases;

import Formularios.TableroAjedrez;
import java.util.List;
import java.util.ArrayList;
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
    private String ivan = "C:\\Users\\ab_st\\OneDrive\\Documentos\\NetBeansProjects\\Prueba-Ing-Software\\Chess2.mx\\src\\Images\\";

    // Constructor
    public Tablero(int player1Id, int player2Id, String colorPlayer2) {
        tablero = new Pieza[8][8];
        peonDobleCasilla = null;
        
        
        //System.out.println("JUGADOR 1: " + player1Id);
        //System.out.println("JUGADOR 2: " + player2Id);
        //System.out.println("COLOR JUGADOR 2: " + colorPlayer2);

        /*INICIALIZAR EN TABLERO EN DIFERENTES ESCENARIOS
        PARA QUE SEA MÁS RÁPIDA LA DEMOSTRACIÓN DE LAS
        DIFERENTES FUNCIONALIDADES*/
        //inicializarTablero();
        //inicializarTableroMaterialInsuficiente();
        inicializarTableroJaqueMate();

        posicionReyBlanco = new int[]{7, 4};
        posicionReyNegro = new int[]{0, 4};
    }

    //Inicializar el tablero con las piezas en las posiciones iniciales
    public void inicializarTablero() {
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

    public void limpiarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                tablero[fila][col] = null;
            }
        }

    }

    //Inicializar tablero en un escenaario de material insuficiente
    private void inicializarTableroMaterialInsuficiente() {
        this.limpiarTablero();

        // Path base para las imágenes
        String directorioBase = angel;

        /*
        // Caso 1: Solo dos reyes en el tablero (tablas por material insuficiente)
        tablero[0][4] = new Pieza("Rey", "Negro", directorioBase + "black_king.png");
        tablero[0][4].setPosicion(new int[]{0, 4});

        tablero[7][4] = new Pieza("Rey", "Blanco", directorioBase + "white_king.png");
        tablero[7][4].setPosicion(new int[]{7, 4});
         */
 /*
        // Caso 2: Rey blanco y un caballo contra el rey negro
        tablero[1][1] = new Pieza("Rey", "Negro", directorioBase + "black_king.png");
        tablero[1][1].setPosicion(new int[]{1, 1});

        tablero[6][6] = new Pieza("Rey", "Blanco", directorioBase + "white_king.png");
        tablero[6][6].setPosicion(new int[]{6, 6});

        tablero[5][5] = new Pieza("Caballo", "Blanco", directorioBase + "white_knight.png");
        tablero[5][5].setPosicion(new int[]{5, 5});
         */
        // Caso 3: Rey blanco y un alfil contra el rey negro
        tablero[2][2] = new Pieza("Rey", "Negro", directorioBase + "black_king.png");
        tablero[2][2].setPosicion(new int[]{2, 2});

        tablero[7][7] = new Pieza("Rey", "Blanco", directorioBase + "white_king.png");
        tablero[7][7].setPosicion(new int[]{7, 7});

        tablero[5][3] = new Pieza("Alfil", "Blanco", directorioBase + "white_bishop.png");
        tablero[5][3].setPosicion(new int[]{5, 3});
    }

    // Inicializar tablero en un escenario de jaque mate
    private void inicializarTableroJaqueMate() {
        this.limpiarTablero();

        // Path base para las imágenes
        String directorioBase = angel;

        // Escenario de jaque mate
        // Colocamos al rey blanco en una posición donde está en jaque mate
        tablero[6][3] = new Pieza("Reina", "Negro", directorioBase + "black_queen.png");
        tablero[6][3].setPosicion(new int[]{6, 3});

        tablero[5][7] = new Pieza("Torre", "Negro", directorioBase + "black_rook.png");
        tablero[5][7].setPosicion(new int[]{5, 5});

        tablero[4][4] = new Pieza("Peon", "Negro", directorioBase + "black_pawn.png");
        tablero[4][4].setPosicion(new int[]{4, 4});

        tablero[6][7] = new Pieza("Rey", "Blanco", directorioBase + "white_king.png");
        tablero[6][7].setPosicion(new int[]{7, 4});
    }


    //Obtener pieza en una posición específica
    public Pieza getPieza(int fila, int col) {
        // Verificar límites válidos del tablero
        if (fila < 0 || fila >= 8 || col < 0 || col >= 8) {
            return null; // Devuelve null si la posición está fuera de rango
        }
        return tablero[fila][col];
    }

    //Establecer una pieza en una posición específica
    public void setPieza(int fila, int col, Pieza pieza) {
        if(fila < 0 || fila >= 8 || col < 0 || col >= 8){
            return;
        } else{
            tablero[fila][col] = pieza;
        }
        
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
                String color = pieza.getColor().equals("Blanco") ? "white_" : "black_";

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

    //Verificar si los reyes se movieron durante la partida o no(para el enroque)
    public void setReyBlancoMovido(boolean movido) {
        this.reyBlancoMovido = movido;
    }

    public void setReyNegroMovido(boolean movido) {
        this.reyNegroMovido = movido;
    }

    //Verificar si las torres se movieron durante la partida o no(para el enroque)
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

    //Esta funcion es el nucleo que controla los movimientos de las piezas, verifica los jaques y movimientos legales
    public boolean moverPieza(int filaInicial, int colInicial, int filaFinal, int colFinal, String colorRey) {
        Pieza pieza = tablero[filaInicial][colInicial];

        if (pieza == null || !pieza.getColor().equals(colorRey)) {
            System.out.println("Movimiento inválido: no es tu pieza.");
            return false;
        }

        // Verificar si el rey está en jaque
        boolean enJaque = estaEnJaque(
                colorRey.equals("Blanco") ? posicionReyBlanco[0] : posicionReyNegro[0],
                colorRey.equals("Blanco") ? posicionReyBlanco[1] : posicionReyNegro[1],
                colorRey,
                this
        );

        if (enJaque) {
            // Obtener movimientos válidos
            List<int[]> movimientosValidos = obtenerMovimientosValidos(filaInicial, colInicial, colorRey);

            // Verificar si el movimiento seleccionado está entre los válidos
            boolean movimientoPermitido = movimientosValidos.stream().anyMatch(
                    mov -> mov[0] == filaFinal && mov[1] == colFinal
            );

            if (!movimientoPermitido) {
                System.out.println("Movimiento inválido: no elimina el jaque.");
                return false;
            }
        }

        // Si no está en jaque o el movimiento es válido, realizar el movimiento
        tablero[filaFinal][colFinal] = pieza;
        tablero[filaInicial][colInicial] = null;

        // Si se movió el rey, actualizar su posición
        if (pieza.getTipo().equals("Rey")) {
            if (colorRey.equals("Blanco")) {
                posicionReyBlanco = new int[]{filaFinal, colFinal};
            } else {
                posicionReyNegro = new int[]{filaFinal, colFinal};
            }
        }

        return true;
    }

    //Funcion auxiliar de moverPieza para cuando se este en jaque
    public boolean movimientoEvitaJaque(int filaInicial, int colInicial, int filaFinal, int colFinal, String colorRey) {
        // Almacenar el estado original
        Pieza piezaOrigen = tablero[filaInicial][colInicial];
        Pieza piezaDestino = tablero[filaFinal][colFinal];

        // Simular el movimiento
        tablero[filaFinal][colFinal] = piezaOrigen;
        tablero[filaInicial][colInicial] = null;

        // Si se está moviendo el rey, actualizamos temporalmente su posición
        int[] posicionOriginalRey = null;
        if (piezaOrigen.getTipo().equals("Rey")) {
            if (colorRey.equals("Blanco")) {
                posicionOriginalRey = posicionReyBlanco;
                posicionReyBlanco = new int[]{filaFinal, colFinal};
            } else {
                posicionOriginalRey = posicionReyNegro;
                posicionReyNegro = new int[]{filaFinal, colFinal};
            }
        }

        // Verificar si el rey sigue en jaque
        boolean sigueEnJaque = estaEnJaque(
                colorRey.equals("Blanco") ? posicionReyBlanco[0] : posicionReyNegro[0],
                colorRey.equals("Blanco") ? posicionReyBlanco[1] : posicionReyNegro[1],
                colorRey,
                this
        );

        // Revertir el movimiento
        tablero[filaInicial][colInicial] = piezaOrigen;
        tablero[filaFinal][colFinal] = piezaDestino;

        // Restaurar la posición del rey si fue temporalmente actualizada
        if (piezaOrigen.getTipo().equals("Rey") && posicionOriginalRey != null) {
            if (colorRey.equals("Blanco")) {
                posicionReyBlanco = posicionOriginalRey;
            } else {
                posicionReyNegro = posicionOriginalRey;
            }
        }

        // Retornar si el movimiento elimina el jaque
        return !sigueEnJaque;
    }

    //Esta funcion verificar movimientosd legales cuando se esta en jaque
    public List<int[]> obtenerMovimientosValidos(int fila, int col, String colorRey) {
        List<int[]> movimientosValidos = new ArrayList<>();
        Pieza pieza = tablero[fila][col];

        if (pieza == null || !pieza.getColor().equals(colorRey)) {
            return movimientosValidos; // Si no hay pieza o es del otro color, no hay movimientos
        }

        // Obtener todos los movimientos posibles de la pieza
        List<int[]> movimientosPosibles = pieza.obtenerMovimientos(fila, col, this);

        // Verificar cuáles movimientos eliminan el jaque
        for (int[] movimiento : movimientosPosibles) {
            int filaDestino = movimiento[0];
            int colDestino = movimiento[1];
            if (movimientoEvitaJaque(fila, col, filaDestino, colDestino, colorRey)) {
                movimientosValidos.add(movimiento);
            }
        }

        return movimientosValidos;
    }

    public boolean esJaqueMate(String color) {
        // Obtener las posiciones actuales del rey del color dado
        int filaRey = (color.equals("Blanco")) ? getFilaReyBlanco() : getFilaReyNegro();
        int colRey = (color.equals("Blanco")) ? getColReyBlanco() : getColReyNegro();

        // Verificar si el rey está en jaque
        if (!estaEnJaque(filaRey, colRey, color, this)) {
            return false; // No es jaque mate si el rey no está en jaque
        }

        // Iterar por todas las piezas del jugador
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = getPieza(fila, col);
                if (pieza != null && pieza.getColor().equals(color)) {
                    // Intentar todos los movimientos legales de la pieza
                    for (int nuevaFila = 0; nuevaFila < 8; nuevaFila++) {
                        for (int nuevaCol = 0; nuevaCol < 8; nuevaCol++) {
                            if (pieza.validarMovimiento(fila, col, nuevaFila, nuevaCol, this, pieza.getTipo(), pieza.getColor())) {
                                // Simular el movimiento
                                Pieza piezaOriginal = getPieza(nuevaFila, nuevaCol);
                                setPieza(nuevaFila, nuevaCol, pieza);
                                setPieza(fila, col, null);

                                // Verificar si el rey sigue en jaque después del movimiento
                                boolean sigueEnJaque = estaEnJaque(filaRey, colRey, color, this);

                                // Revertir el movimiento
                                setPieza(fila, col, pieza);
                                setPieza(nuevaFila, nuevaCol, piezaOriginal);

                                // Si algún movimiento elimina el jaque, no es jaque mate
                                if (!sigueEnJaque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Si no hay movimientos legales que eliminen el jaque, es jaque mate
        return true;
    }

    public boolean esReyAhogado(String color) {
        // Obtener las posiciones actuales del rey del color dado
        int filaRey = (color.equals("Blanco")) ? getFilaReyBlanco() : getFilaReyNegro();
        int colRey = (color.equals("Blanco")) ? getColReyBlanco() : getColReyNegro();

        // Verificar si el rey no está en jaque
        if (estaEnJaque(filaRey, colRey, color, this)) {
            return false; // No es ahogado si el rey está en jaque
        }

        // Iterar por todas las piezas del jugador
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = getPieza(fila, col);
                if (pieza != null && pieza.getColor().equals(color)) {
                    // Intentar todos los movimientos legales de la pieza
                    for (int nuevaFila = 0; nuevaFila < 8; nuevaFila++) {
                        for (int nuevaCol = 0; nuevaCol < 8; nuevaCol++) {
                            if (pieza.validarMovimiento(fila, col, nuevaFila, nuevaCol, this, pieza.getTipo(), pieza.getColor())) {
                                // Simular el movimiento
                                Pieza piezaOriginal = getPieza(nuevaFila, nuevaCol);
                                setPieza(nuevaFila, nuevaCol, pieza);
                                setPieza(fila, col, null);

                                // Verificar si el rey sigue sin estar en jaque después del movimiento
                                boolean sigueEnJaque = estaEnJaque(filaRey, colRey, color, this);

                                // Revertir el movimiento
                                setPieza(fila, col, pieza);
                                setPieza(nuevaFila, nuevaCol, piezaOriginal);

                                // Si algún movimiento es posible, no es ahogado
                                if (!sigueEnJaque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Si no hay movimientos legales y el rey no está en jaque, es ahogado
        return true;
    }

    public boolean materialInsuficiente() {
        // Contadores de piezas
        int peones = 0, torres = 0, damas = 0, caballos = 0, alfiles = 0;

        // Contar piezas en el tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                Pieza pieza = getPieza(fila, col);
                if (pieza != null) {
                    switch (pieza.getTipo()) {
                        case "Peon":
                            peones++;
                            break;
                        case "Torre":
                            torres++;
                            break;
                        case "Dama":
                            damas++;
                            break;
                        case "Caballo":
                            caballos++;
                            break;
                        case "Alfil":
                            alfiles++;
                            break;
                    }
                }
            }
        }

        // Condiciones de material insuficiente
        if (damas == 0 && torres == 0 && peones == 0) {
            // Solo quedan:
            // - Rey contra rey
            // - Rey y alfil contra rey
            // - Rey y caballo contra rey
            if ((caballos <= 1 && alfiles == 0) || (caballos == 0 && alfiles <= 1)) {
                return true;
            }
        }

        return false;
    }
}
