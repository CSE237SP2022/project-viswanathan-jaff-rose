//package Interpreter;
import java.io.File;
import java.util.Scanner;

public class ASMFileReader {

    public static void main(String[] args){

        File file = new File(args[0]);

        try {
            Scanner sc = new Scanner(file);
            String fileName = file.toString();
            boolean validFile = false;
            int index = fileName.lastIndexOf('.');

            if(index > 0) {
                String extension = fileName.substring(index + 1);
                if(extension.equals("S")){
                    validFile = true;
                }
            }

            if(!validFile){
                System.out.println("INVALID FILE TYPE!");
            }

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }

        } catch (Exception e){
            System.out.println("File not found");
        }
        
    }
}