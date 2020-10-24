package ru.enenakhov.mai.lessons.lesson03;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class IOStreams {

    public static final Logger logger = Logger.getLogger(IOStreams.class.getName());

    private static final String SOURCE_FILE_NAME = "log4j.properties";
    private static final String TARGET_FILE_NAME = "log4jcopy.properties";

    public static void main(String[] args) {
        copyFile(SOURCE_FILE_NAME, TARGET_FILE_NAME);
        try {
            RandomAccessFile rf = new RandomAccessFile(getResource(SOURCE_FILE_NAME), "rw");
            rf.seek(1);
            System.out.println(rf.readLine());
        } catch (FileNotFoundException e) {
            logger.error("Не удается найти файл", e);
        } catch (IOException e) {
            logger.error("Не удается прочитать файл", e);
        }

    }

    public static String getResource(String name) {
        return IOStreams.class.getResource("/" + name).getPath();
    }

    public static void copyFile(String sourceName, String targetName) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(getResource(sourceName))))) {
            PrintWriter pw = new PrintWriter(targetName);
            List<String> listLines = br.lines().collect(Collectors.toList());
            for (String line : listLines) {
                pw.println(line);
            }
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            logger.error("Не удается найти файл " + sourceName, e);
        } catch (IOException e) {
            logger.error("Не удается прочитать файл " + sourceName, e);
        }
    }
}
