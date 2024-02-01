/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.javafx.clientesmtp_imap.vistas;

import com.itextpdf.text.DocumentException;
import com.mycompany.javafx.clientesmtp_imap.utiles.Utils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author 2damb
 */
public class PrincipalMonitorView extends javax.swing.JFrame {

    private long totalRam;
    private long usedRam;
    private double percentageUsed;
    private double umbralMaximo;
    private long memoriaMaximaNumero;
    private double memoriaMaximaPorcentaje;
    private String message = "";
    String subject = "77-77-77";
    String correo = "coutojoel77@gmail.com";
    String claveDeAplicacion = "oohydxqftvhgxnyb";
    OperatingSystem os;
    SystemInfo systemInfo = new SystemInfo();
    GlobalMemory memory = systemInfo.getHardware().getMemory();

    public PrincipalMonitorView() {

        initComponents();
        os = systemInfo.getOperatingSystem();
        umbralMaximo = 50.00;
        updateMemoryInfo();

        System.out.println("Sistema Operativo: " + os.toString());

        sistemaOperativoLabel.setText(os.toString());

        memoriaTotalLabel.setText(Utils.formatBytes(totalRam));
        memoriaMaximaNumero = usedRam;
        memoriaMaximaPorcentaje = percentageUsed;

        Thread memoryUpdateThread = new Thread(() -> {
            while (true) {
                updateMemoryInfo();
                memoriaUsadaLabel.setText(Utils.formatBytes(usedRam));
                memoriaTiempoRealBar.setValue((int) percentageUsed);
                picoDeUsoRamBar.setValue((int) memoriaMaximaPorcentaje);

                memoriaReal2Label.setText((int) percentageUsed + "%");
                picoMaximo2Label.setText((int) memoriaMaximaPorcentaje + "%  /  " + Utils.formatBytes(memoriaMaximaNumero));
                if (memoriaMaximaNumero < usedRam) {
                    memoriaMaximaNumero = usedRam;
                }
                if (memoriaMaximaPorcentaje < percentageUsed) {
                    memoriaMaximaPorcentaje = percentageUsed;
                }

                if (percentageUsed >= umbralMaximo) {
                    message = "EL PORCENTAJE DE USO DE SU EQUIPO HA SUPERADO EL 50%\n"
                            + "Sistema operativo: " + os.toString() + "\n"
                            + "Memoria disponible del equipo: " + Utils.formatBytes(totalRam) + "\n"
                            + "Uso maximo de memoria: " + Utils.formatBytes(memoriaMaximaNumero) + "/" + Utils.formatBytes(totalRam) + "/GB";
                    Utils.sendEmail(correo, claveDeAplicacion, correo, subject, message);

                    Utils.readAndSaveEmails(correo, claveDeAplicacion, subject);
                }
                try {
                    Thread.sleep(1000); // Dormir durante 5 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        memoryUpdateThread.start(); // Iniciar el hilo
    }

    private void updateMemoryInfo() {
        totalRam = memory.getTotal();
        usedRam = totalRam - memory.getAvailable();
        percentageUsed = (usedRam * 100.0) / totalRam;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        memoriaTotalLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        memoriaUsadaLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        memoriaTiempoRealBar = new javax.swing.JProgressBar();
        memoriaReal2Label = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        picoDeUsoRamBar = new javax.swing.JProgressBar();
        picoMaximo2Label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        sistemaOperativoLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        salirMenuButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Monitor de RAM");

        jLabel2.setText("Memoria total:");

        memoriaTotalLabel.setText("16");

        jLabel4.setText("Memoria Usada Actualmente:");

        memoriaUsadaLabel.setText("6");
        memoriaUsadaLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel6.setText("Representacion de la memoria en tiempo real:");

        memoriaReal2Label.setText("7.40");

        jLabel9.setText("Pico maximo de uso");

        picoDeUsoRamBar.setForeground(new java.awt.Color(153, 255, 0));

        picoMaximo2Label.setText("7.40");

        jButton1.setText("Generar Pdf");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Sistema Operativo(SO)");

        sistemaOperativoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sistemaOperativoLabel.setText("Windows 7.0");

        jMenuBar1.setAlignmentX(1.0F);
        jMenuBar1.setAlignmentY(1.0F);

        salirMenuButton.setText("Salir");
        salirMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salirMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuBar1.add(salirMenuButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(82, 82, 82))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sistemaOperativoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(memoriaTotalLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(memoriaUsadaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memoriaTiempoRealBar, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                    .addComponent(picoDeUsoRamBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(picoMaximo2Label)
                                    .addComponent(memoriaReal2Label))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(sistemaOperativoLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(memoriaTotalLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(memoriaUsadaLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(memoriaTiempoRealBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(200, 200, 200)
                        .addComponent(memoriaReal2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(picoMaximo2Label)
                    .addComponent(picoDeUsoRamBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            try {
                Utils.generateEmailReport(correo, claveDeAplicacion, subject);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PrincipalMonitorView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(PrincipalMonitorView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(PrincipalMonitorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalMonitorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalMonitorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalMonitorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalMonitorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalMonitorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalMonitorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel memoriaReal2Label;
    private javax.swing.JProgressBar memoriaTiempoRealBar;
    private javax.swing.JLabel memoriaTotalLabel;
    private javax.swing.JLabel memoriaUsadaLabel;
    private javax.swing.JProgressBar picoDeUsoRamBar;
    private javax.swing.JLabel picoMaximo2Label;
    private javax.swing.JMenu salirMenuButton;
    private javax.swing.JLabel sistemaOperativoLabel;
    // End of variables declaration//GEN-END:variables
}
