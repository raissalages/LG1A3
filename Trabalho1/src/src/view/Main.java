package view;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        //region Logger
        Loggader logger = Logger.getLogger(Main.class.getName());
        //Sets a date time format such as "21-mar-2021-10:07:56"
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-LLL-yyyy-HH_m_ss", Locale.ENGLISH);
        LocalDateTime now = LocalDateTime.now();

        try {
            //Creates a log file for each time the program is launched, with its name being the Date and Time at launch moment
            FileHandler fileHandler = new FileHandler("out/logs/" + dtf.format(now) + ".log", true);
            SimpleFormatter formatter = new SimpleFormatter();

            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        } catch (IOException exception) {
            logger.severe(exception.getMessage());
        }
        logger.info("Starting Library Manager");
        //endregion
        */
        MainFrame mainFrame = new MainFrame();
        
        //TODO create UI for login, sign in, Library management and book borrowing
        //TODO create functionality of login, sign in, Library management and book borrowing
    }
}