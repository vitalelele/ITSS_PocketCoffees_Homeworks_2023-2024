package main.java.customException;

public class IllegalCostException extends  Exception{
    public IllegalCostException() { super(); }
    public IllegalCostException(String message) { super(message); }
    public IllegalCostException(String message, Throwable cause) { super(message, cause); }
    public IllegalCostException(Throwable cause) { super(cause); }
}
