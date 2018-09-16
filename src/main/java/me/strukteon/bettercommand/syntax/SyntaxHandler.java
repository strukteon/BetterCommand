package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 02.04.2018 at 22:11.
    
    (c) nils 2018
*/

public interface SyntaxHandler {

    /**
     * Called when an exception occurs
     * @param e exception that occurred
     * @return whether the parser should keep running
     */

    boolean onException(SyntaxValidateException e); // false = stop parsing; true = keep running


    /**
     * Called on a successful finish of the parser
     * @param syntax parsed syntax
     */

    void onFinish(Syntax syntax);

}
