package Formularios;

import Clases.ConexionDB;
import Clases.Pieza;
import Clases.Tablero;
import Clases.consultas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author luigi
 */
public class TableroAjedrez extends javax.swing.JFrame {

    private Tablero tablero;
    private int casillaSeleccionadaX = -1, casillaSeleccionadaY = -1; // Coordenadas de la casilla seleccionada
    private boolean turnoBlanco = true;
    private boolean finDelJuego = false;
    
    private int player1Id = 0;
    private int player2Id = 0;
    private String colorPlayer2 = "";
    consultas con = new consultas();
    ConexionDB db = new ConexionDB();
    Connection cn = db.conectar();
                                            

    public TableroAjedrez(int player1Id, int player2Id, String colorPlayer2) {
        //Crear tablero
        tablero = new Tablero(player1Id, player2Id, colorPlayer2);
        
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.colorPlayer2 = colorPlayer2;
        
        String NameHeader1 = con.obtenerUsuario(player1Id, cn);
        String NameHeader2 = con.obtenerUsuario(player2Id, cn); 
        
        // Configurar la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

         // Crear etiquetas para mostrar nombres de jugadores
        JLabel lblPlayer1 = new JLabel("", SwingConstants.CENTER);
        lblPlayer1.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlayer1.setPreferredSize(new Dimension(640, 30)); // Altura de 30px
        lblPlayer1.setOpaque(true);
        lblPlayer1.setBackground(Color.WHITE);

        JLabel lblPlayer2 = new JLabel("", SwingConstants.CENTER);
        lblPlayer2.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlayer2.setPreferredSize(new Dimension(640, 30)); // Altura de 30px
        lblPlayer2.setOpaque(true);
        lblPlayer2.setBackground(Color.WHITE);

        // Validación según el color de Player2
        if (colorPlayer2.equalsIgnoreCase("blancas")) {
            lblPlayer1.setText(NameHeader1); 
            lblPlayer2.setText(NameHeader2); 
        } else if (colorPlayer2.equalsIgnoreCase("negras")) {
            lblPlayer1.setText(NameHeader2); 
            lblPlayer2.setText(NameHeader1);
        } else {
            lblPlayer1.setText("Jugador 1");
            lblPlayer2.setText("Jugador 2"); 
        }
        
        // Añadir el panel donde se dibuja el tablero
        PanelTablero panelTablero = new PanelTablero(player1Id, player2Id, colorPlayer2);
        
         // Añadir componentes al layout principal
        add(lblPlayer1, BorderLayout.NORTH); // Nombre del jugador 1 arriba
        add(panelTablero, BorderLayout.CENTER); // Tablero en el centro
        add(lblPlayer2, BorderLayout.SOUTH); // Nombre del jugador 2 abajo

        // Configurar el tamaño preferido del panel
        panelTablero.setPreferredSize(new java.awt.Dimension(640, 640)); // Tamaño exacto del tablero (8x8 casillas de 80px)
        
        // Ajustar el tamaño del JFrame automáticamente al contenido
        pack();
        setLocationRelativeTo(null);

        
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
            tablero.inicializarTablero();

            // Reiniciar variables de control
            casillaSeleccionadaX = -1;
            casillaSeleccionadaY = -1;
            turnoBlanco = true;

            repaint();
        }

