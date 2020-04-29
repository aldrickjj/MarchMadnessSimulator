package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Implements a logger that writes to the log.txt file
 */
public class Logger {
    public static Logger instance;
    private String file_name;
    private BufferedWriter writer;
    private Date date;
    private int line_number;

    /**
     * Constructor for the logger
     */
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

    /**
     * Creates and returns a new logger object if and only if there aren't any other logger objects already
     * @return a logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Logs an error in log.txt
     * @param className the name of the class the error occured in
     * @param message the error message
     */
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

    /**
     * Logs a regular event occurring in the code
     * @param className the name of the Class in which this occurred
     * @param message the informational message
     */
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

    /**
     * Logs for the purpose of tracing
     * @param className the name of the Class
     * @param message the associated message
     */
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
