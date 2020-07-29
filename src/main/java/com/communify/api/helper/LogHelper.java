package com.communify.api.helper;

import static java.lang.System.lineSeparator;
import static java.util.Calendar.getInstance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHelper {

    private static final String BASE_DIR = "logs";
    private static final String DEFAULT_DATE = "yyyy-MM-dd";
    private static final String DIR_SEPARATOR = "/";
    private static final String SEPARATOR = " - ";
    private static final String FILE_EXTENSION = ".log";

    public static void createOrUpdate(String email, String ipAddress) {
        try {
            manageDirectory();
            File file = manageFile();
            writeFile(email, ipAddress, file);   
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private static File manageDirectory() throws IOException {
        File directory = instanceDirectory();
        createDirectoryIfNotExists(directory);
        return directory;
    }

    private static File instanceDirectory() {
        return new File(new StringBuilder().append(BASE_DIR).toString());
    }
    
    private static void createDirectoryIfNotExists(File directory) throws IOException {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    
    private static File manageFile() throws IOException {
        File file = instanceFile();
        createFileIfNotExists(file);
        return file;
    }
    
    private static File instanceFile() {
        return new File(new StringBuilder()
            .append(BASE_DIR)
            .append(DIR_SEPARATOR)
            .append(new SimpleDateFormat(DEFAULT_DATE).format(new Date()))
            .append(FILE_EXTENSION)
            .toString());
    }
    
    private static void createFileIfNotExists(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void writeFile(String email, String ipAddress, File file) throws IOException {
        FileWriter writer = new FileWriter(file.getAbsolutePath(), true);
        BufferedWriter output = new BufferedWriter(writer);
        output.write(insertNewLine(email, ipAddress));
        output.close();
    }

    private static String insertNewLine(String email, String ipAddress) {
        return new StringBuilder()
            .append(getInstance().getTimeInMillis())
            .append(SEPARATOR)
            .append(email)
            .append(SEPARATOR)
            .append(ipAddress)
            .append(lineSeparator())
            .toString();
    }
}
