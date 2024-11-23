package Clases;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luigi
 */
public class Pieza {

    private String tipo;
    private String color;
    private String imagenPath;
    private int[] posicion;

    public Pieza(String tipo, String color, String imagenPath) {
        this.tipo = tipo;
        this.color = color;
        this.imagenPath = imagenPath;
        this.posicion = null;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setImagenPath(String path) {
        this.imagenPath = path;
    }

    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    // Validar movimientos según el tipo de pieza
    public boolean validarMovimiento(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero, String tipoPieza, String colorPieza) {
        switch (tipoPieza) {
            case "Peon":
                return validarMovimientoPeon(origenFila, origenCol, destinoFila, destinoCol, tablero);
            case "Torre":
                return validarMovimientoTorre(origenFila, origenCol, destinoFila, destinoCol, tablero);
            case "Alfil":
                return validarMovimientoAlfil(origenFila, origenCol, destinoFila, destinoCol, tablero);
            case "Caballo":
                return validarMovimientoCaballo(origenFila, origenCol, destinoFila, destinoCol, tablero);
            case "Reina":
                return validarMovimientoReina(origenFila, origenCol, destinoFila, destinoCol, tablero);
            case "Rey":
                return validarMovimientoRey(origenFila, origenCol, destinoFila, destinoCol, tablero, colorPieza);
            default:
                return false;
        }
    }

    private boolean validarMovimientoPeon(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        int direccion = color.equals("Blanco") ? -1 : 1; // Blanco sube, negro baja

        // Movimiento hacia adelante (una o dos casillas)
        if (origenCol == destinoCol && tablero.getPieza(destinoFila, destinoCol) == null) {
            // Movimiento normal o doble
            return destinoFila - origenFila == direccion
                    || (origenFila == (color.equals("Blanco") ? 6 : 1) && destinoFila - origenFila == 2 * direccion);
        }

        // Captura en diagonal
        if (Math.abs(destinoCol - origenCol) == 1 && destinoFila - origenFila == direccion) {
            // Captura normal
            if (tablero.getPieza(destinoFila, destinoCol) != null
                    && !tablero.getPieza(destinoFila, destinoCol).getColor().equals(color)) {
                return true;
            }

            // Captura al paso
            if (tablero.getPeonDobleCasilla() != null) {
                int[] peonDoble = tablero.getPeonDobleCasilla();
                if (peonDoble[0] == origenFila && peonDoble[1] == destinoCol) { // Peón adyacente hizo un movimiento doble
                    return true;
                }
            }
        }

        return false;
    }

    private boolean validarMovimientoTorre(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        // Verificar si el movimiento es en línea recta
        if (origenFila != destinoFila && origenCol != destinoCol) {
            return false; // No es un movimiento válido para la torre
        }

        // Verificar que no haya piezas bloqueando el camino
        if (origenFila == destinoFila) {
            // Movimiento horizontal
            int paso = origenCol < destinoCol ? 1 : -1; // Dirección del movimiento
            for (int col = origenCol + paso; col != destinoCol; col += paso) {
                if (tablero.getPieza(origenFila, col) != null) {
                    return false; // Hay una pieza bloqueando el camino
                }
            }
        } else {
            // Movimiento vertical
            int paso = origenFila < destinoFila ? 1 : -1; // Dirección del movimiento
            for (int fila = origenFila + paso; fila != destinoFila; fila += paso) {
                if (tablero.getPieza(fila, origenCol) != null) {
                    return false; // Hay una pieza bloqueando el camino
                }
            }
        }

        // Verificar la casilla de destino
        Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
        if (piezaDestino != null && piezaDestino.getColor().equals(tablero.getPieza(origenFila, origenCol).getColor())) {
            return false; // No puede capturar una pieza del mismo color
        }

        return true; // Movimiento válido
    }

    private boolean validarMovimientoAlfil(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        // Verifica que el movimiento sea diagonal
        if (Math.abs(destinoFila - origenFila) != Math.abs(destinoCol - origenCol)) {
            return false; // No es un movimiento diagonal
        }

        // Determina la dirección de movimiento en filas y columnas
        int direccionFila = (destinoFila > origenFila) ? 1 : -1;
        int direccionCol = (destinoCol > origenCol) ? 1 : -1;

        // Verifica que no haya piezas en la trayectoria
        int filaActual = origenFila + direccionFila;
        int colActual = origenCol + direccionCol;
        while (filaActual != destinoFila && colActual != destinoCol) {
            if (tablero.getPieza(filaActual, colActual) != null) {
                return false; // Hay una pieza en el camino
            }
            filaActual += direccionFila;
            colActual += direccionCol;
        }

        // Verifica si el destino tiene una pieza enemiga o está vacío
        Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
        if (piezaDestino == null || !piezaDestino.getColor().equals(this.color)) {
            return true; // El destino está vacío o tiene una pieza enemiga
        }

        return false; // El destino tiene una pieza aliada
    }

    private boolean validarMovimientoCaballo(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        // Verifica que el movimiento sea en forma de L
        int deltaFila = Math.abs(destinoFila - origenFila);
        int deltaCol = Math.abs(destinoCol - origenCol);

        /*
        System.out.println("Origen:" + origenFila + ", " + origenCol);
        System.out.println("Destino:" + destinoFila + ", " + destinoCol);
        
        System.out.println("DeltaFila: " + deltaFila);
        System.out.println("DeltaCol: " + deltaCol + "\n");
         */
        // El caballo debe mover 2 casillas en una dirección y 1 en la otra
        if (!(deltaFila == 2 && deltaCol == 1) && !(deltaFila == 1 && deltaCol == 2)) {
            return false; // El movimiento no es en forma de L
        }

        // Verifica que el destino no tenga una pieza del mismo color
        Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
        if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
            return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
        }

        return true; // El movimiento es válido
    }

