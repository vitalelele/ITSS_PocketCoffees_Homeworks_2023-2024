package main.java.customException;

public class NullNumberException extends  Exception{
    public NullNumberException() { super(); }
    public NullNumberException(String message) { super(message); }
    public NullNumberException(String message, Throwable cause) { super(message, cause); }
    public NullNumberException(Throwable cause) { super(cause); }
}
