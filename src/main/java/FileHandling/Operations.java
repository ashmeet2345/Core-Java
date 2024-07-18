package FileHandling;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class Operations {

    static String path="C:\\Users\\ACER\\Desktop\\myfile.txt";
    public Operations(){

    }

    public void createFile() throws IOException {

        try{
            File obj=new File(path);
            if (obj.createNewFile()) {
                System.out.println("File " + obj.getName() + " is created successfully.");
            } else {
                throw new FileAlreadyExistsException("File exists already");
            }
        } catch (FileAlreadyExistsException e){
            e.printStackTrace();
        }

    }

    public void writeDataIntoFile(){
        try{
            FileWriter writeToFile=new FileWriter(path);
            writeToFile.write("Rosa parks was an african american women");
            writeToFile.close();
            System.out.println("Content written to file successfully");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDataFromFile(){
        try{
            File file=new File(path);
            Scanner sc=new Scanner(file);
            if(sc.hasNextLine()){
                String line=sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFile(){
        try{
            File obj=new File(path);
            if(obj.delete()){
                System.out.println("File deleted successfully");
            } else {
                throw new FileNotFoundException("File deletion failed");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void readUsingBufferedReader(){
        try{
            File file=new File(path);
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String str="";
            if((str=reader.readLine())!=null){
                System.out.println(str);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
