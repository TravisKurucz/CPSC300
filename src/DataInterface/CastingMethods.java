package DataInterface;

import java.util.ArrayList;
import Database.*;

/**
 * Created by Travis Kurucz on 2017-11-26.
 */
public class CastingMethods {

    //Note: There is probably an easier way to do this with java generics, but here I'm doing the brute force way

    public static ArrayList<Customer> castToCustomers(ArrayList<Object> list){
        ArrayList<Customer> customerList = new ArrayList();
        for (int i=0; i<list.size();i++){
            customerList.set(i, (Customer)list.get(i));
        }
        return customerList;
    }

    public static ArrayList<Equipment> castToEquipment(ArrayList<Object> list){
        ArrayList<Equipment> equipmentList = new ArrayList();
        for (int i=0; i<list.size();i++){
            equipmentList.set(i, (Equipment)list.get(i));
        }
        return equipmentList;
    }

    public static ArrayList<PartOrder> castToPartOrders(ArrayList<Object> list){
        ArrayList<PartOrder> partOrderList = new ArrayList();
        for (int i=0; i<list.size();i++){
            partOrderList.set(i, (PartOrder)list.get(i));
        }
        return partOrderList;
    }

    public static ArrayList<Part> castToParts(ArrayList<Object> list){
        ArrayList<Part> partList = new ArrayList();
        for (int i=0; i<list.size();i++){
            partList.set(i, (Part)list.get(i));
        }
        return partList;
    }

    public static ArrayList<Supplier> castToSuppliers(ArrayList<Object> list){
        ArrayList<Supplier> supplierList = new ArrayList();
        for (int i=0; i<list.size();i++){
            supplierList.set(i, (Supplier)list.get(i));
        }
        return supplierList;
    }




    public static ArrayList<User> castToUsers(ArrayList<Object> list) {
        ArrayList<User> userList = new ArrayList();
        for (int i=0; i<list.size();i++){
            userList.set(i, (User)list.get(i));
        }
        return userList;
    }

    public static ArrayList<WorkOrder> castToWorkOrders(ArrayList<Object> list){
        ArrayList<WorkOrder> workOrderList = new ArrayList();
        for (int i=0; i<list.size();i++){
            workOrderList.set(i, (WorkOrder)list.get(i));
        }
        return workOrderList;
    }

}
