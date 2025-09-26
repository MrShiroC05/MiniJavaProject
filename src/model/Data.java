package model;

import java.util.List;
import java.util.ArrayList;

public class Data {
    private static List<customer> customersList= new ArrayList<>();
    private static List<transaction> transactionsList = new ArrayList<>();

    private static void generateCustomers(int n){

        for(int i = 1; i <= n; i++){
            boolean isBan = Math.random() < 0.2;
            customer cm = new customer(i, 0, isBan, String.format("name-%d", i), String.format("username-%d", i), String.format("password-%d", i), String.format("%d@email", i) );
            customersList.add(cm);
        }
    }


    public static void setup(){
        customersList.clear();
        transactionsList.clear();
        generateCustomers(10);
    }  
    public static void setup(int number){
        customersList.clear();
        transactionsList.clear();
        generateCustomers(number);
    }


    public static boolean addCustomer(customer cm){
        if (customersList.contains(cm))
            return false;
        
        customersList.add(cm);
        return true;
    }
    public static boolean addTransaction(transaction ts){
        if (transactionsList.contains(ts))
            return false;
        
        transactionsList.add(ts);
        return true;

    }
    
    public static List<customer> getCustomersList(){
        return customersList;
    }
    public static List<transaction> getTransactionsList(){
        return transactionsList;
    }
}
