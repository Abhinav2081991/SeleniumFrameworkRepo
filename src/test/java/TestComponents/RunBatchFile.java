package TestComponents;

import java.io.IOException;

public class RunBatchFile {

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder pb =  new ProcessBuilder("Path of batch file");
        pb.start();
        int exitCode = pb.start().waitFor();
        if(exitCode==0){
            System.out.println("Process completed Successfully");

        }else{
            System.out.println("Error "+ exitCode);
        }
    }
}
