package SerializeDeserialize;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Serializing s=new Serializing("Ashmeet",250.25,25);

        try {

            FileOutputStream os=new FileOutputStream(new File("C:\\Users\\ACER\\Downloads\\abc\\obj.txt"));
            ObjectOutputStream oos=new ObjectOutputStream(os);
            oos.writeObject(s);
            System.out.println("Object state converted into byte state");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
