package Fundamentals;

public class Final_Finally_Finalize {
    private final double pie=3.14;
    //final- Keyword to make a value constant which we dont want to change in the future

    public void useFinally(){
        try{
            System.out.println("Try block");
        } finally {
            System.exit(0); //this can help stop the finally block as well. means code is terminated here
            System.out.println("Finally block");
        }
        //finally-block
        //Which is used if we surely want to print or call something even after an error is thrown
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method");
    }

    public static void main(String[] args) {
        Final_Finally_Finalize f = new Final_Finally_Finalize();
        f.useFinally();
        f=null;
        System.gc();
    }
}
