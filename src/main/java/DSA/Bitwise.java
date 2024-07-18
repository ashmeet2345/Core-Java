package DSA;

public class Bitwise {
    public static void main(String[] args) {
        System.out.println(oddEven(24));
    }
    static boolean oddEven(int num){
        return (num&1)==1;
    }
}
