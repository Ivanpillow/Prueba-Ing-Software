
package Formularios;

import Clases.ConexionDB;
import Clases.Sesion;
import Clases.consultas;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ab_studio
 */
public class FormLogin extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    
    public FormLogin() {
        initComponents();
        //Centra la ventanada al iniciar
        this.setLocationRelativeTo(this);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        Background = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        logoname = new javax.swing.JLabel();
        extBtn = new javax.swing.JPanel();
        extTxt = new javax.swing.JLabel();
        chessbg = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        favicon = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        passLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jtxtPass = new javax.swing.JPasswordField();
        btnInSesion = new javax.swing.JPanel();
        btnInSesionTxt = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JPanel();
        btnRegistrarTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginavatarS.png"))); // NOI18N
        Background.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 340, 150));

        logoname.setBackground(new java.awt.Color(255, 255, 255));
        logoname.setFont(new java.awt.Font("Roboto Medium", 0, 24)); // NOI18N
        logoname.setForeground(new java.awt.Color(255, 255, 255));
        logoname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoname.setText("CHESS.MX");
        Background.add(logoname, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 340, 20));

        extBtn.setBackground(new java.awt.Color(0, 0, 0));

        extTxt.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        extTxt.setForeground(new java.awt.Color(255, 255, 255));
        extTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        extTxt.setText("X");
        extTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        extTxt.setPreferredSize(new java.awt.Dimension(40, 40));
        extTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                extTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                extTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                extTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout extBtnLayout = new javax.swing.GroupLayout(extBtn);
        extBtn.setLayout(extBtnLayout);
        extBtnLayout.setHorizontalGroup(
            extBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extBtnLayout.createSequentialGroup()
                .addComponent(extTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        extBtnLayout.setVerticalGroup(
            extBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(extTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Background.add(extBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 40, 40));

        chessbg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/BG.jpg"))); // NOI18N
        Background.add(chessbg, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 340, 600));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeaderMouseDragged(evt);
            }
        });
        Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeaderMousePressed(evt);
            }
        });

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        Background.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 40));

        favicon.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        favicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logoS.png"))); // NOI18N
        favicon.setText("  Sietemesinos");
        Background.add(favicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 240, 50));

        title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        title.setText("INICIAR SESIÓN");
        Background.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        userLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        userLabel.setText("CORREO ELECTRÓNICO");
        Background.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 160, -1));

        jtxtEmail.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jtxtEmail.setForeground(new java.awt.Color(204, 204, 204));
        jtxtEmail.setText("Ingrese su correo");
        jtxtEmail.setBorder(null);
        jtxtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtEmailMousePressed(evt);
            }
        });
        jtxtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtEmailActionPerformed(evt);
            }
        });
        Background.add(jtxtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 410, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        Background.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 410, 30));

        passLabel.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        passLabel.setText("CONTRASEÑA");
        Background.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        Background.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 410, 30));

        jtxtPass.setForeground(new java.awt.Color(204, 204, 204));
        jtxtPass.setText("***********");
        jtxtPass.setBorder(null);
        jtxtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtxtPassMousePressed(evt);
            }
        });
        Background.add(jtxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 382, 410, 30));

        btnInSesion.setBackground(new java.awt.Color(0, 0, 0));

        btnInSesionTxt.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        btnInSesionTxt.setForeground(new java.awt.Color(255, 255, 255));
        btnInSesionTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInSesionTxt.setText("ENTRAR");
        btnInSesionTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInSesionTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInSesionTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInSesionTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInSesionTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnInSesionLayout = new javax.swing.GroupLayout(btnInSesion);
        btnInSesion.setLayout(btnInSesionLayout);
        btnInSesionLayout.setHorizontalGroup(
            btnInSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInSesionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnInSesionTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnInSesionLayout.setVerticalGroup(
            btnInSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInSesionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnInSesionTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        Background.add(btnInSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 130, 50));

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });

        btnRegistrarTxt.setFont(new java.awt.Font("Roboto Black", 1, 14)); // NOI18N
        btnRegistrarTxt.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRegistrarTxt.setText("REGISTRARSE");
        btnRegistrarTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarTxtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnRegistrarLayout = new javax.swing.GroupLayout(btnRegistrar);
        btnRegistrar.setLayout(btnRegistrarLayout);
        btnRegistrarLayout.setHorizontalGroup(
            btnRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        btnRegistrarLayout.setVerticalGroup(
            btnRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        Background.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtEmailActionPerformed

    private void HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_HeaderMousePressed

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_HeaderMouseDragged

    private void extTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extTxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_extTxtMouseClicked

    private void extTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extTxtMouseEntered
        extBtn.setBackground(Color.red);
        extTxt.setForeground(Color.black);
    }//GEN-LAST:event_extTxtMouseEntered

    private void extTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_extTxtMouseExited
        extBtn.setBackground(Color.black);
        extTxt.setForeground(Color.white);
    }//GEN-LAST:event_extTxtMouseExited

    private void btnInSesionTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInSesionTxtMouseEntered
        btnInSesion.setBackground(new Color(0, 43, 84));
    }//GEN-LAST:event_btnInSesionTxtMouseEntered

    private void btnInSesionTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInSesionTxtMouseExited
        btnInSesion.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnInSesionTxtMouseExited

    private void btnRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseEntered
        btnRegistrar.setBackground(new Color(0, 43, 84));
    }//GEN-LAST:event_btnRegistrarMouseEntered

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void jtxtEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtEmailMousePressed
        if (jtxtEmail.getText().equals("Ingrese su correo")){
            jtxtEmail.setText("");
            jtxtEmail.setForeground(Color.black);
        }
        if (String.valueOf(jtxtPass.getPassword()).isEmpty()){
            jtxtPass.setText("***********");
            jtxtPass.setForeground(Color.gray); 
        }
    }//GEN-LAST:event_jtxtEmailMousePressed

    private void jtxtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxtPassMousePressed
        if (String.valueOf(jtxtPass.getPassword()).equals("***********")){
            jtxtPass.setText("");
            jtxtPass.setForeground(Color.black);
        }
        if (jtxtEmail.getText().isEmpty()){
            jtxtEmail.setText("Ingrese su correo");
            jtxtEmail.setForeground(Color.gray);
        }
    }//GEN-LAST:event_jtxtPassMousePressed

    private void btnInSesionTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInSesionTxtMouseClicked
        String email = jtxtEmail.getText().trim();
        String password = new String(jtxtPass.getPassword()).trim();

        // Validar si el campo de usuario está vacío
        if (jtxtEmail.getText().equals("Ingrese su correo")) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa tu correo electrónico.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar si el campo de contraseña está vacío
        if (String.valueOf(jtxtPass.getPassword()).equals("***********")) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa tu contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si los campos no están vacíos, intenta iniciar sesión
        consultas con = new consultas();
        boolean loginSuccess = con.consultarUsuario(email, password);

        // Validar el resultado de la consulta
        if (loginSuccess) {
            //JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            
            
            Homepage hp = new Homepage();
            hp.setVisible(true);
            this.setVisible(false); 
            // this.dispose(); // Cerrar la ventana de inicio de sesión
        } else {
            //JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnInSesionTxtMouseClicked

    private void btnRegistrarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarTxtMouseClicked
        this.setVisible(false);
        FormRegistro form = new FormRegistro();
        form.setVisible(true);
    }//GEN-LAST:event_btnRegistrarTxtMouseClicked

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
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel btnInSesion;
    private javax.swing.JLabel btnInSesionTxt;
    private javax.swing.JPanel btnRegistrar;
    private javax.swing.JLabel btnRegistrarTxt;
    private javax.swing.JLabel chessbg;
    private javax.swing.JPanel extBtn;
    private javax.swing.JLabel extTxt;
    private javax.swing.JLabel favicon;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JPasswordField jtxtPass;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logoname;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel title;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
