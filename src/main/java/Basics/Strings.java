package Basics;

public class Strings {
    public static void main(String[] args) {

        String s1="ABC";
        String s2=new String("ABC");
        //Here with s2 being created, it will not create only 1 object but it will create 2 objects,
        //1 in the heap and other in the String Pool because it could be reused later.

        String s3="ABC";

        if(s1==s3) System.out.println("Yes");
        else System.out.println("No");

        //When S1 is created, JVM searches it in the String pool (It is in the Heap).
        //No 2 String objects can be same in the String constant pool
        //String pool is provided to reuse the existing object.
        // == compares the memory address and equals compares the content of the 2 strings

        String str1="Code";
        String str2=new String("Code");
        String str3=str2.intern();
        System.out.println(str3);
        System.out.println(str1==str3);

        //The intern method does is to check, it will check if theres an object of str2 present in
        //SCP, then return the reference to it such as str3 will now be referencing to "Code" present
        //int SCP
        //Here str3 will work as String str3="Code"
    }
}
