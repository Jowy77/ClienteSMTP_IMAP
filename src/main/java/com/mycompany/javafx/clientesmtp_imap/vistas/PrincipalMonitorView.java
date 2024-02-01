
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
    String correo;
    String claveDeAplicacion;
    OperatingSystem os;
    SystemInfo systemInfo = new SystemInfo();
    GlobalMemory memory = systemInfo.getHardware().getMemory();

    public PrincipalMonitorView(String correo, String passToken) {

        initComponents();
        os = systemInfo.getOperatingSystem();
        umbralMaximo = 50;
        this.correo = correo;
        this.claveDeAplicacion = passToken;
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
                    message = "EL PORCENTAJE DE USO DE SU EQUIPO HA SUPERADO EL "+ umbralMaximo + "\n"
                            + "Sistema operativo: " + os.toString() + "\n"
                            + "Memoria disponible del equipo: " + Utils.formatBytes(totalRam) + "\n"
                            + "Uso maximo de memoria: " + Utils.formatBytes(memoriaMaximaNumero) + "/" + Utils.formatBytes(totalRam) + "/GB";
                    Utils.sendEmail(this.correo, claveDeAplicacion, this.correo, subject, message);
                    Utils.showMessageDialog("CORREO ENVIADO, LA RAM HA LLEGADO A UN ESTADO CRITICO", "RAM SUPERADA(77-77-77)", 1);
                    //PRIMERO ELIMINO LOS CORREOS VIEJOS GUARDADOS EN LOS RECURSOS
                    Utils.deleteFilesInFolder();
                    //AHORA LEERE LOS CORREOS DEL SERVIDOR Y LOS GUARDARE DE NUEVO
                    Utils.readAndSaveEmails(this.correo, claveDeAplicacion, subject);
                    Utils.showMessageDialog("CORREOS CON EL ASUNTO 77-77-77\nGUARDADOS EN LA CARPETA RECURSOS DEL PROYECTO", "CORREO ALMACENADO", 1);
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
        umbralEditButton = new javax.swing.JButton();
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

        umbralEditButton.setText("Cambiar Umbral Correos");
        umbralEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                umbralEditButtonActionPerformed(evt);
            }
        });

        jMenuBar1.setAlignmentX(1.0F);
        jMenuBar1.setAlignmentY(1.0F);

        salirMenuButton.setText("Salir");
        salirMenuButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salirMenuButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salirMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirMenuButtonMouseClicked(evt);
            }
        });
        salirMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuButtonActionPerformed(evt);
            }
        });
        jMenuBar1.add(salirMenuButton);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(82, 82, 82))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(sistemaOperativoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(memoriaTotalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(memoriaUsadaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(memoriaTiempoRealBar, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                            .addComponent(picoDeUsoRamBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(picoMaximo2Label)
                                            .addComponent(memoriaReal2Label))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(umbralEditButton)))
                        .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(picoMaximo2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(picoDeUsoRamBar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(umbralEditButton))
                .addContainerGap())
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

    private void umbralEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_umbralEditButtonActionPerformed
        String nuevoUmbral = Utils.getUserInput("EL UMBRAL ACTUAL ES DEL " + umbralMaximo + "%\n Introduce un nuevo valor para modificarlo");
        
        if(!Utils.containsOnlyNumbers(nuevoUmbral)){
            Utils.showMessageDialog("SOLO PUEDES INTRODUCIR NUMEROS", "DETECTADO TEXTO", 0);
        }else if(Double.parseDouble(nuevoUmbral) < 0 || Double.parseDouble(nuevoUmbral) > 100){
            Utils.showMessageDialog("EL NUMERO TIENE QUE ESTAR ENTRE 0 Y 100", "ERROR", 0);
        }else{
            umbralMaximo=Double.parseDouble(nuevoUmbral);
            Utils.showMessageDialog("UMBRAL CAMBIADO A " + umbralMaximo, "UMBRAL MODIFICADO", 1);
        }
    }//GEN-LAST:event_umbralEditButtonActionPerformed

    private void salirMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuButtonActionPerformed
        
    }//GEN-LAST:event_salirMenuButtonActionPerformed

    private void salirMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMenuButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_salirMenuButtonMouseClicked


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
    private javax.swing.JButton umbralEditButton;
    // End of variables declaration//GEN-END:variables
}
