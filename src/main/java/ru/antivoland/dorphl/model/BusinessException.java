/*
 * @author antivoland
 */
package ru.antivoland.dorphl.model;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
