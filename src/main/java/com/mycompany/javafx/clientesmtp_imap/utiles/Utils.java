package com.mycompany.javafx.clientesmtp_imap.utiles;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Utils {

    public static String formatBytes(long bytes) {
        double kilobytes = bytes / 1024.0;
        double megabytes = kilobytes / 1024.0;
        double gigabytes = megabytes / 1024.0;

        if (gigabytes > 1) {
            return String.format("%.2f GB", gigabytes);
        } else if (megabytes > 1) {
            return String.format("%.2f MB", megabytes);
        } else {
            return String.format("%.2f KB", kilobytes);
        }
    }

    public static String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public static boolean containsOnlyNumbers(String str) {
        return Pattern.matches("[0-9]+", str);
    }

    public static void showMessageDialog(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isEmailFormatValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:gmail)\\.(?:com)$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static void sendEmail(String username, String password, String to, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readAndSaveEmails(String username, String password, String subjectToSearch) {
        Properties properties = new Properties();
        properties.setProperty("imap.gmail.com", "imaps");

        try {

            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", username, password);

            // Abrir la carpeta INBOX
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                if (message.getSubject() != null && message.getSubject().contains(subjectToSearch)) {

                    saveEmailToFile(message);
                }
            }

            // Cerrar la conexión
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveEmailToFile(Message message) {
        try {
            String content = message.getContent().toString();
            FileWriter fileWriter = new FileWriter(new File("src/main/resources/email_" + System.currentTimeMillis() + ".txt"));
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void generateEmailReport(String username, String password,
            String subjectToSearch) throws FileNotFoundException, DocumentException, MessagingException, IOException {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(properties, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", username, password);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar informe PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String outputFileName = file.getAbsolutePath();

            FileOutputStream fos = new FileOutputStream(new File(outputFileName + ".pdf"));

            Document document = new Document();
            PdfWriter.getInstance(document, fos);

            document.open();

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                if (message.getSubject() != null && message.getSubject().contains(subjectToSearch)) {

                    addEmailInfoToReport(document, message);
                }
            }

            inbox.close(false);
            store.close();

            document.close();
            showMessageDialog("PDF CREADO CORRECTAMENTE", "PDF", 1);
        }
    }

    private static void addEmailInfoToReport(Document document, Message message) throws MessagingException, DocumentException, IOException {
        // Agregar información relevante del correo al informe PDF
        document.add(new Paragraph("Asunto: " + message.getSubject()));
        document.add(new Paragraph("Remitente: " + message.getFrom()[0]));
        document.add(new Paragraph("Fecha de Envío: " + message.getSentDate()));
        document.add(new Paragraph("Contenido: " + message.getContent().toString()));
        document.add(new Paragraph("-----------------------------------------------"));
    }

    public static void deleteFilesInFolder() {
        File folder = new File("src/main/resources");

        // Verificar si la ruta especificada es un directorio
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            // Verificar si la carpeta contiene archivos
            if (files != null) {
                for (File file : files) {
                    // Eliminar cada archivo
                    if (file.isFile()) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Archivo eliminado: " + file.getAbsolutePath());
                        } else {
                            System.err.println("No se pudo eliminar el archivo: " + file.getAbsolutePath());
                        }
                    }
                }
            } else {
                System.out.println("La carpeta está vacía.");
            }
        } else {
            System.err.println("La ruta especificada no es un directorio.");
        }
    }
}
