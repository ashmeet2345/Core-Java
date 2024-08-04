package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailSafeIterator {
    public static void main(String[] args) {
        List<Integer> list=new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it=list.iterator();
        while(it.hasNext()) {
            System.out.println(it.next()+" ");
            list.add(4);
            //it will not print as it will be added to a copy of the above list object.
        }
    }
}
