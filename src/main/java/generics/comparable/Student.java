package generics.comparable;

public class Student implements Comparable<Student>{
    int rollNo;
    int Marks;
    String name;

    public Student(int rollNo, int marks, String name) {
        this.rollNo = rollNo;
        Marks = marks;
        this.name = name;
    }

    @Override
    public String toString() {
        return Marks +"";
    }

    @Override
    public int compareTo(Student o) {
        int difference =(int)(this.Marks - o.Marks);
        return difference;
    }
}