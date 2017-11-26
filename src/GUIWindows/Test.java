package GUIWindows;

import Database.Customer;
import Database.Management;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by colto on 2017-11-26.
 */
public class Test
{
    public static void Test()
    {
        Stage stage = new Stage();
        stage.setTitle("Test");

        TextArea one = new TextArea();

        TextArea two = new TextArea();

        TextArea three = new TextArea();

        Button save = new Button("Save");

        Button load = new Button("load");

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (one.getText() == "" || two.getText() == "" || three.getText() == "") {
                    JOptionPane.showMessageDialog(null, "You can't save something with an empty field!");
                }
                else{
                    try {
                        System.out.println("Trying to write to file...");
                        File file = new File("CPSC300/src/Database/customers.ser");
                        System.out.println(file.exists());
                        // if (file.exists()){
                        Customer cOne = new Customer();
                        cOne.setName(one.getText());
                        cOne.setEmail(two.getText());
                        cOne.setPhoneNumber((three.getText()));

                            /*Customer cTwo = new Customer();
                            Customer cThree = new Customer();
                            cOne.setName(one.getText());
                            cTwo.setName(two.getText());
                            cThree.setName(three.getText()); */

                        ArrayList<Customer> list = new ArrayList<Customer>();
                        list.add(cOne);
                        /*
                            list.add(cTwo);
                            list.add(cThree);
                         */
                        Management.addObject(file, list);



                        // }

                    } catch (Exception e){
                        System.out.println("You broke it.");
                    }

                }
            }
        });

        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // System.out.println("Trying to write to file...");
                    File file = new File("CPSC300/src/Database/customers.ser");
                    //int lineNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the line number"));
                    //String s = Management.loadFromDatabase(file, lineNumber);
                    //String[] array = s.split(" thisisaflag ");
                    ArrayList l = Management.readList(file);
                    ArrayList<Customer> list = new ArrayList<Customer>();
                    System.out.println("trying to loop");
                    for (int i=0; i<l.size(); i++){
                        list.add((Customer)l.get(i));
                        System.out.println("added "+list.get(i).getName());
                    }

                    one.setText(list.get(0).getName());
                    two.setText(list.get(0).getEmail());
                    three.setText(list.get(0).getPhoneNumber());
                }

                catch(Exception e){
                    e.printStackTrace();

                }




            }


        });


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        grid.add(one, 1,1);
        grid.add(two, 2, 1);
        grid.add(three, 3, 1);
        grid.add(save, 1, 2);
        grid.add (load, 2,2);
        Scene scene = new Scene(grid, 500, 600);
        stage.setScene(scene);
        stage.show();
    }
}
