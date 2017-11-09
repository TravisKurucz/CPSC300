package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Management {


    public static boolean addObject(File file, Object newEntry){
        return true;
    }

    public static boolean addToDatabase(File file, String s) throws IOException
    {
        if(true)
        {
          //  System.out.println(file.getName());
            //System.out.println(s);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.append(s +"\n");
            bufferedWriter.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String loadFromDatabase(File file, int line)throws  IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
       int count = 1;
       String s = reader.readLine();
       while ((count) < line && s != null) {
           reader.readLine();
           count++;
       }
        String output = reader.readLine();
        reader.close();
        return output;





    }
}
