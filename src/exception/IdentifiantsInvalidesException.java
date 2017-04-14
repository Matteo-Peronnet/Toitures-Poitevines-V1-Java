/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Loïc
 */
public class IdentifiantsInvalidesException extends Exception {

    /**
     * Creates a new instance of <code>MauvaisLoginException</code> without
     * detail message.
     */
    public IdentifiantsInvalidesException() {
        super();
    }

    /**
     * Constructs an instance of <code>MauvaisLoginException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IdentifiantsInvalidesException(String msg) {
        super(msg);
    }
}
