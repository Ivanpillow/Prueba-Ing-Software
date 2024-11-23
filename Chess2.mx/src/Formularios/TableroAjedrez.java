package Formularios;

import Clases.Pieza;
import Clases.Tablero;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luigi
 */
public class TableroAjedrez extends javax.swing.JFrame {

    private Tablero tablero;
    private int casillaSeleccionadaX = -1, casillaSeleccionadaY = -1; // Coordenadas de la casilla seleccionada
    private boolean turnoBlanco = true;

    public TableroAjedrez() {
        // Crear tablero
        tablero = new Tablero();

        // Añadir el panel donde se dibuja el tablero
        PanelTablero panelTablero = new PanelTablero();
        add(panelTablero);

        // Configurar el tamaño preferido del panel
        panelTablero.setPreferredSize(new java.awt.Dimension(640, 640)); // Tamaño exacto del tablero (8x8 casillas de 80px)

        // Ajustar el tamaño del JFrame automáticamente al contenido
        pack();

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
    }

    class PanelTablero extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int tamanoCasilla = 80; // Tamaño de cada casilla
            boolean colorClaro = true; // Usado para alternar colores de las casillas
            for (int fila = 0; fila < 8; fila++) {
                for (int col = 0; col < 8; col++) {
                    // Dibujar las casillas
                    if (colorClaro) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.DARK_GRAY);
                    }
                    g.fillRect(col * tamanoCasilla, fila * tamanoCasilla, tamanoCasilla, tamanoCasilla);
                    colorClaro = !colorClaro;

                    // Dibujar las piezas
                    Pieza pieza = tablero.getPieza(fila, col);
                    if (pieza != null) {
                        Image img = new ImageIcon(pieza.getImagenPath()).getImage();
                        //System.out.println("Cargando imagen desde: " + pieza.getImagenPath());

                        g.drawImage(img, col * tamanoCasilla, fila * tamanoCasilla, tamanoCasilla, tamanoCasilla, this);
                    }
                }
                colorClaro = !colorClaro;
            }
        }
        
        private void reiniciarJuego() {
            // Reiniciar el tablero
            tablero = new Tablero();

            // Reiniciar variables de control
            casillaSeleccionadaX = -1;
            casillaSeleccionadaY = -1;
            turnoBlanco = true;

            // Actualizar el tablero en pantalla
            repaint();
        }
        
        private void verificarEstadoDelJuego() {
            // Determinar el color del jugador actual
            String colorActual = turnoBlanco ? "Blanco" : "Negro";
            String colorOponente = turnoBlanco ? "Negro" : "Blanco";

            // Verificar Jaque Mate
            if (tablero.esJaqueMate(colorOponente)) {
                JOptionPane.showMessageDialog(this,
                        "¡Jaque mate! " + colorActual + " gana el juego.",
                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                reiniciarJuego();
                return;
            }

            // Verificar Rey Ahogado
            if (tablero.esReyAhogado(colorOponente)) {
                JOptionPane.showMessageDialog(this,
                        "¡Empate por rey ahogado!",
                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                reiniciarJuego();
                return;
            }

            // Verificar Tablas por Material Insuficiente
            if (tablero.materialInsuficiente()) {
                JOptionPane.showMessageDialog(this,
                        "¡Empate por material insuficiente!",
                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                reiniciarJuego();
                return;
            }
        }

        public PanelTablero() {
            // Añadir un MouseListener para manejar los clics
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    int fila = y / 80;
                    int col = x / 80;

                    if (casillaSeleccionadaX == -1 && casillaSeleccionadaY == -1) {
                        // Seleccionar pieza
                        Pieza pieza = tablero.getPieza(fila, col);
                        if (pieza != null) {
                            // Validar si la pieza pertenece al turno actual
                            if ((turnoBlanco && pieza.getColor().equals("Blanco"))
                                    || (!turnoBlanco && pieza.getColor().equals("Negro"))) {
                                casillaSeleccionadaX = col;
                                casillaSeleccionadaY = fila;
                            } else {
                                String jugador = turnoBlanco ? "Blancas" : "Negras";
                                JOptionPane.showMessageDialog(TableroAjedrez.this,
                                        "Es el turno de " + jugador, "Turno incorrecto", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } else {
                         // Mover pieza seleccionada
                        Pieza pieza = tablero.getPieza(casillaSeleccionadaY, casillaSeleccionadaX);

                        if (pieza != null && pieza.getColor().equals(turnoBlanco ? "Blanco" : "Negro")) {
                            // Obtener posición del rey del jugador actual
                            int filaRey = (turnoBlanco) ? tablero.getFilaReyBlanco() : tablero.getFilaReyNegro();
                            int colRey = (turnoBlanco) ? tablero.getColReyBlanco() : tablero.getColReyNegro();

                            // Validar si el rey está en jaque
                            boolean reyEnJaque = tablero.estaEnJaque(filaRey, colRey, turnoBlanco ? "Blanco" : "Negro", tablero);

                            // Intentar movimiento
                            boolean movimientoValido = pieza.validarMovimiento(casillaSeleccionadaY, casillaSeleccionadaX, fila, col, tablero, pieza.getTipo(), pieza.getColor());

                            if (movimientoValido) {
                                // Simular el movimiento
                                Pieza piezaOriginal = tablero.getPieza(fila, col);
                                tablero.setPieza(fila, col, pieza);
                                tablero.setPieza(casillaSeleccionadaY, casillaSeleccionadaX, null);
                                
                                // Verificar si el movimiento pone al rey enemigo en jaque
                                int filaReyEnemigo = (!turnoBlanco) ? tablero.getFilaReyBlanco() : tablero.getFilaReyNegro();
                                int colReyEnemigo = (!turnoBlanco) ? tablero.getColReyBlanco() : tablero.getColReyNegro();

                                if (tablero.estaEnJaque(filaReyEnemigo, colReyEnemigo, !turnoBlanco ? "Blanco" : "Negro", tablero)) {
                                    JOptionPane.showMessageDialog(TableroAjedrez.this,
                                            "¡El rey " + (!turnoBlanco ? "Blanco" : "Negro") + " está en peligro!", "Jaque", JOptionPane.ERROR_MESSAGE);
                                }

                                // Verificar si el movimiento elimina el jaque
                                boolean eliminaJaque = !tablero.estaEnJaque(filaRey, colRey, turnoBlanco ? "Blanco" : "Negro", tablero);

                                // Revertir el movimiento si no elimina el jaque
                                if (reyEnJaque && !eliminaJaque) {
                                    tablero.setPieza(casillaSeleccionadaY, casillaSeleccionadaX, pieza);
                                    tablero.setPieza(fila, col, piezaOriginal);
                                    JOptionPane.showMessageDialog(TableroAjedrez.this,
                                            "Debes realizar un movimiento que elimine el jaque.", "Movimiento inválido", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    // Realizar el movimiento de manera definitiva
                                    tablero.setPieza(fila, col, pieza);
                                    tablero.setPieza(casillaSeleccionadaY, casillaSeleccionadaX, null);

                                    // Cambiar turno
                                    turnoBlanco = !turnoBlanco;

                                    // Verificar promoción de peón
                                    tablero.coronacionPeon(pieza, fila, turnoBlanco);
                                    
                                    //verificarEstadoDelJuego();

                                    repaint();
                                }
                            } else {
                                JOptionPane.showMessageDialog(TableroAjedrez.this,
                                        "El movimiento realizado no es válido", "Movimiento inválido", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        casillaSeleccionadaX = -1;
                        casillaSeleccionadaY = -1;
                    }
                }

            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableroAjedrez().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