        //  ESTA FUNCION MANDA A LLAMAR LAS VERIFICAIONES EN TABLERO.JAVA E INDICA EL CASO
        private void verificarEstadoDelJuego(int player1Id, int player2Id, String colorPlayer2) throws SQLException {
            // Determinar el color del jugador actual
            String colorActual = turnoBlanco ? "Blanco" : "Negro";
            String colorOponente = turnoBlanco ? "Negro" : "Blanco";

            // Verificar Jaque Mate
            if (tablero.esJaqueMate(colorOponente)) {
                JOptionPane.showMessageDialog(this,
                        "¡Jaque mate! " + colorActual + " gana el juego.",
                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                finDelJuego = true;
                
                if(colorPlayer2.equals("Blancas")){
                    colorPlayer2 = "Blanco";
                } else{
                    colorPlayer2 = "Negro";
                }
                
                
                //Ganó Jugador 2 con Blancas
                if(colorPlayer2.equals(colorActual) && colorPlayer2.equals("Blanco")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = ? WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player2Id);
                    pst.setInt(2, player2Id);
                    pst.setInt(3, player1Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 1: " + pst);
                
                //Ganó Jugador 2 con Negras    
                } else if(colorPlayer2.equals(colorActual) && colorPlayer2.equals("Negro")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = ? WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player2Id);
                    pst.setInt(2, player1Id);
                    pst.setInt(3, player2Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 2: " + pst);
                    
                //Ganó Jugador 1 con Blancas    
                } else if(colorPlayer2.equals(colorOponente) && colorPlayer2.equals("Blanco")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = ? WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player1Id);
                    pst.setInt(2, player2Id);
                    pst.setInt(3, player1Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 3: " + pst);
                

                //Ganó Jugador 1 con Negras    
                } else if(colorPlayer2.equals(colorOponente) && colorPlayer2.equals("Negro")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = ? WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player1Id);
                    pst.setInt(2, player1Id);
                    pst.setInt(3, player2Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 4: " + pst);
                }
                
                else {
                    System.out.println("No se cumplió ningun caso");
                }
             
                cerrarYMostrarHome();
                return;
            }

            // Verificar Tablas por Material Insuficiente
            if (tablero.materialInsuficiente()) {
                JOptionPane.showMessageDialog(this,
                        "¡Empate por material insuficiente!",
                        "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
                //reiniciarJuego();
                finDelJuego = true;
                
                if(colorPlayer2.equals("Blancas")){
                    colorPlayer2 = "Blanco";
                } else{
                    colorPlayer2 = "Negro";
                }
                
                
                if(colorPlayer2.equals(colorActual) && colorPlayer2.equals("Blanco")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = 0 WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player2Id);
                    pst.setInt(2, player1Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 1: " + pst);
                
                //Ganó Jugador 2 con Negras    
                } else if(colorPlayer2.equals(colorActual) && colorPlayer2.equals("Negro")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = 0 WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player1Id);
                    pst.setInt(2, player2Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 2: " + pst);
                    
                //Ganó Jugador 1 con Blancas    
                } else if(colorPlayer2.equals(colorOponente) && colorPlayer2.equals("Blanco")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = 0 WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player2Id);
                    pst.setInt(2, player1Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 3: " + pst);
                

                //Ganó Jugador 1 con Negras    
                } else if(colorPlayer2.equals(colorOponente) && colorPlayer2.equals("Negro")){
                    // Establecer conexión con la base de datos
                    ConexionDB db = new ConexionDB();
                    Connection cn = db.conectar();
                    
                    String query = "UPDATE games SET winner_id = 0 WHERE player_id_white = ? AND player_id_black = ? AND winner_id IS NULL";
                    
                    PreparedStatement pst = cn.prepareStatement(query);
                    
                    pst.setInt(1, player1Id);
                    pst.setInt(2, player2Id);
                    
                    pst.executeUpdate();

                    System.out.println("Caso 4: " + pst);
                }
                
                else {
                    System.out.println("No se cumplió ningun caso");
                }
                
                cerrarYMostrarHome();
                //return;
            }
        }

        // Método auxiliar para cerrar la ventana actual y abrir la Homepage
        private void cerrarYMostrarHome() {
            // Cerrar la ventana actual
            JFrame ventanaActual = (JFrame) this.getTopLevelAncestor();
            if (ventanaActual != null) {
                ventanaActual.dispose();
            }
        }

        public PanelTablero(int player1Id, int player2Id, String colorPlayer2) {
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
                                try {
                                    verificarEstadoDelJuego(player1Id, player2Id, colorPlayer2);
                                } catch (SQLException ex) {
                                    Logger.getLogger(TableroAjedrez.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if (tablero.estaEnJaque(filaReyEnemigo, colReyEnemigo, !turnoBlanco ? "Blanco" : "Negro", tablero) && !finDelJuego) {
                                    JOptionPane.showMessageDialog(TableroAjedrez.this,
                                            "¡El rey " + (!turnoBlanco ? "Blanco" : "Negro") + " está en peligro!", "Jaque", JOptionPane.ERROR_MESSAGE);
                                }

                                if (pieza.getTipo().equals("Rey") && tablero.estaEnJaque(fila, col, turnoBlanco ? "Blanco" : "Negro", tablero) && !finDelJuego) {
                                    JOptionPane.showMessageDialog(TableroAjedrez.this,
                                            "¡El rey " + (turnoBlanco ? "Blanco" : "Negro") + " está en peligro!", "Jaque", JOptionPane.ERROR_MESSAGE);
                                }

                                // Verificar si el movimiento elimina el jaque
                                boolean eliminaJaque = tablero.estaEnJaque(filaRey, colRey, turnoBlanco ? "Blanco" : "Negro", tablero);

                                // Revertir el movimiento si no elimina el jaquee
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

                                    repaint();
                                }
                            } else {
                                JOptionPane.showMessageDialog(TableroAjedrez.this,
                                        "El movimiento realizado no es válido", "Movimiento inválido: " + pieza.getTipo(), JOptionPane.ERROR_MESSAGE);
                            }
                        }

                        casillaSeleccionadaX = -1;
                        casillaSeleccionadaY = -1;
                    }
                }

            });
        }
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel /
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        / If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         
For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
      try {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;}}} catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);} catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);} catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);} catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(TableroAjedrez.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);}//</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableroAjedrez(0, 0, "").setVisible(true);
                
            }
        });
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
