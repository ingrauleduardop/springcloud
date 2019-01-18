/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.client.exception;

import cl.methodo_commons.enums.Errors;


/**
 *
 * @author rperez
 */
public class AuthenticationException extends Exception{

    public AuthenticationException() {
    }

    public AuthenticationException(String string) {
        super(string);
    }
    
    public AuthenticationException(Errors error) {
        super(error.name());
    }

    public AuthenticationException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
