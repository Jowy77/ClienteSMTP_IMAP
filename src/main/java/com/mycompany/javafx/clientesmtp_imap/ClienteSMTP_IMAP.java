package com.mycompany.javafx.clientesmtp_imap;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class ClienteSMTP_IMAP {

    public static void main(String[] args) {
        // Crear una instancia de SystemInfo
        SystemInfo systemInfo = new SystemInfo();

        // Obtener la capa de abstracción del hardware
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        // Obtener información sobre el sistema operativo
        OperatingSystem os = systemInfo.getOperatingSystem();
        System.out.println("Sistema Operativo: " + os.toString());

        // Obtener información sobre la memoria
        GlobalMemory memory = hardware.getMemory();
        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();
        long usedMemory = totalMemory - availableMemory;
        System.out.println("Memoria Total: " + formatBytes(totalMemory));
        System.out.println("Memoria Disponible: " + formatBytes(availableMemory));
        System.out.println("Uso actual de la memoria: " + formatBytes(usedMemory));
    }

    // Método para formatear bytes a kilobytes, megabytes, etc.
    private static String formatBytes(long bytes) {
        double kilobytes = bytes / 1024.0;
        double megabytes = kilobytes / 1024.0;
        double gigabytes = megabytes / 1024.0;

        return String.format("%.2f GB", gigabytes);
    }
}
