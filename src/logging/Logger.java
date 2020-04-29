package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Logger {
    public static Logger instance;
    private String file_name;
    private BufferedWriter writer;
    private Date date;
    private int line_number;

    private Logger() {
        line_number = 1;
        date = new Date();
        file_name = "log.txt";
        try {
            writer = new BufferedWriter(new FileWriter(file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = this;
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void error(String className, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.toString()).append(" ERROR ").append(className)
                .append(":").append(line_number).append(" - ").append(message);
        line_number += 1;
        try {
            writer.write(sb.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String className, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.toString()).append(" INFO ").append(className)
                .append(":").append(line_number).append(" - ").append(message);
        line_number += 1;
        try {
            writer.write(sb.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trace(String className, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(date.toString()).append(" TRACE ").append(className)
                .append(":").append(line_number).append(" - ").append(message);
        line_number += 1;
        try {
            writer.write(sb.toString());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
