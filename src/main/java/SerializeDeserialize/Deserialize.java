package SerializeDeserialize;

import java.io.*;

public class Deserialize {
    public static void main(String[] args) {

        try{
            FileInputStream is=new FileInputStream("C:\\Users\\ACER\\Downloads\\abc\\obj.txt");
            ObjectInputStream ois=new ObjectInputStream(is);
            Serializing s= (Serializing) ois.readObject();
            System.out.println(s.name+" "+s.age+" "+s.salary);
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
