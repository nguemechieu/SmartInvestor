package com.smartinvestor.smartinvestor;

import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    public Log() {
        System.out.println("Log");


    }

    public static void info( String s,String message) {
        System.out.println(message);
        System.out.println(s);
        org.slf4j.Logger logger = LoggerFactory.getLogger(s);
        logger.info(s, message);


    }

    public static void error(String message) {
        System.err.println(message);
        Logger logger =
                Logger.getLogger(message.getClass().getName());
        logger.log(java.util.logging.Level.SEVERE, message);
    }

    public static void warn(String message) {
        System.err.println(message);
        Logger logger =
                Logger.getLogger(message.getClass().getName());
        logger.log(java.util.logging.Level.SEVERE, message);


    }



    public static void i(String file, String created_new_file) {
        System.err.println("File " + file + " created "
                + " at " + new java.util.Date());
        System.err.println(created_new_file);
        org.slf4j.Logger logger =
                org.slf4j.LoggerFactory.getLogger(file);
        logger.info(file, created_new_file);
    }


    public static void error(int tag, String s) {
        Logger logger =
                Logger.getLogger(s.getClass().getName());
        logger.log(java.util.logging.Level.SEVERE, s);

    }

    public static void e(String tag, @NotNull String noCoinInfoAvailable) {
        Logger logger =
                Logger.getLogger(noCoinInfoAvailable.getClass().getName());
        logger.log(Level.FINE,noCoinInfoAvailable);
    }

    public void debug(@NotNull String s) {
        Logger logger =
                Logger.getLogger(s.getClass().getName());
        logger.log(java.util.logging.Level.FINE, s);

    }
}
