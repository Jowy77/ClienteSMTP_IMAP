package com.mycompany.javafx.clientesmtp_imap;

import com.mycompany.javafx.clientesmtp_imap.vistas.LogInView;
import com.mycompany.javafx.clientesmtp_imap.vistas.PrincipalMonitorView;

public class ClienteSMTP_IMAP {

    public static void main(String[] args) {
        /*SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        GlobalMemory memory = hardware.getMemory();

        long totalMemory = memory.getTotal();
        long usedMemory = totalMemory - memory.getAvailable();

        double percentageUsed = (usedMemory * 100.0) / totalMemory;
        
        OperatingSystem os = systemInfo.getOperatingSystem();
        System.out.println("Sistema Operativo: " + os.toString());

        System.out.println("Memoria Total: " + formatBytes(totalMemory));
        System.out.println("Memoria Utilizada: " + formatBytes(usedMemory));
        System.out.println("Porcentaje de RAM Utilizada: " + String.format("%.2f%%", percentageUsed));
    }

    // Método para formatear bytes a kilobytes, megabytes, etc.
    private static String formatBytes(long bytes) {
        double kilobytes = bytes / 1024.0;
        double megabytes = kilobytes / 1024.0;
        double gigabytes = megabytes / 1024.0;

        if (gigabytes > 1) {
            return String.format("%.2f GB", gigabytes);
        } else if (megabytes > 1) {
            return String.format("%.2f MB", megabytes);
        } else {
            return String.format("%.2f KB", kilobytes);
        }*/
        
        LogInView view = new LogInView();
        view.setVisible(true);
    }
}