    private boolean validarMovimientoReina(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        // Verifica si el movimiento es horizontal (misma fila)
        if (origenFila == destinoFila) {
            int minCol = Math.min(origenCol, destinoCol);
            int maxCol = Math.max(origenCol, destinoCol);
            // Verifica que no haya piezas en el camino
            for (int col = minCol + 1; col < maxCol; col++) {
                if (tablero.getPieza(origenFila, col) != null) {
                    return false; // Hay una pieza en el camino
                }
            }
            // Verifica que el destino esté vacío o tenga una pieza del color contrario
            Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
            if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }
            return true;
        }

        // Verifica si el movimiento es vertical (misma columna)
        if (origenCol == destinoCol) {
            int minFila = Math.min(origenFila, destinoFila);
            int maxFila = Math.max(origenFila, destinoFila);
            // Verifica que no haya piezas en el camino
            for (int fila = minFila + 1; fila < maxFila; fila++) {
                if (tablero.getPieza(fila, origenCol) != null) {
                    return false; // Hay una pieza en el camino
                }
            }
            // Verifica que el destino esté vacío o tenga una pieza del color contrario
            Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
            if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }
            return true;
        }

        // Verifica si el movimiento es diagonal (diferencia de filas y columnas igual)
        if (Math.abs(destinoFila - origenFila) == Math.abs(destinoCol - origenCol)) {
            int filaDir = destinoFila > origenFila ? 1 : -1;
            int colDir = destinoCol > origenCol ? 1 : -1;

            int fila = origenFila + filaDir;
            int col = origenCol + colDir;

            // Verifica que no haya piezas en el camino
            while (fila != destinoFila && col != destinoCol) {
                if (tablero.getPieza(fila, col) != null) {
                    return false; // Hay una pieza en el camino
                }
                fila += filaDir;
                col += colDir;
            }

            // Verifica que el destino esté vacío o tenga una pieza del color contrario
            Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
            if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
                return false; // No se puede mover a una casilla ocupada por una pieza del mismo color
            }
            return true;
        }

        // Si el movimiento no es ni horizontal, ni vertical, ni diagonal, no es válido
        return false;
    }

    private boolean validarMovimientoRey(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero, String color) {
        //Verifica que el rey se mueva solo una casilla en cualquier dirección (horizontal, vertical, diagonal)
        if (Math.abs(destinoFila - origenFila) <= 1 && Math.abs(destinoCol - origenCol) <= 1) {
            //Verifica que el destino esté vacío o tenga una pieza del color contrario
            Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
            if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
                return false; //No puede moverse a una casilla ocupada por una pieza del mismo color
            }
            //Actualizar la posición del rey
            this.setPosicion(new int[]{destinoFila, destinoCol});
            //Actualizar la posición en las variables del rey
            if (color.equals("Blanco")) {
                tablero.setPosicionReyBlanco(new int[]{destinoFila, destinoCol});
                tablero.setReyBlancoMovido(true);
            } else if (color.equals("Negro")) {
                tablero.setPosicionReyNegro(new int[]{destinoFila, destinoCol});
                tablero.setReyNegroMovido(true);
            }
            return true;
        }
        
        //Validación del enroque
        if (origenFila == destinoFila && Math.abs(destinoCol - origenCol) == 2) {
            //Determinar el lado del enroque (corto o largo)
            boolean esLadoCorto = destinoCol > origenCol;

            //Verificar las condiciones para el enroque
            if (color.equals("Blanco")) {
                if (tablero.reyBlancoMovido || (esLadoCorto && tablero.torreBlancaDerechaMovida) || (!esLadoCorto && tablero.torreBlancaIzquierdaMovida)) {
                    return false; //Rey o torre ya se movieron
                }
            } else {
                if (tablero.reyNegroMovido || (esLadoCorto && tablero.torreNegraDerechaMovida) || (!esLadoCorto && tablero.torreNegraIzquierdaMovida)) {
                    return false; //Rey o torre ya se movieron
                }
            }

            //Verificar que no haya piezas entre el rey y la torre
            int paso = esLadoCorto ? 1 : -1;
            for (int col = origenCol + paso; col != (esLadoCorto ? 7 : 0); col += paso) {
                if (tablero.getPieza(origenFila, col) != null) {
                    return false; //Hay una pieza bloqueando
                }
            }

            //Verificar que las casillas no estén bajo ataque
            for (int col = origenCol; col != destinoCol + paso; col += paso) {
                if (tablero.isCasillaBajoAtaque(origenFila, col, color)) {
                    return false; //Casilla bajo ataque
                }
            }

            //Realizar el enroque
            Pieza torre = tablero.getPieza(origenFila, esLadoCorto ? 7 : 0);
            tablero.setPieza(origenFila, destinoCol - paso, torre); // Mover la torre
            tablero.setPieza(origenFila, esLadoCorto ? 7 : 0, null);
            torre.setPosicion(new int[]{origenFila, destinoCol - paso});

            //Mover el rey
            this.setPosicion(new int[]{origenFila, destinoCol});
            if (color.equals("Blanco")) {
                tablero.setPosicionReyBlanco(new int[]{origenFila, destinoCol});
                tablero.setReyBlancoMovido(true);
            } else {
                tablero.setPosicionReyNegro(new int[]{origenFila, destinoCol});
                tablero.setReyNegroMovido(true);
            }

            return true;
        }
    
    

        // Si no se cumple la condición, el movimiento no es válido
        return false;
    }
    
    /**
     * Obtiene todos los movimientos válidos para esta pieza desde su posición actual.
     *
     * @param fila    Fila actual de la pieza.
     * @param col     Columna actual de la pieza.
     * @param tablero Objeto Tablero que representa el estado actual del juego.
     * @return Lista de movimientos válidos como pares [fila, columna].
     */
    public List<int[]> obtenerMovimientos(int fila, int col, Tablero tablero) {
        List<int[]> movimientos = new ArrayList<>();

        // Recorremos todas las posiciones del tablero
        for (int i = 0; i < 8; i++) { // Tablero de 8x8
            for (int j = 0; j < 8; j++) {
                // Validamos si movernos a (i, j) es un movimiento válido
                if (validarMovimiento(fila, col, i, j, tablero, this.tipo, this.color)) {
                    movimientos.add(new int[]{i, j}); // Añadimos la posición válida
                }
            }
        }

        return movimientos; // Devolvemos la lista de movimientos válidos
    }

}
