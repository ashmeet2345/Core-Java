package Java8.Practice;

import java.util.ArrayList;

public class CustomArrayList extends ArrayList {

    @Override
    public boolean add(Object o) {
        if(this.contains(o))
            return true;
        else{
            return super.add(o);
        }
    }

    public static void main(String[] args) {
        CustomArrayList array=new CustomArrayList();
        array.add(1);
        array.add(1);
        array.add(1);
        array.add(3);
        array.add(4);

        System.out.println(array);
    }
}
