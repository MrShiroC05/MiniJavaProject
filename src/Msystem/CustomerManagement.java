package Msystem;

import model.customer;
import model.transaction;
import model.Data;
import java.util.List;
import java.util.ArrayList;


public class CustomerManagement {
    private static List<customer> customersList = new ArrayList<>();
    protected CustomerManagement(){
        load();
    }

    protected static boolean load(){
        Data.setup();
        customersList = Data.getCustomersList();
        return true;
    }

    protected static customer searchById(int id){
        for (customer customer : customersList) 
            if (customer.getId() == id)
                return customer;
            
        return null;
    }

    protected static customer Login(String usename, String password){
        for (customer customer : customersList) 
            if (customer.login(usename, password))
                return customer;

        return null;
    }

    protected static boolean deposit(float amount, customer cm){
        int index = customersList.indexOf(cm);
        if (index < 0)
            return false;

        cm.add(amount);
        customersList.set(index, cm);
            transaction ts = new transaction(cm.getId(), amount);
        
        Data.addTransaction(ts);

        return true;
    }
    protected static String withdraw(float amount, customer cm){
        int index = customersList.indexOf(cm);
        if (index < 0)
            return "Your data has something wrong.";
        if (!cm.reduce(amount))
            return "check your amount.";

        customersList.set(index, cm);

        Data.addTransaction(new transaction(cm.getId(), -1*amount));
        return "";
    }
    protected static String transfer(float amount, customer giver, customer recipient){
        int giverIndex = customersList.indexOf(giver);
        int recipientIndex = customersList.indexOf(recipient);

        if (giverIndex == recipientIndex && (giverIndex < 0 || recipientIndex < 0))
            return "Chack your Id or their id";

        if (!giver.reduce(amount))
            return "Chack your amount";
        
        recipient.add(amount);

        return "";
    }

    protected static List<customer> getCustomersList(){
        return customersList;
    }

    protected static List<transaction> getTransactionsList(){
        return Data.getTransactionsList();
    }
    protected static List<transaction> getTransactionsList(int id){
        List<transaction> transactions = new ArrayList<>();

        for(transaction ts : Data.getTransactionsList())
            if(ts.getGiverId() == id || ts.getRecipientId() == id)
                transactions.add(ts);

        return Data.getTransactionsList();
    }

}
