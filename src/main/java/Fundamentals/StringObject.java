package Fundamentals;

public class StringObject {
    public static void main(String[] args) {

        String s1=new String("Ashmeet");
        //1st object because of new, in heap;
        //2d object because of Ashmeet literal stored in SCP-> (String constant pool area)

        String s2="Ashmeet";
        //It will create object in SCP if there is no String object in scp already.
        /*but here, s1 has already stored Ashmeet in SCP so s2 will not create another object
           as both s1 and s2 value are same
           So total object count = 1;
        * */

        System.out.println(s1.intern().hashCode()==s2.hashCode());
        //intern here is used to find the hashcode of SCP
        String s="Java";
        s.concat(" is lob");
        System.out.println(s);

        StringBuffer sb=new StringBuffer("Java");
        sb.append(" is lob");
        System.out.println(sb);
    }
}
