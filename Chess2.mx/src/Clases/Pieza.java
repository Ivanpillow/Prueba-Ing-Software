
package Clases;

/**
 *
 * @author luigi
 */
public class Pieza {

    private String tipo;
    private String color;
    private String imagenPath;

    public Pieza(String tipo, String color, String imagenPath) {
        this.tipo = tipo;
        this.color = color;
        this.imagenPath = imagenPath;
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
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public void setImagenPath(String path){
        this.imagenPath = path;
    }

    // Validar movimientos según el tipo de pieza
    public boolean validarMovimiento(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero, String tipoPieza) {
        int diferenciaFila = Math.abs(destinoFila - origenFila);
        int diferenciaCol = Math.abs(destinoCol - origenCol);

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
                return validarMovimientoRey(origenFila, origenCol, destinoFila, destinoCol, tablero);
            default:
                return false;
        }
    }

    private boolean validarMovimientoPeon(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        int direccion = color.equals("Blanco") ? -1 : 1; // Blanco sube, negro baja
        /*
        origenCol == destinoCol es para verificar que solo se pueda mover de forma vertical
        tablero.getPieza(destinoFila, destinoCol) == null para verificar que el destino a donde se
        quiere mover el peón no haya una pieza
         */
        if (origenCol == destinoCol && tablero.getPieza(destinoFila, destinoCol) == null) {
            // Movimiento hacia adelante
            return destinoFila - origenFila == direccion
                    || (origenFila == (color.equals("Blanco") ? 6 : 1) && destinoFila - origenFila == 2 * direccion);
        } else if (Math.abs(destinoCol - origenCol) == 1 && destinoFila - origenFila == direccion) {
            // Captura en diagonal
            return tablero.getPieza(destinoFila, destinoCol) != null
                    && !tablero.getPieza(destinoFila, destinoCol).getColor().equals(color);
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

    private boolean validarMovimientoRey(int origenFila, int origenCol, int destinoFila, int destinoCol, Tablero tablero) {
        // Verifica que el rey se mueva solo una casilla en cualquier dirección (horizontal, vertical, diagonal)
        if (Math.abs(destinoFila - origenFila) <= 1 && Math.abs(destinoCol - origenCol) <= 1) {
            // Verifica que el destino esté vacío o tenga una pieza del color contrario
            Pieza piezaDestino = tablero.getPieza(destinoFila, destinoCol);
            if (piezaDestino != null && piezaDestino.getColor().equals(this.color)) {
                return false; // No puede moverse a una casilla ocupada por una pieza del mismo color
            }
            return true;
        }

        // Si no se cumple la condición, el movimiento no es válido
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
