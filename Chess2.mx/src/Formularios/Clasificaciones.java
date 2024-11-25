
package Formularios;
import Clases.ConexionDB;
import Clases.Sesion;
import java.awt.Color;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ab_st
 */
public class Clasificaciones extends javax.swing.JFrame {
    
    int yMouse, xMouse;

    public Clasificaciones() {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana
        cargarClasificaciones(); // Llama al método para cargar datos
    }
    
    private void cargarClasificaciones() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Jugador");
        modelo.addColumn("Partidas Ganadas");

        jTableClasificaciones.setModel(modelo); 

        try {
            ConexionDB db = new ConexionDB();
            Connection cn = db.conectar();

           
            String query = "SELECT u.username AS jugador, COUNT(g.winner_id) AS partidas_ganadas " +
                           "FROM games g " +
                           "JOIN users u ON g.winner_id = u.user_id " +
                           "GROUP BY g.winner_id " +
                           "ORDER BY partidas_ganadas DESC";

            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            
            while (rs.next()) {
                String jugador = rs.getString("jugador");
                int partidasGanadas = rs.getInt("partidas_ganadas");
                modelo.addRow(new Object[]{jugador, partidasGanadas});
            }

            cn.close(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las clasificaciones: " + e.getMessage());
        }
    }
    
    
     private void cargarUsuarios() {
        //Obtener el nombre del usuario logueado
        String usuarioLogueado = Sesion.getUsername();

        //Crear una lista para los usuarios que se mostrarán en el JComboBox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //cbUsers.setModel(model);
        
        //Agregar la opción por defecto al principio
        model.addElement("Seleccione contrincante...");
        //cbUsers.setSelectedIndex(0); 

        try {
            
            ConexionDB db = new ConexionDB();
            Connection cn = db.conectar();
            // Conectar a la base de datos
            
            String query = "SELECT username FROM users WHERE username != ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, usuarioLogueado);
            
            //System.out.println("Ejecutando consulta: " + query);  // Depuración

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Obtener el nombre de usuario de cada fila y agregarlo al JComboBox
                String username = rs.getString("username");
                model.addElement(username);
            }

            cn.close(); // Cerrar la conexión

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + e.getMessage());
        }
    }
    
    // Método para cargar los datos desde la base de datos
    private void cargarDatos() {
//        DefaultTableModel model = (DefaultTableModel) jTableClasificaciones.getModel();
//        model.setRowCount(0); // Limpia la tabla antes de cargar datos
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // Cambia la URL, usuario y contraseña según tu base de datos
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña");
//            stmt = conn.createStatement();
//            String sql = "SELECT id, jugador, puntaje FROM clasificaciones";
//            rs = stmt.executeQuery(sql);
//
//            // Llena la tabla con los datos de la base de datos
//            while (rs.next()) {
//                Object[] fila = {rs.getInt("id"), rs.getString("jugador"), rs.getInt("puntaje")};
//                model.addRow(fila);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage());
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClasificaciones = new javax.swing.JTable();
        header = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        exitTxt = new javax.swing.JLabel();
        seleccionaJugTxt = new javax.swing.JLabel();
        volverIcon = new javax.swing.JLabel();
        btnVolver = new javax.swing.JPanel();
        btnVolverTxt1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableClasificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableClasificaciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 490, 300));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitTxt.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTxt.setText("X");
        exitTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exitTxt.setPreferredSize(new java.awt.Dimension(40, 40));
        exitTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        seleccionaJugTxt.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        seleccionaJugTxt.setText("Clasificaciones");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                    .addGap(0, 450, Short.MAX_VALUE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 158, Short.MAX_VALUE)
                    .addComponent(seleccionaJugTxt)
                    .addGap(0, 158, Short.MAX_VALUE)))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addComponent(seleccionaJugTxt)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );

        getContentPane().add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 40));

        volverIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        volverIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previousM.png"))); // NOI18N
        getContentPane().add(volverIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 40, 40));

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnVolverTxt1.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        btnVolverTxt1.setForeground(new java.awt.Color(255, 255, 255));
        btnVolverTxt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolverTxt1.setText("  VOLVER");
        btnVolverTxt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverTxt1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolverTxt1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolverTxt1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnVolverLayout = new javax.swing.GroupLayout(btnVolver);
        btnVolver.setLayout(btnVolverLayout);
        btnVolverLayout.setHorizontalGroup(
            btnVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnVolverLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolverTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        btnVolverLayout.setVerticalGroup(
            btnVolverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnVolverLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnVolverTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 130, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitTxtMouseClicked

    private void exitTxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseEntered
        exitBtn.setBackground(Color.red);
        exitTxt.setForeground(Color.white);
    }//GEN-LAST:event_exitTxtMouseEntered

    private void exitTxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTxtMouseExited
        exitBtn.setBackground(Color.white);
        exitTxt.setForeground(Color.black);
    }//GEN-LAST:event_exitTxtMouseExited

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void btnVolverTxt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxt1MouseClicked
        this.setVisible(false);
        //Homepage hp2 = new Homepage();
        //hp2.setVisible(true);
    }//GEN-LAST:event_btnVolverTxt1MouseClicked

    private void btnVolverTxt1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxt1MouseEntered
        btnVolver.setBackground(new Color(0, 43, 85));
    }//GEN-LAST:event_btnVolverTxt1MouseEntered

    private void btnVolverTxt1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverTxt1MouseExited
        btnVolver.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnVolverTxt1MouseExited

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
            java.util.logging.Logger.getLogger(Clasificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clasificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clasificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clasificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clasificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnVolver;
    private javax.swing.JLabel btnVolverTxt1;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JLabel exitTxt;
    private javax.swing.JPanel header;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClasificaciones;
    private javax.swing.JLabel seleccionaJugTxt;
    private javax.swing.JLabel volverIcon;
    // End of variables declaration//GEN-END:variables
}
