package ExceptionHandling;

public class Main {

    public void test(int order) throws CustomException {
        if(order!=69)
            throw new CustomException("Order should be 69");
        else
            System.out.println("Nice");
    }

    public static void main(String[] args){
        int a=5;
        int b=0;
       /* try{
            if(b==0)
                throw new MyException("This is not a good way to perform divide");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally { //optional
            System.out.println("This will always run");
        }*/


        Main m=new Main();
        try {
            m.test(68);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    static int divide(int a,int b) throws Exception {
        if(b==0)
            throw new Exception("b should not be 0");
        return a/b;
    }
}
