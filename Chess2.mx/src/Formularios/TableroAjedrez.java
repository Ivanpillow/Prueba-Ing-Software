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
                        if (pieza != null && pieza.validarMovimiento(casillaSeleccionadaY, casillaSeleccionadaX, fila, col, tablero, pieza.getTipo())) {
                            //Si se quiere mover un peón, checar si se puede hacer captura al paso
                            if (pieza.getTipo().equals("Peon")) {
                                tablero.capturaAlPaso(pieza, fila, col, casillaSeleccionadaY);
                            }

                            // Realizar el movimiento
                            tablero.setPieza(fila, col, pieza);
                            tablero.setPieza(casillaSeleccionadaY, casillaSeleccionadaX, null);

                            // Función que se encarga de promocionar el peón
                            tablero.coronacionPeon(pieza, fila, turnoBlanco);

                            // Cambiar turno
                            turnoBlanco = !turnoBlanco;
                            String jugador = turnoBlanco ? "Blancas" : "Negras";
                            JOptionPane.showMessageDialog(TableroAjedrez.this,
                                    "Turno de piezas " + jugador, "Cambio de turno", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(TableroAjedrez.this,
                                    "El movimiento realizado no es válido", "Movimiento inválido: " + pieza.getTipo(), JOptionPane.ERROR_MESSAGE);
                        }
                        casillaSeleccionadaX = -1;
                        casillaSeleccionadaY = -1;
                        repaint();
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
