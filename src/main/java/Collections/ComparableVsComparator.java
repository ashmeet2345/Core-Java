package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableVsComparator implements Comparable<ComparableVsComparator> {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public ComparableVsComparator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ComparableVsComparator() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(ComparableVsComparator o) {
        if(this.id == o.id)
            return 0;
        else if(this.id > o.id)
            return 1;
        else
            return -1;
    }


    @Override
    public String toString() {
        return "ComparableVsComparator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {

        ComparableVsComparator student1=new ComparableVsComparator(3,"ABC");
        ComparableVsComparator student2=new ComparableVsComparator(1,"XYZ");
        ComparableVsComparator student3=new ComparableVsComparator(2,"OBC");
        List<ComparableVsComparator> l=new ArrayList<ComparableVsComparator>();
        l.add(student1);
        l.add(student2);
        l.add(student3);
        Collections.sort(l);

        System.out.println(l);

        //UsingComparable is used where only one attribute is being compared.
        //In case of comparator, we can compare multiple attributes of the instance.

    }


}
