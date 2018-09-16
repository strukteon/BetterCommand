package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 02.04.2018 at 18:05.
    
    (c) nils 2018
*/

import java.util.ArrayList;
import java.util.List;

public class SyntaxValidateException extends Exception {

    private SyntaxElement element;
    private Cause cause;

    private List<SyntaxValidateException> exceptions = new ArrayList<>();


    /**
     * Initializes a new exception object with the provided message
     * @param message message
     */

    public SyntaxValidateException(String message){
        this(message, null);
    }


    /**
     * Initializes a new exception object with the provided message at the provided syntax element
     * @param message message
     * @param element element in which the exception occurred
     */

    public SyntaxValidateException(String message, SyntaxElement element){
        super(message);
        this.element = element;
    }


    /**
     * Initializes a new exception object with the provided cause
     * @param cause cause of exception
     */

    public SyntaxValidateException(Cause cause){
        this(cause, null);
    }


    /**
     * Initializes a new exception object with the provided cause at the provided syntax element
     * @param cause cause of exception
     * @param element element in which the exception occurred
     */

    public SyntaxValidateException(Cause cause, SyntaxElement element){
        super("At: " + element + "; Cause: " + cause.name());
        this.cause = cause;
        this.element = element;
    }


    /**
     * Initializes a new {@link SyntaxValidateException} with child exceptions
     * @param exceptions list of exceptions
     */

    public SyntaxValidateException(List<SyntaxValidateException> exceptions){
        this.exceptions = exceptions;
    }


    /**
     * Return a list of all exceptions that occurred
     * @return list of exceptions
     */

    public List<SyntaxValidateException> getExceptions() {
        if (exceptions.size() == 0)
            exceptions.add(this);
        return exceptions;
    }


    /**
     * Returns the {@link SyntaxElement} which caused the exception
     * @return syntax element
     */

    public SyntaxElement getElement() {
        return element;
    }


    /**
     * Returns the cause of the Exception
     * @return cause
     */

    public Cause getErrorCause() {
        return cause;
    }


    public enum Cause {

        NOT_FOUND,
        INVALID,
        EMPTY,
        TOO_FEW_ARGS,
        UNDEFINED,
        MISSING

    }
}
