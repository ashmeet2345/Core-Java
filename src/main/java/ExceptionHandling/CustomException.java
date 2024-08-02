package ExceptionHandling;

public class CustomException extends Exception{
    //If we extend RuntimeException, it will be a run time exception
    //If we extend Exception, it will be a checked exception

    public CustomException(String message){
        super(message);
    }

}
