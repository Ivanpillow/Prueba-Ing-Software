
package Formularios;

import Clases.Sesion;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 *
 * @author luigi
 */
public class Homepage extends javax.swing.JFrame {
    private String username = Sesion.getUsername();
    /**
     * Creates new form Homepage
     */
    public Homepage() {
        initComponents();
        jLabelUsername.setText("Bienvenido " + username);
        
        //Centra la ventanada al iniciar
        this.setLocationRelativeTo(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevaPartida1 = new javax.swing.JPanel();
        jLabelUsername = new javax.swing.JLabel();
        iconUser = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();
        btnNvPartida = new javax.swing.JPanel();
        btnNPartidatxt = new javax.swing.JLabel();
        btnClasif = new javax.swing.JPanel();
        btnClasiftxt = new javax.swing.JLabel();
        btnVolver = new javax.swing.JPanel();
        btnVolverTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNuevaPartida1.setBackground(new java.awt.Color(112, 145, 255));
        btnNuevaPartida1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsername.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabelUsername.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsername.setText("Inicio");
        btnNuevaPartida1.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 30, 310, -1));

        iconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginavatarS.png"))); // NOI18N
        btnNuevaPartida1.add(iconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 180, 150));

        lblBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BG.jpg"))); // NOI18N
        btnNuevaPartida1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 310, 440));

        btnNvPartida.setBackground(new java.awt.Color(0, 0, 0));
        btnNvPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNvPartidaMouseClicked(evt);
            }
        });

        btnNPartidatxt.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnNPartidatxt.setForeground(new java.awt.Color(255, 255, 255));
        btnNPartidatxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNPartidatxt.setText("Nueva partida");

        javax.swing.GroupLayout btnNvPartidaLayout = new javax.swing.GroupLayout(btnNvPartida);
        btnNvPartida.setLayout(btnNvPartidaLayout);
        btnNvPartidaLayout.setHorizontalGroup(
            btnNvPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNPartidatxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btnNvPartidaLayout.setVerticalGroup(
            btnNvPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNPartidatxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnNuevaPartida1.add(btnNvPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 160, 40));

        btnClasif.setBackground(new java.awt.Color(0, 0, 0));

        btnClasiftxt.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnClasiftxt.setForeground(new java.awt.Color(255, 255, 255));
        btnClasiftxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClasiftxt.setText("Clasificaciones");
        btnClasiftxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClasiftxtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnClasifLayout = new javax.swing.GroupLayout(btnClasif);
        btnClasif.setLayout(btnClasifLayout);
        btnClasifLayout.setHorizontalGroup(
            btnClasifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClasiftxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        btnClasifLayout.setVerticalGroup(
            btnClasifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClasiftxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnNuevaPartida1.add(btnClasif, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 160, 40));

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnVolverTxt.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnVolverTxt.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolverTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previous.png"))); // NOI18N
        btnVolverTxt.setText("  VOLVER");
        btnVolverTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolverTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolverTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnVolverLayout = new javax.swing.GroupLayout(btnVolver);
        btnVolver.setLayout(btnVolverLayout);
        btnVolverLayout.setHorizontalGroup(
            btnVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVolverTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnVolverLayout.setVerticalGroup(
            btnVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVolverTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        btnNuevaPartida1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevaPartida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevaPartida1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirSeleccionJugador2() {
        // Crear y mostrar el formulario de selección del jugador 2
        SeleccionJugador2 seleccion = new SeleccionJugador2();
        seleccion.setVisible(true);
        this.setVisible(false); // Opcional: ocultar la pantalla de inicio
    }
    
    private void btnNvPartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNvPartidaMouseClicked
        abrirSeleccionJugador2();
    }//GEN-LAST:event_btnNvPartidaMouseClicked

    private void btnClasiftxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClasiftxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClasiftxtMouseClicked

    private void btnVolverTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxtMouseClicked
        this.setVisible(false);
        FormLogin fr2 = new FormLogin();
        fr2.setVisible(true);
    }//GEN-LAST:event_btnVolverTxtMouseClicked

    private void btnVolverTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxtMouseEntered
        btnVolver.setBackground(new Color(0, 43, 85));
    }//GEN-LAST:event_btnVolverTxtMouseEntered

    private void btnVolverTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxtMouseExited
        btnVolver.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnVolverTxtMouseExited

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
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnClasif;
    private javax.swing.JLabel btnClasiftxt;
    private javax.swing.JLabel btnNPartidatxt;
    private javax.swing.JPanel btnNuevaPartida1;
    private javax.swing.JPanel btnNvPartida;
    private javax.swing.JPanel btnVolver;
    private javax.swing.JLabel btnVolverTxt;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel lblBackground;
    // End of variables declaration//GEN-END:variables
}
