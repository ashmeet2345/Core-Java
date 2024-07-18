package FileHandling;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Operations operations=new Operations();
        /*operations.createFile();
        operations.writeDataIntoFile();*/
       // operations.readDataFromFile();
       // operations.deleteFile();
        operations.readUsingBufferedReader();
    }
}
