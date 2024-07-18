package ExceptionHandling;

public class Main {
    public static void main(String[] args) {
        int a=5;
        int b=0;
        try{
            if(b==0)
                throw new MyException("This is not a good way to perform divide");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally { //optional
            System.out.println("This will always run");
        }
    }

    static int divide(int a,int b) throws Exception {
        if(b==0)
            throw new Exception("b should not be 0");
        return a/b;
    }
}
