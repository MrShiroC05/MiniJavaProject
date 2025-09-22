package Msystem;

import model.customer;
import model.transaction;
import java.util.List;
import java.util.ArrayList;

public class CustomerManagement {
    private List<customer> customersList = new ArrayList<>();

    public CustomerManagement(){
        load();
    }

    public boolean save(){
        return true;
    }
    public boolean load(){
        for(int i = 1; i < 10; i++){
            int random = (int) (Math.random() * 10_000);

            customer cm = new customer(i, random, false, String.format("name-%d", i), String.format("username-%d", i), String.format("password-%d", i), String.format("%d@email", i) );
            customersList.add(cm);
        }
        return true;
    }

    public customer searchById(int id){
        for (customer customer : customersList) 
            if (customer.getId() == id)
                return customer;
            
        return null;
    }

    public customer Login(String usename, String password){
        for (customer customer : customersList) 
            if (customer.login(usename, password))
                return customer;

        return null;
    }

    public boolean deposit(float amount, customer cm){
        int index = customersList.indexOf(cm);
        if (index < 0)
            return false;

        cm.add(amount);
        customersList.set(index, cm);

        receipt(index, amount);
        return true;
    }
    public String withdraw(float amount, customer cm){
        int index = customersList.indexOf(cm);
        if (index < 0)
            return "Your data has something wrong.";
        if (!cm.reduce(amount))
            return "check your amount.";

        customersList.set(index, cm);
        receipt(cm.getId(), amount);

        return "";
    }
    public String transfer(float amount, customer giver, customer recipient){
        int giverIndex = customersList.indexOf(giver);
        int recipientIndex = customersList.indexOf(recipient);

        if (giverIndex == recipientIndex && (giverIndex < 0 || recipientIndex < 0))
            return "Chack your Id or their id";

        if (!giver.reduce(amount))
            return "Chack your amount";
        
        recipient.add(amount);
        receipt(giver.getId(), recipient.getId(), amount);
        return "";
    }

    private void receipt(int giver, int recipient, float amount){
        transaction transaction = new transaction(giver, recipient, amount);

        // save feature comeing soon
    }
    private void receipt(int recipient, float amount){
        transaction transaction = new transaction(recipient, amount);

        // save feature comeing soon

    }
}
