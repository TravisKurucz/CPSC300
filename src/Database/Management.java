package Database;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Management {

    /**
     *
     * @param file is the file that we're trying to add to
     * @param newEntry is the entry we're trying to add to the file. MUST be serializable
     * @return true if the function succeeded, false if the function failed and couldn't write
     */
    public static boolean addObject(File file, Object newEntry){
        try {
            //create the output stream to begin writing objects to the file
            FileOutputStream fileOut = new FileOutputStream(file);
            //TODO delet ths
           // Customer c = (Customer)newEntry;
           // System.out.println(c.getName());

            System.out.println("Trying to write to file " + file.getName());
            ObjectOutputStream ObOut = new ObjectOutputStream(fileOut);

            //write the object to the given datafile
            ObOut.writeObject(newEntry);

            //close the writers
            ObOut.close();
            fileOut.close();

        }   catch(Exception e){
            //if something happened,
            System.out.println("Something went wrong");
            e.printStackTrace();
            return false;
        }
        //if this has succeeded, return true
        return true;
    }//end add object

    /**
     *
     * @param file is the file that we're trying to read from.
     *
     * @return an ArrayList of all objects in the data file we're reading
     *
     *
     */

    public static ArrayList<Object> readList(File file) {
        try {

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream ObIn = new ObjectInputStream(fileIn);
            ArrayList returnArray = new ArrayList();

            Object gamma = ObIn.readObject();
            //oof
            ArrayList list = (ArrayList) gamma;

            return list;


        } catch (Exception e) {
            //TODO this
            System.out.println("Something went wrong");
            e.printStackTrace();
            return null;
        }
    }
}
