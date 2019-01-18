/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.business.util;

import org.apache.commons.logging.Log;

/**
 *
 * @author rperez
 */
public class MessageUtil {

    public static void message(String tittle, String message, Class myClass, Log logger) {
        if (myClass != null) {
            String header = "";
            for (int i = 0; i < myClass.toString().length(); i++) {
                header = header.concat("=");
            }
            System.out.println("\n\n            ");
            System.out.println("            " + header);
            System.out.println("            " + myClass);
        }
        System.out.println("            **********  " + tittle.toUpperCase() + "  **********");
        System.out.println(" ---> " + message.toLowerCase());
    }

    public static void message(String message, Log logger) {
        System.out.println(message.toUpperCase());
    }

    public static void message(String message) {
        System.out.println(message.toUpperCase());
    }
    
    public static void message(String message, String detail) {
        System.out.println(new StringBuilder().append(message.toUpperCase()).append(" (").append(detail).append(") "));
    }
}
