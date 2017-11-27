package Database;

import java.io.*;
import java.util.ArrayList;


/**
 * Management is a class that directly interfaces with the data .ser files. It currently has three methods:
 * addObject takes in an object and adds it to a given file by means of saving it into an arraylist and serializing the list
 * readList returns the entire arrayList from the file,
 * writeList will overwrite an entire list to a .ser file (for when small changes have been made to members of that list)
 *
 *
 */
public class Management {



    /**
     *
     * @param file is the file that we're adding to
     * @param newObject is the object that will be added to the arraylist existing in the .ser file
     * @return true if the process was successful, false if the process failed
     */



    public static boolean addObject(File file, Object newObject){
        try{
            //first of all, create an arraylist
            ArrayList list;
            //then, check to see if the file is empty or not (file will exist, but need to see if it is empty)
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {//the file is empty. Create an arraylist, add the object to it,
                                        //then save the arraylist to the .ser file
                list = new ArrayList();

            }
            else {  // the file isn't empty, and therefore contains an arraylist. Read this list and add the object
                    //to it
                //generate the stream to read from the .ser file
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream ObIn = new ObjectInputStream(fileIn);

                //read it and return it
                ArrayList returnArray = new ArrayList();

                Object gamma = ObIn.readObject();

                //close both the readers
                fileIn.close();
                ObIn.close();

                //cast the read object (gamma) as an array list (it is one anyway and should have no conflicts)
                list = (ArrayList) gamma;

            }


            //now, add the object to the list that has either been created or read

            list.add(newObject);

            //now that the object has been added to the list, re-write it to the .ser file
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream ObOut = new ObjectOutputStream(fileOut);

            //write the arraylist back to the .ser file

            ObOut.writeObject(list);

            //close the writers
            fileOut.close();
            ObOut.close();

            //process has been completed, return true
            return true;

        }   catch (Exception e){
            //something went wrong and the process failed. return false
            return false;
        }

    }


    /**
     *
     * @param file is the file that we're trying to read from.
     *
     * @return an ArrayList of all objects in the data file we're reading
     *
     *
     */

    public static ArrayList<Object> readList(String filePath){
        try {
            File file = new File(filePath);
            return readList(file);
        } catch (Exception e){
            return null;
        }
    }

    public static ArrayList<Object> readList(File file) throws IOException {
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null){//file is empty. Cannot read from an empty file
                throw new IOException();
            }

            //generate the stream to read from the .ser file
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream ObIn = new ObjectInputStream(fileIn);

            //read it and return it
            ArrayList returnArray = new ArrayList();

            Object gamma = ObIn.readObject();

            /*
            Now, cast the object (gamma) as an array list. This is a bit dirty, but the only thing to be saved on the
            .ser files will be the single Arraylist of only one type of object
             */

            ArrayList list = (ArrayList) gamma;

            return list;


        }
        catch (Exception e) {
            //TODO this
            System.out.println("Something went wrong");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function writes an entire arraylist to a .ser file after being serialized
     * Note: it does not matter if the file exists or not, or if the file already has something in it.
     * this method will write over that data
     *
     * This function is to be used if an existing list is opened and edited and then needs to be saved back
     * again to the .ser
     *
     * @param file the file we want to write to
     * @param list the list we want to write to the file
     * @return true if the write was successful, false if it threw an exception
     */
    public static boolean writeList(File file, ArrayList list){
        try{
            //create the writers
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream ObOut = new ObjectOutputStream(fileOut);

            //write the arraylist back to the .ser file

            ObOut.writeObject(list);

            //close the writers
            fileOut.close();
            ObOut.close();

            return true;
        }
        catch(Exception e){
            //TODO this
            return false;
        }


    }

}
