package main.java.customException;

public class IllegalNumberException extends  Exception{
    public IllegalNumberException() { super(); }
    public IllegalNumberException(String message) { super(message); }
    public IllegalNumberException(String message, Throwable cause) { super(message, cause); }
    public IllegalNumberException(Throwable cause) { super(cause); }
}
